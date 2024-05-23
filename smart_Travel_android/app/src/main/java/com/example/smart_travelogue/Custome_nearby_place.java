package com.example.smart_travelogue;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class Custome_nearby_place extends ArrayAdapter<String>  {

    private Activity context;
    SharedPreferences sh;
    private String[] place_id, p_name, city,lmark,state,latitude,longitude;

    public static  String plc_id;


    TextView t,t1,t2,t3,t4,t5,t6,t7,t8,t9,t10;





    public Custome_nearby_place(View_near_by_places context, String[] p_name, String[] city, String[] lmark, String[] state, String[] latitude, String[] longitude) {
        super(context, R.layout.activity_custome_nearby_place, p_name);
        this.context = context;
        this.p_name=p_name;
        this.city=city;
        this.lmark=lmark;
        this.state=state;
        this.latitude=latitude;
        this.longitude= longitude;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //override getView() method

        LayoutInflater inflater = context.getLayoutInflater();
        View listViewItem = inflater.inflate(R.layout.activity_custome_nearby_place, null, true);
        //cust_list_view is xml file of layout created in step no.2

        ImageView im = (ImageView) listViewItem.findViewById(R.id.location);

//        im.setTag(place_id[position]);
        im.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//                int ik=Integer.parseInt(view.getTag().toString());
                Intent intent = new Intent(android.content.Intent.ACTION_VIEW,
                        Uri.parse("http://maps.google.com/maps?q="+latitude[position]+","+longitude[position]));
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);

            }
        });

        ImageView img = (ImageView) listViewItem.findViewById(R.id.review);

        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                Intent in = new Intent(context.getApplicationContext(), Send_review.class);
                in.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(in);
            }
        });

        TextView t=(TextView)listViewItem.findViewById(R.id.label);
        TextView t1=(TextView)listViewItem.findViewById(R.id.data);

        TextView t2=(TextView)listViewItem.findViewById(R.id.label1);
        TextView t3=(TextView)listViewItem.findViewById(R.id.data1);

        TextView t4=(TextView)listViewItem.findViewById(R.id.label3);
        TextView t5=(TextView)listViewItem.findViewById(R.id.data3);

        TextView t6=(TextView)listViewItem.findViewById(R.id.label4);
        TextView t7=(TextView)listViewItem.findViewById(R.id.data4);


//
        t.setText( "Place");
        t1.setText(p_name[position]);

        t2.setText("City");
        t3.setText(city[position]);

        t4.setText("LandMark");
        t5.setText(lmark[position]);

        t6.setText("State");
        t7.setText(state[position]);




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


    public void setAdapter(Custome_nearby_place drg) {
    }
}