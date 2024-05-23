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
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

public class View_near_by_places extends AppCompatActivity implements JsonResponse {

    GridView lv1;
    String [] place_id,p_name,city,lmark ,state,latitude,longitude,val;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_near_by_places);

//
//        startService(new Intent(getApplicationContext(),LocationService.class));

        lv1=(GridView)findViewById(R.id.lvnear);

        JsonReq JR=new JsonReq();
        JR.json_response=(JsonResponse) View_near_by_places.this;
        String q = "/user_view_near_by_places?lati="+Locationservice.lati+"&logi="+Locationservice.logi;
        q=q.replace(" ","%20");
        JR.execute(q);
    }




    @Override
    public void response(JSONObject jo) {
        // TODO Auto-generated method stub
        try {

            String status=jo.getString("status");
            Log.d("pearl",status);
//            Toast.makeText(getApplicationContext(),status, Toast.LENGTH_SHORT).show();
            if(status.equalsIgnoreCase("success")){

                JSONArray ja1=(JSONArray)jo.getJSONArray("data");
                place_id=new String[ja1.length()];
                p_name=new String[ja1.length()];
                city=new String[ja1.length()];
                lmark=new String[ja1.length()];
                state=new String[ja1.length()];
                latitude=new String[ja1.length()];
                longitude=new String[ja1.length()];



                val=new String[ja1.length()];

                for(int i = 0;i<ja1.length();i++)
                {

                    place_id[i]=ja1.getJSONObject(i).getString("place_id");
                    p_name[i]=ja1.getJSONObject(i).getString("p_name");
                    city[i]=ja1.getJSONObject(i).getString("city");
                    lmark[i]=ja1.getJSONObject(i).getString("lmark");
                    state[i]=ja1.getJSONObject(i).getString("state");
                    latitude[i]=ja1.getJSONObject(i).getString("latitude");
                    longitude[i]=ja1.getJSONObject(i).getString("longitude");


//                    val[i]="Type : "+type[i]+"\nFacility : "
//                            +f_name[i]+"\nPlace : "+f_place[i]+"\nCity : "
//                            +city[i]+"\nState : "+state[i]
//                            +"\nPhone : "+phone[i]+"\nEmail : "+email[i];


                }
//                ArrayAdapter<String> ar=new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_list_item_1,val);
//                lv1.setAdapter(ar);
                Custome_nearby_place ci=new Custome_nearby_place(View_near_by_places.this,p_name,city,lmark,state,latitude,longitude);
                lv1.setAdapter((ListAdapter) ci);


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
