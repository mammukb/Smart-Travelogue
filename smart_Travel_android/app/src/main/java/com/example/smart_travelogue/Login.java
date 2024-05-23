package com.example.smart_travelogue;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.smart_travelogue.IPSetting;
import com.example.smart_travelogue.JsonReq;
import com.example.smart_travelogue.User_home;

import org.json.JSONArray;
import org.json.JSONObject;

public class Login extends AppCompatActivity implements JsonResponse {

    EditText e1, e2;
    Button btn;
    TextView textView;
    String uname, psd;

    SharedPreferences sh;
    public static String logid, usertype;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        sh = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

        e1 = findViewById(R.id.editTextText5);
        e2 = findViewById(R.id.editTextTextPassword2);
        textView = findViewById(R.id.textView10);

        String text = "Don't have an account? <font color='#fff'>Register Now!</font>";
        textView.setText(Html.fromHtml(text));

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle the click event, e.g., navigate to the registration page
                startActivity(new Intent(Login.this, User_registration.class));
            }
        });



        btn = findViewById(R.id.button4);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uname = e1.getText().toString();
                psd = e2.getText().toString();

                if (uname.equalsIgnoreCase("")) {
                    e1.setError("Enter the username");
                    e1.setFocusable(true);
                } else if (psd.equalsIgnoreCase("")) {
                    e2.setError("enter the password");
                    e2.setFocusable(true);
                } else {
                    JsonReq JR = new JsonReq();
                    JR.json_response = Login.this;
                    String q = "/phonelogin?username=" + uname + "&password=" + psd;
                    q = q.replace(" ", "%20");
                    JR.execute(q);
                }
            }
        });
    }

    @Override
    public void response(JSONObject jo) {
        try {
            String status = jo.getString("status");
            Log.d("pearl", status);

            if (status.equalsIgnoreCase("success")) {
                JSONArray ja1 = jo.getJSONArray("data");
                logid = ja1.getJSONObject(0).getString("login_id");
                usertype = ja1.getJSONObject(0).getString("usertype");

                SharedPreferences.Editor e = sh.edit();
                e.putString("login_id", logid);
                e.putString("usertype", usertype);
                e.commit();


                if (usertype.equals("user")) {
                    Toast.makeText(getApplicationContext(), "Login Success", Toast.LENGTH_LONG).show();
                    startActivity(new Intent(getApplicationContext(), User_home.class));
//                    Intent ik=new Intent(getApplicationContext(),Locationservice.class);
//                    startService(ik);
                }
//                }if (usertype.equals("student")) {
//                    Toast.makeText(getApplicationContext(), "Login Success", Toast.LENGTH_LONG).show();
//                    startActivity(new Intent(getApplicationContext(), Home_page.class));
//                }
            } else {
                Toast.makeText(getApplicationContext(), "Login failed..! Please enter correct username or password ", Toast.LENGTH_LONG).show();
                startActivity(new Intent(getApplicationContext(), Login.class));
            }
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_LONG).show();
        }
    }
    public void onBackPressed()
    {
        // TODO Auto-generated method stub
        super.onBackPressed();
        Intent b=new Intent(getApplicationContext(), IPSetting.class);
        startActivity(b);
    }
}
