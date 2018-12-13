package com.example.mahe.billpayment;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.NavUtils;
import android.support.v4.app.TaskStackBuilder;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.telephony.SmsManager;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

/**
 * Created by Mahe on 6/23/2017.
 */

public class Check extends AppCompatActivity {

    Bundle bundle;
    int randomNo;
    private static final int MY_PERMISSIONS_REQUEST_SEND_SMS = 1;
    private static final int PERMISSION_REQUEST_CODE = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check);
        bundle=getIntent().getExtras();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        int permissionCheck = ContextCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS);
        TextView t1,t2;
        int i;

        t1=(TextView)findViewById(R.id.Sername);
        t2=(TextView)findViewById(R.id.cardhide);

        String Service=bundle.getString("Service");
        Service.toUpperCase();
        t1.setText("Service Name: "+Service);

        String CardNo=bundle.getString("CardNo");

       // t2.setText("Card No.: " + CardNo);

        String star = "**** **** **** " + CardNo.substring(CardNo.length() - 4);
        t2.setText("Card No.: " + star);

        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.SEND_SMS)
                != PackageManager.PERMISSION_GRANTED) {

            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.SEND_SMS)) {


            } else {

                String[] permissions = {Manifest.permission.SEND_SMS};

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    requestPermissions(permissions, PERMISSION_REQUEST_CODE);
                }
                // No explanation needed, we can request the permission.

                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.SEND_SMS},
                        MY_PERMISSIONS_REQUEST_SEND_SMS);

            }
        }


        String number = bundle.getString("Number");
        int min = 100000;
        int max = 999999;
        Random r = new Random();

        randomNo = r.nextInt(max - min + 1) + min;

        SmsManager smsManager = SmsManager.getDefault();
        smsManager.sendTextMessage(number, null, String.valueOf(randomNo), null, null);
        Toast.makeText(getApplicationContext(), "Message Sent", Toast.LENGTH_LONG).show();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                Intent intent = new Intent(this, PaymentDetails.class);
                intent.putExtras(bundle);
                startActivity(intent);
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
   /** @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, PaymentDetails.class);
        startActivity(intent);
        Check.this.finish();
    }**/

   @Override
   public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
       switch (requestCode) {
           case MY_PERMISSIONS_REQUEST_SEND_SMS: {
               // If request is cancelled, the result arrays are empty.
               if (grantResults.length > 0
                       && grantResults[0] == PackageManager.PERMISSION_GRANTED) {


                   // permission was granted, yay! Do the
                   // contacts-related task you need to do.

               } else {

                   Toast.makeText(getApplicationContext(), "Please enable SMS permissions for this APP!", Toast.LENGTH_LONG).show();

                   // permission denied, boo! Disable the
                   // functionality that depends on this permission.
               }
               return;
           }
       }
   }
    public void SendOtp(View view) {


        //SmsManager smsManager = SmsManager.getDefault();
        String number = bundle.getString("Number");
        int min = 100000;
        int max = 999999;
        Random r = new Random();

        randomNo = r.nextInt(max - min + 1) + min;

        SmsManager smsManager = SmsManager.getDefault();
        smsManager.sendTextMessage(number, null, String.valueOf(randomNo), null, null);
        Toast.makeText(getApplicationContext(), "Message Sent", Toast.LENGTH_LONG).show();
    }


    public void GoToSuccessful(View view)
    {
        EditText et=(EditText)findViewById(R.id.otpet);
        String otp=et.getText().toString().trim();

        if (otp.matches(""))
        {
            Toast.makeText(getApplicationContext(),"Enter OTP",Toast.LENGTH_SHORT).show();
        }
        else if (otp.matches(String.valueOf(randomNo).toString().trim()))
        {
            String number = bundle.getString("Phone");
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(number, null, "Transaction Successful", null, null);
            Intent intent=new Intent(this,Successful.class);
            intent.putExtras(bundle);
            startActivity(intent);
            Check.this.finish();
        }
        else
        {
            Toast.makeText(getApplicationContext(),"Enter the correct OTP",Toast.LENGTH_SHORT).show();
        }
    }
}
