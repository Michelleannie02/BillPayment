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
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Mahe on 6/22/2017.
 */

public class PaymentDetails extends AppCompatActivity {

    Bundle bundle = new Bundle();

    private Spinner spinner4;
    private static final String[]paths = {"Credit Card", "Debit Card"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paymentdetails);
        bundle = getIntent().getExtras();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        spinner4 = (Spinner)findViewById(R.id.spinner4);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item,paths);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner4.setAdapter(adapter);
        spinner4.setOnItemSelectedListener(new MySpinnerSelectedListener());
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                Intent intent = new Intent(this, ConfirmPayment.class);
                intent.putExtras(bundle);
                startActivity(intent);
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public static boolean isValidDate(String inDate) {

        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/yy");
        dateFormat.setLenient(false);
        Date d=null;
        Date d1=null;
        String today= getToday("MM/yy");
        try {

            d = dateFormat.parse(inDate);
            d1 = dateFormat.parse(today);
            if(d1.compareTo(d) <= 0){
                return true;
            }else{//expired
                return false;
            }
        } catch (ParseException e) {
            e.printStackTrace();
            return true;
        }
       /** try {
            dateFormat.parse(inDate.trim());
        } catch (ParseException pe) {
            return false;
        }
        return true;**/
    }

    public static String getToday(String format){
        Date date = new Date();
        return new SimpleDateFormat(format).format(date);
    }


    private Pattern pattern;
    private Matcher matcher;

    private static final String DATE_PATTERN =
            "^(0?[1-9]|1[012])\\/\\d{2}$";


    public void GoToCheck(View view) {
        int flag = 1;

        final EditText etno, etname, etdate, etcvv;

        etno = (EditText) findViewById(R.id.cardnum);
        etname = (EditText) findViewById(R.id.name);
        etdate = (EditText) findViewById(R.id.date);
        etcvv = (EditText) findViewById(R.id.cvv);

        if (etno.getText().toString().trim().equals("") || etname.getText().toString().trim().equals("") || etdate.getText().toString().trim().equals("") || etcvv.getText().toString().trim().equals("")) {
            Toast.makeText(getApplicationContext(), "Please fill in all the details", Toast.LENGTH_SHORT).show();
        } else {
            /**if (etno.getText().toString().trim().length() == 16) {
                if (etno.getText().toString().trim().charAt(14) == '4') {
                    bundle.putString("CardType", "VISA");
                } else if (etno.getText().toString().trim().charAt(14) == '5') {
                    if (etno.getText().toString().trim().charAt(15) >= '1' && etno.getText().toString().trim().charAt(15) <= '5') {
                        bundle.putString("CardType", "MasterCard");
                    } else if (etno.getText().toString().trim().charAt(15) == '8' || etno.getText().toString().trim().charAt(15) == '0') {
                        bundle.putString("CardType", "MaestroCard");
                    }
                } else if (etno.getText().toString().trim().charAt(14) == '6') {
                    if (etno.getText().toString().trim().charAt(15) == '2' || etno.getText().toString().trim().charAt(15) == '3' || etno.getText().toString().trim().charAt(15) == '7') {
                        bundle.putString("CardType", "MaestroCard");
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "Card No. Incorrect", Toast.LENGTH_SHORT).show();
                    flag = 0;
                }
            }**/
            if (etno.getText().toString().trim().length() != 16) {
                Toast.makeText(getApplicationContext(), "Card No. Incorrect", Toast.LENGTH_SHORT).show();
                flag = 0;
            }

            if (flag == 1) {

                if (isValidDate(etdate.getText().toString().trim()) == false) {
                    Toast.makeText(getApplicationContext(), "Enter correct expiration date!", Toast.LENGTH_SHORT).show();
                    flag = 0;
                }
                matcher = Pattern.compile(DATE_PATTERN).matcher(etdate.getText().toString().trim());

                if (!matcher.matches()) {
                    flag = 0;
                    Toast.makeText(getApplicationContext(), "Invalid Date", Toast.LENGTH_SHORT).show();
                }
            }

            if (flag == 1) {
                if (etcvv.getText().toString().trim().length() != 3) {
                    flag = 0;
                    Toast.makeText(getApplicationContext(), "Enter a valid CVV no.", Toast.LENGTH_SHORT).show();
                }
            }

            if (flag == 1) {

                if (bundle.getString("From").matches("BillPayment")) {
                    Intent intent = new Intent(this, Check.class);
                    bundle.putString("CardNo", etno.getText().toString().trim());
                    intent.putExtra("Service", String.valueOf((spinner4.getSelectedItem())));
                    intent.putExtras(bundle);
                    startActivity(intent);
                    // PaymentDetails.this.finish();
                }

            }
        }
    }
}


