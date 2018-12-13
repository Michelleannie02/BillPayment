package com.example.mahe.billpayment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v4.app.TaskStackBuilder;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

/**
 * Created by Mahe on 6/22/2017.
 */

public class ConfirmPayment extends AppCompatActivity {

    Bundle bundle= new Bundle();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmpayment);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        bundle = getIntent().getExtras();

        if (bundle.getString("From").matches("BillPayment")) {
            TextView t1, t2, t3;

            t1 = (TextView) findViewById(R.id.FPhone);
            t2 = (TextView) findViewById(R.id.FService);
            t3 = (TextView) findViewById(R.id.FAmount);

            String fphone;
            Integer famount;
            String fservice;
            Integer fcategory;
            String cat="";

            fphone = bundle.getString("Phone");
            famount = bundle.getInt("Amount");
            fcategory = bundle.getInt("Category");

            //fservice = bundle.getString("Service");
            fservice= getIntent().getStringExtra("Service");
            bundle.putString("Service", fservice.toString());

            if (fcategory == 1) {
                cat = "Mobile";
            } else if (fcategory == 2) {
                cat = "Datacard";
            } else if (fcategory == 3) {
                cat = "Landline";
            }

            t1.setText(cat+ " No.: " + fphone);
            t2.setText("Service: " + fservice);
            t3.setText("Amount: " + famount);
        }

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                Intent intent = new Intent(this, BillPayment.class);
                intent.putExtras(bundle);
                startActivity(intent);
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

  /**  @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, BillPayment.class);
        startActivity(intent);
        ConfirmPayment.this.finish();
    }**/

    public void GoToPaymentDetails(View view)
    {
        Intent intent= new Intent(this,PaymentDetails.class);
        intent.putExtras(bundle);
        startActivity(intent);
       // ConfirmPayment.this.finish();
    }
}
