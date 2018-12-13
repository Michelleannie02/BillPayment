package com.example.mahe.billpayment;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v4.app.NavUtils;
import android.support.v4.app.TaskStackBuilder;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.Random;

/**
 * Created by Mahe on 6/22/2017.
 */

public class DatacardBill extends AppCompatActivity {

    Bundle bundle;
    int randomNo;
    int temp = 2;

    private Spinner spinner3;
    private static final String[]paths = {"Airtel", "Vodafone", "BSNL", "Tata Docomo", "Jio", "Aircel", "Reliance", "MTNL"};


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datacardbill);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        bundle = getIntent().getExtras();

        spinner3 = (Spinner)findViewById(R.id.spinner3);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item,paths);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner3.setAdapter(adapter);
        spinner3.setOnItemSelectedListener(new MySpinnerSelectedListener());
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
    /**@Override
    public void onBackPressed() {
        Intent intent = new Intent(this, BillPayment.class);
        startActivity(intent);
        DatacardBill.this.finish();
    }**/
    public void GoToConfirmPayment(View view) {
        EditText Phone = (EditText) findViewById(R.id.dnumber);
        //EditText Service = (EditText) findViewById(R.id.dservice);
        EditText Amount = (EditText) findViewById(R.id.damount);
        EditText Phone2 = (EditText) findViewById(R.id.dphone);
        String phone = Phone2.getText().toString().trim();
        String phone2 = Phone.getText().toString().trim();
        String amount = Amount.getText().toString().trim();
        float f = Float.parseFloat(amount);
        int pay = Math.round(f);
        Bundle bundle1=new Bundle();

        int min = 100000;
        int max = 999999;
        Random r = new Random();

        randomNo = r.nextInt(max - min + 1) + min;

        if (Phone2.getText().toString().trim().matches("") || Phone.getText().toString().trim().matches("")  || Amount.getText().toString().trim().matches("")) {
            Toast.makeText(getApplicationContext(), "Enter the details", Toast.LENGTH_SHORT).show();
        }
        else if (!(isValidMobile(phone2))){
            Toast.makeText(getApplicationContext(), "Invalid datacard number! Enter 10 digit number.", Toast.LENGTH_SHORT).show();
        }
        else if (!(isValidMobile(phone))){
            Toast.makeText(getApplicationContext(), "Invalid phone number!", Toast.LENGTH_SHORT).show();
        }
        else if (!(isValidPay(pay))){
            Toast.makeText(getApplicationContext(), "Enter amount greater than or equal to 10!", Toast.LENGTH_SHORT).show();
        }
        else {
            bundle1.putString("Phone", Phone.getText().toString());
            bundle1.putString("Number", Phone2.getText().toString());
           // bundle1.putString("Service", Service.getText().toString());
            bundle1.putInt("Amount", Integer.parseInt(Amount.getText().toString()));
            bundle1.putInt("OrderID", randomNo);
            bundle1.putInt("Category", temp);
            bundle1.putString("Username", bundle.getString("Username"));
            bundle1.putString("Password", bundle.getString("Password"));
            bundle1.putString("From", "BillPayment");

            Intent intent = new Intent(this, ConfirmPayment.class);
            intent.putExtra("Service", String.valueOf((spinner3.getSelectedItem())));
            intent.putExtras(bundle1);
            startActivity(intent);
           // DatacardBill.this.finish();
        }
    }
    private boolean isValidMobile(String phone) {
        boolean check=false;
        if(phone.length() != 10) {
            check = false;

        } else {
            check = true;
        }

        return check;
    }
    private boolean isValidPay(int pay) {
        boolean check=false;
        if(pay <10 ) {
            check = false;

        } else {
            check = true;
        }

        return check;
    }
}
