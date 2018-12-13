package com.example.mahe.billpayment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v4.app.TaskStackBuilder;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.Random;

import static com.example.mahe.billpayment.R.id.spinner;

/**
 * Created by Mahe on 6/22/2017.
 */

public class LandlineBill extends AppCompatActivity {

    Bundle bundle;
    int randomNo;
    int temp = 3;

    private Spinner spinner2;
    private static final String[]paths = {"Airtel", "BSNL", "Reliance", "MTNL", "Tata Indicom"};


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landlinebill);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        bundle = getIntent().getExtras();

        spinner2 = (Spinner)findViewById(R.id.spinner2);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item,paths);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner2.setAdapter(adapter);
        spinner2.setOnItemSelectedListener(new MySpinnerSelectedListener());
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
        LandlineBill.this.finish();
    }**/
    public void GoToConfirmPayment(View view) {
        EditText Phone = (EditText) findViewById(R.id.tnumber);
        EditText Phone2 = (EditText) findViewById(R.id.tphone);
       // EditText Service = (EditText) findViewById(R.id.tservice);
        EditText Amount = (EditText) findViewById(R.id.tamount);
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

        if (Phone2.getText().toString().trim().matches("") || Phone.getText().toString().trim().matches("") || Amount.getText().toString().trim().matches("")) {
            Toast.makeText(getApplicationContext(), "Enter the details", Toast.LENGTH_SHORT).show();
        }
        else if (!(isValidMobile(phone2))){
            Toast.makeText(getApplicationContext(), "Invalid landline number! Enter 10 digit number.", Toast.LENGTH_SHORT).show();
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
            //bundle1.putString("Service", Service.getText().toString());
            bundle1.putInt("Amount", Integer.parseInt(Amount.getText().toString()));
            bundle1.putInt("OrderID", randomNo);
            bundle1.putInt("Category", temp);
            bundle1.putString("Username", bundle.getString("Username"));
            bundle1.putString("Password", bundle.getString("Password"));
            bundle1.putString("From", "BillPayment");

            Intent intent = new Intent(this, ConfirmPayment.class);
            intent.putExtra("Service", String.valueOf((spinner2.getSelectedItem())));

            intent.putExtras(bundle1);
            startActivity(intent);
          //  LandlineBill.this.finish();
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
