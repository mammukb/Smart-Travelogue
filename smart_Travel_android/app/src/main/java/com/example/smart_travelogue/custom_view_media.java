package com.example.smart_travelogue;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class custom_view_media extends BaseAdapter {

    private Context context;
    SharedPreferences sh;
    String[] id,tit,desc,pname,file,ftype;

    public custom_view_media(Context context, String[] id, String[] tit, String[] desc, String[] pname, String[] file, String[] ftype) {

        this.context=context;
        this.id=id;
        this.tit=tit;
        this.desc=desc;
        this.pname=pname;
        this.file=file;
        this.ftype=ftype;



        }

        @Override
        public int getCount() {
            return id.length;
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            LayoutInflater inflator=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            View gridView;
            if(view==null)
            {
                gridView=new View(context);
                //gridView=inflator.inflate(R.layout.customview, null);
                gridView=inflator.inflate(R.layout.activity_custom_view_media,null);

            }
            else
            {
                gridView=(View)view;

            }
            TextView tv1=(TextView)gridView.findViewById(R.id.data);
            TextView tv2=(TextView)gridView.findViewById(R.id.data1);
            TextView tv3=(TextView)gridView.findViewById(R.id.textView5);
            ImageView im=(ImageView)gridView.findViewById(R.id.imageView3);

            tv1.setText(tit[i]);
            tv2.setText(desc[i]);
            tv3.setText(pname[i]);

            sh= PreferenceManager.getDefaultSharedPreferences(context.getApplicationContext());

            String pth = "http://"+sh.getString("ip", "")+"/static/"+file[i];
            pth = pth.replace("~", "");
//	       Toast.makeText(context, pth, Toast.LENGTH_LONG).show();

            Log.d("-------------", pth);
            Picasso.with(context)
                    .load(pth)
                    .placeholder(R.drawable.ic_launcher_background)
                    .error(R.drawable.ic_launcher_background).into(im);


            return gridView;
    }
}
