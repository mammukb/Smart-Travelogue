package com.example.smart_travelogue;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class view_others_custom_traveloge extends ArrayAdapter<String> {

    private Activity context;
    SharedPreferences sh;
    private String[] travelogue_id, title, desc;

    public static  String plc_id,trav_id;


    TextView t,t1,t2,t3,t4,t5,t6,t7,t8,t9,t10;



    ImageView img;

    public view_others_custom_traveloge(view_others_traveloge context, String[] travelogue_id, String[] title, String[] desc) {
        super(context, R.layout.activity_view_others_custom_traveloge, travelogue_id);
        this.context = context;
        this.travelogue_id=travelogue_id;
        this.title = title;
        this.desc=desc;


    }



    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //override getView() method

        LayoutInflater inflater = context.getLayoutInflater();
        View listViewItem = inflater.inflate(R.layout.activity_view_others_custom_traveloge, null, true);
        //cust_list_view is xml file of layout created in step no.2


        sh = PreferenceManager.getDefaultSharedPreferences(context.getApplicationContext());



        TextView t=(TextView)listViewItem.findViewById(R.id.label);
        TextView t1=(TextView)listViewItem.findViewById(R.id.data);

        TextView t2=(TextView)listViewItem.findViewById(R.id.label1);
        TextView t3=(TextView)listViewItem.findViewById(R.id.data1);




//
        t.setText( "Title");
        t1.setText(title[position]);

        t2.setText("Description");
        t3.setText(desc[position]);
        trav_id = travelogue_id[position]; // Store the travelogue_id value at the clicked position

        // Storing trav_id in SharedPreferences
        SharedPreferences.Editor editor = context.getSharedPreferences("YourPrefsName", Context.MODE_PRIVATE).edit();
        editor.putString("trav_id", trav_id);
        editor.apply();



        ImageView img = (ImageView) listViewItem.findViewById(R.id.media);
        img.setVisibility(View.INVISIBLE);

        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                trav_id=travelogue_id[position];



                Intent in = new Intent(context.getApplicationContext(), Manage_audio_video.class);
                in.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(in);
            }
        });



//        t.setText(Html.fromHtml("SCHOOL : " + school_name[position]
//                + "<br/>TITLE : " + title[position]
//                + "<br/>DETAILS : " + description[position]
//                + "<br/>DATE : " + date[position]
//                + "<br/>" + statusText, Html.FROM_HTML_MODE_LEGACY));



//        t.setText("Name : "+ student_name[position] + "\nCourse : "  + course_name[position]
//                + "\nDepartment : " + department_name[position] + "\nDob : " +dob[position]
//                + "\nGender : "+ gender[position]
//                +"\nAddress : " + place[position] +",\n"+ post[position] +",\n"+pincode[position]+",\n"+district[position]
//                + "\nContact-No : " +phone[position]  );
//        t1.setText("Name : " + student_name[position]+"\nPh. : " + phone[position] );
//        t2.setText("Description : "+description[position]);
//        t3.setText("Cost : "+cost[position]);
//        t4.setText("Date : "+date_time[position]);
//        t5.setText("Status : "+wstatus[position]);

//        SpannableString spannableText = new SpannableString(text);
//
// Change font color for student_name[position]
//        int studentNameStart = t.indexOf(student_name[position]);
//        int studentNameEnd = studentNameStart + student_name[position].length();
//        spannableText.setSpan(new ForegroundColorSpan(Color.RED), studentNameStart, studentNameEnd, Spannable.SPAN_INCLUSIVE_EXCLUSIVE);

// Change font color for course_name[position]
//        int studentNameStart = text.indexOf(student_name[position]);
//        int studentNameEnd = studentNameStart + student_name[position].length();
//        spannableText.setSpan(new ForegroundColorSpan(Color.WHITE), studentNameStart, studentNameEnd, Spannable.SPAN_INCLUSIVE_EXCLUSIVE);

// Change font color for course_name[position] to white with transparency


//
//        sh=PreferenceManager.getDefaultSharedPreferences(getContext());
//
//        String pth = "http://"+sh.getString("ip", "")+"/"+place[position];
//        pth = pth.replace("~", "");
//	       Toast.makeText(context, pth, Toast.LENGTH_LONG).show();
//
//        Log.d("-------------", pth);
//        Picasso.with(context)
//                .load(pth)
//                .placeholder(R.drawable.pro)
//                .error(R.drawable.pro).into(im);
//
        return  listViewItem;
    }

    private TextView setText(String string) {
        // TODO Auto-generated method stub
        return null;
    }


    public void setAdapter(Custome_Travelogue drg) {
    }
}