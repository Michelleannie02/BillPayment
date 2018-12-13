package com.example.mahe.billpayment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v4.app.TaskStackBuilder;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.Random;
import java.util.regex.Pattern;

/**
 * Created by Mahe on 6/22/2017.
 */

public class MobileBill extends AppCompatActivity {

    int randomNo;
    Bundle bundle;
    int temp = 1;
    String text="";

    private Spinner spinner;
    private static final String[]paths = {"Airtel", "Vodafone", "BSNL", "Tata Docomo", "Jio", "Aircel", "Reliance", "MTNL"};

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mobilebill);
        bundle = getIntent().getExtras();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        spinner = (Spinner)findViewById(R.id.spinner);
        Button button=(Button)findViewById(R.id.button4);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item,paths);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new MySpinnerSelectedListener());
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

    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }

   /** @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, BillPayment.class);
        startActivity(intent);
        MobileBill.this.finish();
    }**/


    public void GoToConfirmPayment(View view) {

        EditText Phone = (EditText) findViewById(R.id.phone);
        //EditText Service = (EditText) findViewById(R.id.Service);
        EditText Amount = (EditText) findViewById(R.id.Amount);
        Button button=(Button)findViewById(R.id.button4);
        String phone = Phone.getText().toString().trim();
        String amount = Amount.getText().toString().trim();
        float f = Float.parseFloat(amount);
        int pay = Math.round(f);

        //int pay = Integer.parseInt(amount);
        Bundle bundle1=new Bundle();

        int min = 100000;
        int max = 999999;
        Random r = new Random();

        randomNo = r.nextInt(max - min + 1) + min;

        if (Phone.getText().toString().trim().matches("") || Amount.getText().toString().trim().matches("")) {
            Toast.makeText(getApplicationContext(), "Enter the details", Toast.LENGTH_SHORT).show();
        }

        else if (!(isValidMobile(phone))){
            Toast.makeText(getApplicationContext(), "Invalid phone number!", Toast.LENGTH_SHORT).show();
        }

        else if (!(isValidPay(f))){
            Toast.makeText(getApplicationContext(), "Enter amount greater than or equal to 10!", Toast.LENGTH_SHORT).show();
        }
        else {
            bundle1.putString("Phone", Phone.getText().toString());
            bundle1.putString("Number", Phone.getText().toString());
           // bundle1.putString("Service",text.toString());
            bundle1.putInt("Amount", Integer.parseInt(Amount.getText().toString()));
            bundle1.putInt("OrderID", randomNo);
            bundle1.putInt("Category", temp);
            bundle1.putString("Username", bundle.getString("Username"));
            bundle1.putString("Password", bundle.getString("Password"));
            bundle1.putString("From", "BillPayment");

            Intent intent = new Intent(this, ConfirmPayment.class);
            intent.putExtra("Service", String.valueOf((spinner.getSelectedItem())));
            intent.putExtras(bundle1);
            startActivity(intent);
           // MobileBill.this.finish();
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

    private boolean isValidPay(float pay) {
        boolean check=false;
        if(pay < 10.0 ) {
            check = false;

        } else {
            check = true;
        }

        return check;
    }
}
