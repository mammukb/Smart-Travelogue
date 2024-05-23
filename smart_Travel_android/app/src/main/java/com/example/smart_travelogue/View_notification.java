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

public class View_notification extends AppCompatActivity implements JsonResponse {


    ListView lv;
    String [] noti,date,val;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_notification);

        lv = findViewById(R.id.list);

        JsonReq JR = new JsonReq();
        JR.json_response = (JsonResponse) View_notification.this;
        String q = "/view_noti";
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
                noti = new String[ja1.length()];
                date = new String[ja1.length()];

                val = new String[ja1.length()];

                for(int i = 0;i<ja1.length();i++)
                {

                    noti[i] = ja1.getJSONObject(i).getString("noti");
                    date[i] = ja1.getJSONObject(i).getString("date");

                    val[i] = "Notification: : "+noti[i]+"\nDate : " +date[i] ;
                }

                ArrayAdapter<String> ar=new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_list_item_1,val);
                lv.setAdapter(ar);
//
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