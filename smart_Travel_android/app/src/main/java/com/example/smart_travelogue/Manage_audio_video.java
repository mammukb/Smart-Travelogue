package com.example.smart_travelogue;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Manage_audio_video extends AppCompatActivity implements JsonResponse {

    private static final int PICK_FILE_REQUEST = 1;

    private ImageButton uploadButton;
    private TextView fileTextView;

    private String loginId;
    private SharedPreferences sharedPreferences;
    private byte[] fileByteArray;
    EditText e1,e2;
    String title,desc;

    String [] place_id,place_name,val;

    Spinner spinner;

    public  static  String plc_id;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_audio_video);

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

        uploadButton = findViewById(R.id.btUpload);
        fileTextView = findViewById(R.id.tvFile);
        e1 = findViewById(R.id.complaint_tt);
        e2 = findViewById(R.id.desc);

        fileTextView.setOnClickListener(view -> selectFileOption());

//        uploadButton.setOnClickListener(arg0 -> uploadFile());

        uploadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                title=e1.getText().toString();
                desc=e2.getText().toString();
                if (title.trim().isEmpty()) {
                    e1.setFocusable(true);
                    e1.setError("Enter Title");
                }
                else{
                    e1.setBackgroundColor(Color.TRANSPARENT);
                    uploadFile();
                }

            }
        });



        spinner = findViewById(R.id.spinner);

        JsonReq JR = new JsonReq();
        JR.json_response = Manage_audio_video.this;
        String q = "/dropdwonviewplace";
        q = q.replace(" ", "%20");
        JR.execute(q);
    }

    private void uploadFile() {
        loginId = sharedPreferences.getString("login_id", "");
        try {
            String uploadUrl = "http://" + IPSetting.ip + "/Multi_file_upload";
            String fileType = sharedPreferences.getString("file_type", "");
            Toast.makeText(this, "fff"+fileByteArray, Toast.LENGTH_SHORT).show();

            Map<String, byte[]> params = new HashMap<>();
            params.put("file", fileByteArray);
            params.put("fileType", fileType.getBytes());
            params.put("plc_id", plc_id.getBytes());
//            params.put("log_id", sharedPreferences.getString("login_id","").getBytes());
            params.put("title", title.getBytes());
            params.put("desc", desc.getBytes());
            params.put("log_id", sharedPreferences.getString("trav_id","").getBytes());

//            FileUploadAsync fileUploadAsync = new FileUploadAsync(uploadUrl);
//            FileUploadAsync fileUploadAsync = new FileUploadAsync(uploadUrl);
            FileUploadAsync fileUploadAsync = new FileUploadAsync(uploadUrl, Manage_audio_video.this);


            fileUploadAsync.json_response = Manage_audio_video.this;
            fileUploadAsync.execute(params);
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "Exception upload: " + e, Toast.LENGTH_SHORT).show();
        }
    }

    private void startFilePicker(String mimeType) {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType(mimeType);
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        startActivityForResult(Intent.createChooser(intent, "Select File"), PICK_FILE_REQUEST);
    }

    private void selectFileOption() {
        final CharSequence[] items = {"Upload Image",// "Upload Video", "Upload Audio",
                 "Cancel"};
        AlertDialog.Builder builder = new AlertDialog.Builder(Manage_audio_video.this);
        builder.setTitle("Select File");
        builder.setItems(items, (dialog, item) -> {
            if (items[item].equals("Upload Image")) {
                setFileTypeAndStartPicker("jpg");
            } else if (items[item].equals("Upload Video")) {
                setFileTypeAndStartPicker("mp4");
            } else if (items[item].equals("Upload Audio")) {
                setFileTypeAndStartPicker("mp3");
            } else if (items[item].equals("Cancel")) {
                dialog.dismiss();
            }
        });
        builder.show();
    }

    private void setFileTypeAndStartPicker(String fileType) {
        SharedPreferences.Editor ed = sharedPreferences.edit();
        ed.putString("file_type", fileType);
        ed.apply();
        startFilePicker(getMimeType(fileType));
    }

    private String getMimeType(String fileType) {
        // Provide appropriate MIME types based on file extensions
        switch (fileType) {
            case "jpg":
                return "image/*";
            case "mp4":
                return "video/*";
            case "mp3":
                return "audio/*";
            default:
                return "*/*";
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_FILE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            Uri fileUri = data.getData();
            try {
                InputStream inputStream = getContentResolver().openInputStream(fileUri);
                ByteArrayOutputStream bos = new ByteArrayOutputStream();
                byte[] buffer = new byte[1024];
                int bytesRead;
                try {
                    while ((bytesRead = inputStream.read(buffer)) != -1) {
                        bos.write(buffer, 0, bytesRead);
                    }
                } finally {
                    inputStream.close(); // Ensure the input stream is closed
                }

                if (bos.size() > 0) {
                    fileByteArray = bos.toByteArray();
                    Log.d("fileByteArray", String.valueOf(fileByteArray));
                    Toast.makeText(getApplicationContext(), "File Selected", Toast.LENGTH_SHORT).show();
                }
            } catch (Exception e) {
                e.printStackTrace();
                Toast.makeText(getApplicationContext(), "Error selecting file", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void response(JSONObject jo) {
        try {
            String method = jo.getString("method");

            if (method.equalsIgnoreCase("Multi_file_upload")) {
                String status = jo.getString("status");

                if (status.equalsIgnoreCase("Success")) {
                    Toast.makeText(getApplicationContext(), "File uploaded successfully", Toast.LENGTH_LONG).show();
                    startActivity(new Intent(getApplicationContext(),User_home.class));
                } else {
                    Toast.makeText(getApplicationContext(), "Upload failed", Toast.LENGTH_LONG).show();
                }
            }if(method.equalsIgnoreCase("dropdwonviewplace")){
                String status=jo.getString("status");
                if (status.equalsIgnoreCase("success")){

                    JSONArray ja1=(JSONArray)jo.getJSONArray("data");
                    Toast.makeText(this, "aaa", Toast.LENGTH_SHORT).show();
                    place_id = new String[ja1.length()];
                    place_name = new String[ja1.length()];

//                    val = new String[ja1.length()];

                    for(int i = 0;i<ja1.length();i++)
                    {

                        place_id[i] = ja1.getJSONObject(i).getString("place_id");
                        place_name[i] = ja1.getJSONObject(i).getString("place_name");

//                        val[i] = "place_name: : "+place_id[i]+"\nDate : " +date[i] ;
                    }
                    ArrayAdapter<String> ar=new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_list_item_1,place_name);
                    spinner.setAdapter(ar);
                    spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            plc_id=place_id[position];


                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }
                    });




                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(getApplicationContext(), "Error: " + e, Toast.LENGTH_LONG).show();
        }
    }
}
