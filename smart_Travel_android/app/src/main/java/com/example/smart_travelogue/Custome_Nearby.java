package com.example.smart_travelogue;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

//import com.squareup.picasso.Picasso;

public class Custome_Nearby extends ArrayAdapter<String> {

    private Activity context;       //for to get current activity context
    SharedPreferences sh;

    private String[] type,f_name,f_place,city,state,phone,email,photo,lati,longi;



    public Custome_Nearby(Near_by_facilities context, String[] type, String[] f_name, String[] f_place, String[] city, String[] state, String[] phone, String[] email, String[] photo,String[] lati,String[] longi) {
        super(context, R.layout.activity_custome_nearby, type);
        this.context = context;
        this.type = type;
        this.f_name=f_name;
        this.f_place=f_place;
        this.city=city;
        this.state=state;
        this.phone=phone;
        this.email=email;
        this.photo=photo;
        this.lati=lati;
        this.longi=longi;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //override getView() method

        LayoutInflater inflater = context.getLayoutInflater();
        View listViewItem = inflater.inflate(R.layout.activity_custome_nearby, null, true);
        //cust_list_view is xml file of layout created in step no.2
        ImageView img = (ImageView) listViewItem.findViewById(R.id.location);

        ImageView im = (ImageView) listViewItem.findViewById(R.id.imageView);
        TextView t=(TextView)listViewItem.findViewById(R.id.type);
        TextView t1=(TextView)listViewItem.findViewById(R.id.vehicle);
        TextView t2=(TextView)listViewItem.findViewById(R.id.reg);
        TextView t3=(TextView)listViewItem.findViewById(R.id.name);
        TextView t4=(TextView)listViewItem.findViewById(R.id.stock);
        TextView t5=(TextView)listViewItem.findViewById(R.id.phone);
        TextView t6=(TextView)listViewItem.findViewById(R.id.email);




        t.setText("Type:"+ type[position]);
        t1.setText("Facility:"+ f_name[position]);
        t2.setText("Place:"+f_place[position]);
        t3.setText("City:"+city[position]);
        t4.setText("State:"+state[position]);
        t5.setText("Contact:"+phone[position]);
        t6.setText("Email:"+email[position]);


        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(android.content.Intent.ACTION_VIEW,
                        Uri.parse("http://maps.google.com/maps?q="+lati[position]+","+longi[position]));
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });


        sh= PreferenceManager.getDefaultSharedPreferences(getContext());

        String pth = "http://"+sh.getString("ip", "")+"/static/"+photo[position];
        pth = pth.replace("~", "");
//	       Toast.makeText(context, pth, Toast.LENGTH_LONG).show();

        Log.d("-------------", pth);
        Picasso.with(context)
                .load(pth)
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_background).into(im);

        return  listViewItem;
    }





    private TextView setText(String string) {
        // TODO Auto-generated method stub
        return null;
    }
}