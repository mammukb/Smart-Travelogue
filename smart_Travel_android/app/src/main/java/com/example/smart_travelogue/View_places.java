package com.example.smart_travelogue;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONArray;
import org.json.JSONObject;

public class View_places extends AppCompatActivity implements JsonResponse {


    ListView lv;
    String [] place_id,place,city,lmark,state,lati,longi,val;
    FloatingActionButton fab;

    public  static  String plc_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_places);
        fab=findViewById(R.id.floatingActionButton);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getApplicationContext(),Add_place.class);
                startActivity(i);
            }
        });
        lv = findViewById(R.id.list);

        JsonReq JR = new JsonReq();
        JR.json_response = (JsonResponse) View_places.this;
        String q = "/view_placess";
        q = q.replace(" ", "%20");
        JR.execute(q);
    }

    @Override
    public void response(JSONObject jo) {


        try {
            String status = jo.getString("status");
            if(status.equalsIgnoreCase("success")){
                JSONArray ja1=(JSONArray)jo.getJSONArray("data");
//                Toast.makeText(this, "aaa", Toast.LENGTH_SHORT).show();
                place_id = new String[ja1.length()];
                place = new String[ja1.length()];
                city = new String[ja1.length()];
                lmark = new String[ja1.length()];
                state = new String[ja1.length()];
                lati = new String[ja1.length()];
                longi = new String[ja1.length()];
                val = new String[ja1.length()];

                for(int i = 0;i<ja1.length();i++)
                {

                    place_id[i] = ja1.getJSONObject(i).getString("place_id");
                    place[i] = ja1.getJSONObject(i).getString("place_name");
                    city[i] = ja1.getJSONObject(i).getString("city");
                    lmark[i] = ja1.getJSONObject(i).getString("landmark");
                    state[i]=ja1.getJSONObject(i).getString("state");
                    lati[i] = ja1.getJSONObject(i).getString("lati");
                    longi[i] = ja1.getJSONObject(i).getString("longi");
//                    Toast.makeText(this, "bbbb", Toast.LENGTH_SHORT).show();
                    val[i] = "Place : "+place[i]+"\ncity : " +city[i] +
                    "\nState : " + state[i] + "\nLandmark : " +lmark[i];
                }


                Customize_places ll = new Customize_places(View_places.this,place_id,place,city,lmark,state,lati,longi);
                lv.setAdapter(ll);






            }

        }catch (Exception e){
            Toast.makeText(this, e.toString(), Toast.LENGTH_SHORT).show();
        }
    }
    @Override
    public void onBackPressed() {
        // TODO Auto-generated method stub
        super.onBackPressed();
        startActivity(new Intent(getApplicationContext(), User_home.class));
    }


}