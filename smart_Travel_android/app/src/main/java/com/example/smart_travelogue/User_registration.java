package com.example.smart_travelogue;

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
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class User_registration extends AppCompatActivity implements JsonResponse {


    EditText e1,e2,e3,e4,e5,e6,e7;

    Button btn;


    ImageButton img;

    String fname,lname,place,phone,email,uname,psd;

    public  static  String cy_post,image,FileUploadAsync;

    final int CAMERA_PIC_REQUEST = 0, GALLERY_CODE = 201;


    public static String encodedImage = "", path = "";
    byte[] byteArray = null;
    private Uri mImageCaptureUri;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_registration);

        e1=findViewById(R.id.name);
        e2=findViewById(R.id.place);
        e3=findViewById(R.id.phone);
        e4=findViewById(R.id.email);
        e5=findViewById(R.id.uname);
        e6=findViewById(R.id.password);

        img=findViewById(R.id.image1);
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectImageOption();
            }
        });
        btn=findViewById(R.id.button3);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                fname = e1.getText().toString();
                place = e2.getText().toString();
                phone = e3.getText().toString();
                email = e4.getText().toString();
                uname = e5.getText().toString();
                psd = e6.getText().toString();

                if (fname.equalsIgnoreCase("")){
                    e1.setError("Enter the  Name");
                    e1.setFocusable(true);

               // }else if (place.matches("^[a-z A-Z]+$")) {
                //    e2.setError("Enter the Place");
                //    e2.setFocusable(true);
                }
                else if (phone.equalsIgnoreCase("") || phone.length()!=10) {
                    e3.setError("Enter the Contact Number");
                    e3.setFocusable(true);
                } else if (!email.matches("[a-zA-Z0-9._-]+@[a-z]+\\.[a-z]+")) {
                    e4.setError("Enter the Email Address");
                    e4.setFocusable(true);
                }
                else if (uname.equalsIgnoreCase("")) {
                    e5.setError("Enter the Username");
                    e5.setFocusable(true);
                } else if (psd.equalsIgnoreCase("")) {
                    e6.setError("Enter the Email Address");
                    e6.setFocusable(true);
                }else {
                    sendAttach();

                }


            }
        });

    }
    private void sendAttach() {

        try {
            SharedPreferences sh = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
//	            String uid = sh.getString("uid", "");




//                String q = "http://" + IpSetting.ip + "/smart city/apis.php";
            String q = "http://" +sh.getString("ip","")+"/user_reg";
//            Toast.makeText(getApplicationContext(), " qq"+q, Toast.LENGTH_LONG).show();
//            Log.d("hhttpp",q);
//            Toast.makeText(getApplicationContext(), "byteArray"+byteArray, Toast.LENGTH_LONG).show();



            Map<String, byte[]> aa = new HashMap<>();

            aa.put("image", byteArray);
//            Toast.makeText(getApplicationContext(), "byteArray"+byteArray, Toast.LENGTH_LONG).show();

            aa.put("id",sh.getString("user_id","").getBytes());
            aa.put("name",fname.getBytes());
            aa.put("place",place.getBytes());
            aa.put("phone",phone.getBytes());
            aa.put("email",email.getBytes());
            aa.put("uname",uname.getBytes());
            aa.put("password",psd.getBytes());
            aa.put("lati",Locationservice.lati.getBytes());
            aa.put("logi",Locationservice.logi.getBytes());

//            FileUploadAsync fua = new FileUploadAsync(q);
            FileUploadAsync fua = new FileUploadAsync(q, User_registration.this);
            fua.json_response = (JsonResponse) User_registration.this;
            fua.execute(aa);
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "Exception upload : " + e, Toast.LENGTH_SHORT).show();
        }
    }
    private void selectImageOption() {
        /*Android 10+ gallery code
        android:requestLegacyExternalStorage="true"*/

        final CharSequence[] items = {"Capture Photo", "Choose from Gallery", "Cancel"};

        AlertDialog.Builder builder = new AlertDialog.Builder(User_registration.this);
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

            String status=jo.getString("status");
            if (status.equalsIgnoreCase("success")){

                Toast.makeText(getApplicationContext(), "Registration Success", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getApplicationContext(),Login.class));
            }

        }
        catch (Exception e){

            Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_SHORT).show();
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
                img.setImageBitmap(bit);

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
                img.setImageBitmap(thumbnail);
                byteArray = baos.toByteArray();

                String str = Base64.encodeToString(byteArray, Base64.DEFAULT);
                encodedImage = str;
//                sendAttach1();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }
    public void onBackPressed()
    {
        // TODO Auto-generated method stub
        super.onBackPressed();
        Intent b=new Intent(getApplicationContext(),Login.class);
        startActivity(b);
    }
}