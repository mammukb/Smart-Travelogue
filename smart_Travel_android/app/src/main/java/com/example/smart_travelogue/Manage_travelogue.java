package com.example.smart_travelogue;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONObject;

public class Manage_travelogue extends AppCompatActivity  implements JsonResponse{


    EditText e1,e2;

    Button btn;

    String title,description;


    TextView t1;


    SharedPreferences sh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_travelogue);

        sh = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        e1 = findViewById(R.id.complaint_tt);
        e2 = findViewById(R.id.description);

        btn = findViewById(R.id.button1);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                title = e1.getText().toString();
                description = e2.getText().toString();


                JsonReq JR = new JsonReq();
                JR.json_response = (JsonResponse) Manage_travelogue.this;
                String q = "/add_travelogue?login_id=" + sh.getString("login_id","") + "&title=" + title + "&desc=" + description;
                q = q.replace(" ", "%20");
                JR.execute(q);


            }
        });
        t1 = findViewById(R.id.Viwtravelogue);
        t1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),View_travelogue.class));

            }
        });

    }

    @Override
    public void response(JSONObject jo) {
        
        
        
        try {
            
            String status=jo.getString("status");
            if (status.equalsIgnoreCase("success")){
                Toast.makeText(this, "added huui :)", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getApplicationContext(),User_home.class));
            }
            
        }catch (Exception e){
            Toast.makeText(this, e.toString(), Toast.LENGTH_SHORT).show();
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