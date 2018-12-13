package com.example.mahe.billpayment;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v4.app.TaskStackBuilder;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Mahe on 6/22/2017.
 */

public class Successful extends AppCompatActivity {

    Bundle bundle;
    private SQLiteDatabase newDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_successful);
        bundle = getIntent().getExtras();
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);

        //int num = 0;


            TextView t1, t2, t3, t4,t5;

            t1 = (TextView) findViewById(R.id.order_text);
            t2 = (TextView) findViewById(R.id.amount_text);
            t3 = (TextView) findViewById(R.id.phone_text);
            t4 = (TextView) findViewById(R.id.service_text);
           // t5 = (TextView) findViewById(R.id.blah);

            String fphone;
            Integer famount;
            Integer forder;
            Integer fcategory;
            String service = "";

            fphone = bundle.getString("Phone");
            famount = bundle.getInt("Amount");
            forder = bundle.getInt("OrderID");
            fcategory = bundle.getInt("Category");

            if (fcategory == 1) {
                service = "Mobile";
            } else if (fcategory == 2) {
                service = "Datacard";
            } else if (fcategory == 3) {
                service = "Landline";
            }
            try {

                DbRegister db = new DbRegister(this);
                newDB = db.getWritableDatabase();
                String username = bundle.getString("Username");
                Cursor c = db.getTheUser(username);
                c.moveToFirst();
                String fname = c.getString(c.getColumnIndex("First_Name"));
                String lname = c.getString(c.getColumnIndex("Last_Name"));

                t3.setText("Phone No.: " + fphone);
                t2.setText("Amount: " + famount);
                t1.setText("Order ID: " + forder);
                t4.setText("Category: " + service);

                Transaction t = new Transaction(username, fname, lname, service, fphone, forder, famount);
                db.addTransaction(t);

               // num = db.getTransactionCount(username);



            } catch (SQLiteException se) {
                Log.e(getClass().getSimpleName(), "Could not create or Open the database");
            } finally {

                newDB.close();
            }
        //t5.setText("Orders: " + num);
    }

    @Override
    public void onBackPressed() {
        Toast.makeText(getApplicationContext(), "Cannot go back to previous page.", Toast.LENGTH_LONG).show();
    }

    public void GoToMain(View view)
    {
        Intent intent=new Intent(this,MainActivity.class);
        //intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();
    }

    public void GoToBillPayment(View view)
    {
        Intent intent=new Intent(this,BillPayment.class);
        intent.putExtras(bundle);
        startActivity(intent);
       // Successful.this.finish();
    }
}
