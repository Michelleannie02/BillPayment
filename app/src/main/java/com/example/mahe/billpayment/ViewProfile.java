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

/**
 * Created by Mahe on 6/23/2017.
 */

public class ViewProfile extends AppCompatActivity {


    Bundle bundle;
    private SQLiteDatabase newDB;

   /** @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, BillPayment.class);
        intent.putExtras(bundle);
        startActivity(intent);
        ViewProfile.this.finish();
    }**/

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewprofile);
        bundle = getIntent().getExtras();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        TextView t1, t2, t3;

            t1 = (TextView) findViewById(R.id.zname);
            t2 = (TextView) findViewById(R.id.zemail);
            t3 = (TextView) findViewById(R.id.zusername);

            String fname="";
            String lname="";
            String email="";
            String username;

            username = bundle.getString("Username");

             try {
                DbRegister db = new DbRegister(this.getApplicationContext());
                newDB = db.getWritableDatabase();
                 /** fname = db.getFName(username);
                  lname = db.getLName(username);
                  email = db.getEmail(username);**/

                 Cursor c = db.getTheUser(username);
                 c.moveToFirst();
                 fname = c.getString(c.getColumnIndex("First_Name"));
                 lname = c.getString(c.getColumnIndex("Last_Name"));
                 email = c.getString(c.getColumnIndex("Email_id"));


             } catch (SQLiteException se ) {
                Log.e(getClass().getSimpleName(), "Could not create or Open the database");
            } finally {

                newDB.close();
            }


            t1.setText("Name: " + fname + " " + lname );
            t2.setText("Email: " + email);
            t3.setText("Username: " + username);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                Intent intent = new Intent(this, Settings.class);
                intent.putExtras(bundle);
                startActivity(intent);
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }



    public void GoToBillPayment(View view){
        Intent intent = new Intent(this, BillPayment.class);
        intent.putExtras(bundle);
        startActivity(intent);
        ViewProfile.this.finish();
    }
}
