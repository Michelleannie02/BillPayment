package com.example.mahe.billpayment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v4.app.TaskStackBuilder;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by Mahe on 6/22/2017.
 */

public class BillPayment extends AppCompatActivity {

    Bundle bundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_billpayment);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);

        bundle=getIntent().getExtras();
    }
    @Override
    public void onBackPressed() {
        Toast.makeText(getApplicationContext(), "Cannot go back to previous page.", Toast.LENGTH_LONG).show();
    }


    public void GoToMain(View view)
    {
        Intent intent=new Intent(this,MainActivity.class);
       // intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();
    }

    public void GoToMobileBill(View view) {

            Intent intent = new Intent(this, MobileBill.class);
            bundle.putString("From", "MobileBill");
            intent.putExtras(bundle);
            startActivity(intent);
           // BillPayment.this.finish();

    }

    public void GoToOrderHistory(View view){
        Intent intent = new Intent(this, OrderHistory.class);
        intent.putExtras(bundle);
        startActivity(intent);
        // Settings.this.finish();
    }

    public void GoToLandlineBill(View view) {


            Intent intent = new Intent(this, LandlineBill.class);
            bundle.putString("From", "BillPayment");
            intent.putExtras(bundle);
            startActivity(intent);
          //  BillPayment.this.finish();

    }

    public void GoToDatacardBill(View view) {


            Intent intent = new Intent(this, DatacardBill.class);
            bundle.putString("From", "BillPayment");
            intent.putExtras(bundle);
            startActivity(intent);
          //  BillPayment.this.finish();

    }
    public void GoToSettings(View view)
    {
        Intent intent= new Intent(this,Settings.class);
        bundle.putString("From", "Settings");
        intent.putExtras(bundle);
        startActivity(intent);
      //  BillPayment.this.finish();
    }
}
