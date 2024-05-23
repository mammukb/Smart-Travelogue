package com.example.smart_travelogue;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;


public class  View_travelogue extends AppCompatActivity implements JsonResponse, AdapterView.OnItemClickListener {

    ListView lv;

    SharedPreferences sh;

    String [] travelogue_id,title,desc,val;
    public static String travelogue_ids;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_travelogue);

        lv = findViewById(R.id.list);
        lv.setOnItemClickListener(this);
        sh = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

        JsonReq JR = new JsonReq();
        JR.json_response = (JsonResponse) View_travelogue.this;
        String q = "/View_travelogue?login=" + sh.getString("login_id","");
        q = q.replace(" ", "%20");
        JR.execute(q);
    }

    @Override
    public void response(JSONObject jo) {

        try {
            String status = jo.getString("status");
            if (status.equalsIgnoreCase("success")){

                JSONArray ja1 = jo.getJSONArray("data");
                travelogue_id = new String[ja1.length()];
                title = new String[ja1.length()];
                desc = new String[ja1.length()];

                val = new String[ja1.length()];

                for(int i = 0;i<ja1.length();i++)
                {

                    travelogue_id[i] = ja1.getJSONObject(i).getString("id");
                    title[i] = ja1.getJSONObject(i).getString("Title");
                    desc[i] = ja1.getJSONObject(i).getString("description");

                    val[i]="Title" + title[i];
//
//
                }

//                ArrayAdapter<String> ar=new ArrayAdapter<>(getApplicationContext(),android.R.layout.simple_list_item_1,val);
//                lv.setAdapter(ar);

                Custome_Travelogue ll = new Custome_Travelogue(View_travelogue.this,travelogue_id,title,desc);
                lv.setAdapter(ll);





            }

        }catch (Exception e){
            Toast.makeText(this, e.toString(), Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        travelogue_ids=travelogue_id[position];
        SharedPreferences.Editor e = sh.edit();
        e.putString("trav_id", travelogue_ids);
        e.commit();
        startActivity(new Intent(getApplicationContext(),Manage_audio_video.class));


    }
}