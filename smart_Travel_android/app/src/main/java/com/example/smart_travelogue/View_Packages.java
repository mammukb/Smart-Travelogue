package com.example.smart_travelogue;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

public class View_Packages extends AppCompatActivity implements JsonResponse {


    ListView lv;
    String [] package_id,p_name,details,valid_from,valid_till,price,facility,val;

    public  static  String plc_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_packages);

        lv = findViewById(R.id.list);

        JsonReq JR = new JsonReq();
        JR.json_response = (JsonResponse) View_Packages.this;
        String q = "/view_packages";
        q = q.replace(" ", "%20");
        JR.execute(q);
    }

    @Override
    public void response(JSONObject jo) {


        try {
            String status = jo.getString("status");
            if(status.equalsIgnoreCase("success")){
                JSONArray ja1=(JSONArray)jo.getJSONArray("data");
                Toast.makeText(this, "aaa", Toast.LENGTH_SHORT).show();
                package_id = new String[ja1.length()];
                p_name = new String[ja1.length()];
                details = new String[ja1.length()];
                valid_from = new String[ja1.length()];
                valid_till = new String[ja1.length()];
                price = new String[ja1.length()];
                val = new String[ja1.length()];

                for(int i = 0;i<ja1.length();i++)
                {

                    package_id[i] = ja1.getJSONObject(i).getString("package_id");
                    p_name[i] = ja1.getJSONObject(i).getString("p_name");
                    details[i] = ja1.getJSONObject(i).getString("details");
                    valid_from[i] = ja1.getJSONObject(i).getString("valid_from");
                    valid_till[i]=ja1.getJSONObject(i).getString("valid_till");
                    price[i] = ja1.getJSONObject(i).getString("price");
                    val[i] = "Package_Name : "+p_name[i]+"\nDetails : " +details[i] +
                            "\nValid_from : " + valid_from[i] + "\nValid_till : " +valid_till[i]
                            +
                            "\nPrice : " + price[i];
                }

                ArrayAdapter<String> ar=new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_list_item_1,val);
                lv.setAdapter(ar);

//                Customize_places ll = new Customize_places(View_places.this,place_id,place,city,lmark,state,lati,longi);
//                lv.setAdapter(ll);



//                lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//                    @Override
//                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//
//                        plc_id = place_id[position];
//
//                        startActivity(new Intent(getApplicationContext(),Send_review.class));
//
////
////
////
//                    }
//                });




            }

        }catch (Exception e){
            Toast.makeText(this, e.toString(), Toast.LENGTH_SHORT).show();
        }
    }
}