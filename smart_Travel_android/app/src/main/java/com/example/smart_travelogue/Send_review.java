package com.example.smart_travelogue;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RatingBar;
import android.widget.Toast;

import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;



public class Send_review extends AppCompatActivity implements JsonResponse {

    ImageButton img1,img2;

    SharedPreferences sh;


    Button btn;

    String review;


    public  static  String cy_post,image,FileUploadAsync;

    final int CAMERA_PIC_REQUEST = 0, GALLERY_CODE = 201;


    public static String encodedImage = "", path = "";
    byte[] byteArray = null;
    private Uri mImageCaptureUri;

    Float rated;

    EditText t1;
    RatingBar ratingBar;
    private String rateString;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_review);

        sh = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

        t1 = findViewById(R.id.editTextTextMultiLine2);
        ratingBar = (RatingBar) findViewById(R.id.ratingBar);
        btn =  findViewById(R.id.button);

        btn .setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               float rating =  ratingBar.getRating();
               String ratingString = String.valueOf(rating);
               SharedPreferences.Editor e = sh.edit();
               e.putString("ratingString", ratingString);
               e.putFloat("rate",rating);
               e.commit();


                review=t1.getText().toString();
                sendAttach();


            }
        });

        img1 = findViewById(R.id.imageButton);

        img1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                selectImageOption();
            }
        });


    }
    private void sendAttach() {

        try {
            SharedPreferences sh = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

//            Toast.makeText(this, "saaaa"+byteArray, Toast.LENGTH_SHORT).show();
            String q = "http://" +sh.getString("ip","")+"/send_rating";

            Map<String, byte[]> aa = new HashMap<>();

            aa.put("image", byteArray);
            aa.put("id",sh.getString("login_id","").getBytes());
            aa.put("ratingString",sh.getString("ratingString","").getBytes());
            aa.put("review",review.getBytes());


//
            aa.put("place_id",Customize_places.plc_id.getBytes());
//            Toast.makeText(this, "vvvvv", Toast.LENGTH_SHORT).show();



//            FileUploadAsync fua = new FileUploadAsync(q);
            FileUploadAsync fua = new FileUploadAsync(q, Send_review.this);

            fua.json_response = (JsonResponse) Send_review.this;
            fua.execute(aa);
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "Exception upload : " + e, Toast.LENGTH_SHORT).show();
        }
    }
    private void selectImageOption() {


        final CharSequence[] items = {"Capture Photo", "Choose from Gallery", "Cancel"};

        AlertDialog.Builder builder = new AlertDialog.Builder(Send_review.this);
        builder.setTitle("Take Photo!");
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {

                if (items[item].equals("Capture Photo")) {
                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    //intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);

                    startActivityForResult(intent, CAMERA_PIC_REQUEST);

                } else if (items[item].equals("Choose from Gallery")) {
                    Intent i = new Intent(Intent.ACTION_PICK,android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    startActivityForResult(i, GALLERY_CODE);

                } else if (items[item].equals("Cancel")) {
                    dialog.dismiss();
                }
            }
        });
        builder.show();
    }

    @Override
    public void response(JSONObject jo) {

        try {
            String status = jo.getString("status");
            if (status.equalsIgnoreCase("success")){
                Toast.makeText(this, "Shared", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getApplicationContext(),View_places.class));
            }else {

                Toast.makeText(this, "Aww!!", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getApplicationContext(),View_places.class));
            }


        }catch (Exception e){
            Toast.makeText(this, e.toString(), Toast.LENGTH_SHORT).show();
        }



    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == GALLERY_CODE && resultCode == RESULT_OK && null != data) {

            mImageCaptureUri = data.getData();
            System.out.println("Gallery Image URI : " + mImageCaptureUri);
//               CropingIMG();

            Uri uri = data.getData();
            Log.d("File Uri", "File Uri: " + uri.toString());
            // Get the path
            //String path = null;
            try {
                path = FileUtils.getPath(this, uri);
//                path= FileUtils.getPath(this,uri);
                Toast.makeText(getApplicationContext(), "path : " + path, Toast.LENGTH_LONG).show();

                File fl = new File(path);
                Toast.makeText(getApplicationContext(), "fl : " +fl, Toast.LENGTH_LONG).show();

                int ln = (int) fl.length();

//                Toast.makeText(getApplicationContext(), "rrrrssss : "+ln , Toast.LENGTH_LONG).show();


                InputStream inputStream = new FileInputStream(fl);
//                Toast.makeText(getApplicationContext(), "rrrr : " , Toast.LENGTH_LONG).show();

                ByteArrayOutputStream bos = new ByteArrayOutputStream();
//                Toast.makeText(getApplicationContext(), "rrrr00 : " , Toast.LENGTH_LONG).show();

                byte[] b = new byte[ln];
//                Toast.makeText(getApplicationContext(), "rrrr11 : " , Toast.LENGTH_LONG).show();

                int bytesRead = 0;
//                Toast.makeText(getApplicationContext(), "rrrr22 : " , Toast.LENGTH_LONG).show();


                while ((bytesRead = inputStream.read(b)) != -1) {
//                    Toast.makeText(getApplicationContext(), "rrrr33 : " , Toast.LENGTH_LONG).show();

                    bos.write(b, 0, bytesRead);
                }
                inputStream.close();
                byteArray = bos.toByteArray();

                Bitmap bit = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
                img1.setImageBitmap(bit);

                String str = Base64.encodeToString(byteArray, Base64.DEFAULT);
                encodedImage = str;
//                sendAttach1();w
            } catch (Exception e) {
                Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_LONG).show();
            }
        } else if (requestCode == CAMERA_PIC_REQUEST && resultCode == Activity.RESULT_OK) {

            try {
                Bitmap thumbnail = (Bitmap) data.getExtras().get("data");
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                thumbnail.compress(Bitmap.CompressFormat.JPEG, 100, baos);
                img1.setImageBitmap(thumbnail);
                byteArray = baos.toByteArray();

                String str = Base64.encodeToString(byteArray, Base64.DEFAULT);
                encodedImage = str;
//                sendAttach1();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }
}