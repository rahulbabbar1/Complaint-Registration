package com.example.pawan.complaintregistration;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;

public class AdDetails extends AppCompatActivity {
    EditText e1,e2,e3,e4,e5,e6;
    Bitmap img;
    byte[]  imageInByte;
    Button b;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ad_details);
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);



//        e1 = (EditText) findViewById(R.id.editText4);
//        e2 = (EditText) findViewById(R.id.editText5);
//        e3 = (EditText) findViewById(R.id.editText6);
//        e4 = (EditText) findViewById(R.id.editText7);
//        e5 = (EditText) findViewById(R.id.editText8);
//        e6 = (EditText) findViewById(R.id.editText9);
         b =(Button) findViewById(R.id.submit);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });

    }
    public void login(){
        String street = e1.getText().toString();
        String colony = e2.getText().toString();
        String city = e3.getText().toString();
        String zipcode = e4.getText().toString();
        String phoneno = e5.getText().toString();
        String complaintdetails = e6.getText().toString();

        // try {

        img = (Bitmap) getIntent().getExtras().get("dhankher");
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        img.compress(Bitmap.CompressFormat.PNG, 100, stream);
        imageInByte = stream.toByteArray();


    /*  }catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
      } */
        // String image = Base64.encodeToString(imageInByte, Base64.NO_WRAP);
        String image = Base64.encodeToString(imageInByte, Base64.DEFAULT);


        String type = "login";
        BackgroundWorker backgroundWorker = new BackgroundWorker(this);
        try{
            ProgressDialog progress = new ProgressDialog(this);
            progress.setMessage("Uploading 🙂 ");
            progress.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
            progress.setIndeterminate(true);
            progress.show();
            String result=backgroundWorker.execute(type,street,colony,city,zipcode,phoneno,complaintdetails,image).get();
         /*   JSONObject jsonObject = new JSONObject(result);
            Log.d("abc", "login() called with: " +jsonObject.get("count")+ "");
            for(int i=0;i<Integer.parseInt(jsonObject.get("count").toString());i++){
                JSONObject inside=jsonObject.getJSONObject(String.valueOf(i));
                Log.d("abc", inside.getString("street") + " " + inside.getString("colony") + " " + inside.getString("city") + " " + inside.getString("zipcode") + " " +
                        inside.getString("phoneno") + " " + inside.getString("complaintdetails") + " " + inside.getString("id") + " " + inside.getString("image"));
            } */
            Log.d("After query", "login() called with: " + result + "");
            progress.cancel();
        }
        catch (java.lang.InterruptedException e){

        }
        catch (java.util.concurrent.ExecutionException e){

      //  } catch (JSONException e) {
      //      e.printStackTrace();
        }
    }
}

