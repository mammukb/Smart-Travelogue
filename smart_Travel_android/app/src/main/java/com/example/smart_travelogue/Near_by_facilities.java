package com.example.smart_travelogue;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

public class Near_by_facilities extends AppCompatActivity implements JsonResponse {

    GridView lv1;
    String [] facility_id,type,f_name,f_place,city ,state,phone,email,val,photo,lati,longi;
//    public static String vehicle_ids,driver_ids,type_ids,assign_ids,lts,lgs,fuel_types,rates;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_near_by_facilities);


        startService(new Intent(getApplicationContext(),Locationservice.class));

        lv1=(GridView)findViewById(R.id.lvnear);

        JsonReq JR=new JsonReq();
        JR.json_response=(JsonResponse) Near_by_facilities.this;
        String q = "/user_near_by_facility?lati="+Locationservice.lati+"&logi="+Locationservice.logi;
        q=q.replace(" ","%20");
        JR.execute(q);
    }




    @Override
    public void response(JSONObject jo) {
        // TODO Auto-generated method stub
        try {

            String status=jo.getString("status");
            Log.d("pearl",status);
            Toast.makeText(getApplicationContext(),status, Toast.LENGTH_SHORT).show();
            if(status.equalsIgnoreCase("success")){

                JSONArray ja1=(JSONArray)jo.getJSONArray("data");
                facility_id=new String[ja1.length()];
                type=new String[ja1.length()];
                f_name=new String[ja1.length()];
                f_place=new String[ja1.length()];
                city=new String[ja1.length()];
                state=new String[ja1.length()];
                phone=new String[ja1.length()];
                email=new String[ja1.length()];
                photo=new String[ja1.length()];
                lati=new String[ja1.length()];
                longi=new String[ja1.length()];



                val=new String[ja1.length()];

                for(int i = 0;i<ja1.length();i++)
                {

                    facility_id[i]=ja1.getJSONObject(i).getString("facility_id");
                    type[i]=ja1.getJSONObject(i).getString("type");
                    f_name[i]=ja1.getJSONObject(i).getString("f_name");
                    f_place[i]=ja1.getJSONObject(i).getString("f_place");
                    city[i]=ja1.getJSONObject(i).getString("city");
                    state[i]=ja1.getJSONObject(i).getString("state");
                    phone[i]=ja1.getJSONObject(i).getString("phone");
                    email[i]=ja1.getJSONObject(i).getString("email");
                    photo[i]=ja1.getJSONObject(i).getString("photo");
                    lati[i]=ja1.getJSONObject(i).getString("lati");
                    longi[i]=ja1.getJSONObject(i).getString("longi");


                    val[i]="Type : "+type[i]+"\nFacility : "
                            +f_name[i]+"\nPlace : "+f_place[i]+"\nCity : "
                            +city[i]+"\nState : "+state[i]
                            +"\nPhone : "+phone[i]+"\nEmail : "+email[i];


                }
//                ArrayAdapter<String> ar=new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_list_item_1,val);
//                lv1.setAdapter(ar);
                Custome_Nearby ci=new Custome_Nearby(Near_by_facilities.this,type,f_name,f_place,city,state,phone,email,photo,lati,longi);
                lv1.setAdapter(ci);





            }

            else {
                Toast.makeText(getApplicationContext(), "no data", Toast.LENGTH_LONG).show();

            }
//
        }catch (Exception e)
        {
            // TODO: handle exception

            Toast.makeText(getApplicationContext(),e.toString(), Toast.LENGTH_LONG).show();
        }



    }


    public void onBackPressed()
    {
        // TODO Auto-generated method stub
        super.onBackPressed();
        Intent b=new Intent(getApplicationContext(),User_home.class);
        startActivity(b);
    }




}
