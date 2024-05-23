package com.example.smart_travelogue;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class User_home extends AppCompatActivity {


    Button btn,btn1,btn2,btn3,btn4,btn5,btn6,b1,b2,other,fe,mm;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_home);

        fe=findViewById(R.id.fee);
        fe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getApplicationContext(),send_feedback.class);
                startActivity(i);
            }
        });

        other=findViewById(R.id.button2);
        other.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getApplicationContext(),view_others_traveloge.class);
                startActivity(i);
            }
        });
        b1=findViewById(R.id.service);

        startService(new Intent(getApplicationContext(),Locationservice.class));

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), View_services.class));
            }
        });

        btn = findViewById(R.id.Viewplaces);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(getApplicationContext(),View_places.class));
            }
        });
//        btn1 = findViewById(R.id.manage_audio_video);
//        btn1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                startActivity(new Intent(getApplicationContext(),Manage_audio_video.class));
//            }
//        });
        btn2 = findViewById(R.id.view_noti);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(getApplicationContext(),View_notification.class));
            }
        });btn3 = findViewById(R.id.ds);
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(getApplicationContext(),View_Packages.class));
            }
        });
        btn4 = findViewById(R.id.nby);
        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(getApplicationContext(),Near_by_facilities.class));
            }
        });
        btn5 = findViewById(R.id.nerbyplc);
        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(getApplicationContext(),View_near_by_places.class));
            }
        });
        btn6 = findViewById(R.id.addtravelogue);
        btn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(getApplicationContext(),Manage_travelogue.class));
            }
        });
        b2 = findViewById(R.id.logut);
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(getApplicationContext(),Login.class));
            }
        });
        mm = findViewById(R.id.button22);
        mm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(getApplicationContext(),view_media.class));
            }
        });
    }
}