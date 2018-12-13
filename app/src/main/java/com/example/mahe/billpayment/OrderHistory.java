package com.example.mahe.billpayment;

import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.database.CursorIndexOutOfBoundsException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v4.app.TaskStackBuilder;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Set;

import android.view.View;

/**
 * Created by Mahe on 6/23/2017.
 */

public class OrderHistory extends ListActivity{

    Bundle bundle;
    private ArrayList<String> results = new ArrayList<String>();
    private String tableName = DbRegister.T_Name;
    private SQLiteDatabase newDB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bundle=getIntent().getExtras();

        try {
            openAndQueryDatabase();

            displayResultList();
        }
        catch(CursorIndexOutOfBoundsException e){
            results.add("No orders to display!");
            displayResultList();
        }
    }


    private void displayResultList() {
        TextView tView = new TextView(this);
        tView.setText("Order History");
        getListView().addHeaderView(tView);

        setListAdapter(new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, results));
        getListView().setTextFilterEnabled(true);

    }

    private void openAndQueryDatabase() {
        String username = bundle.getString("Username");
        int num=0;
        try {
            DbRegister db = new DbRegister(this.getApplicationContext());
            newDB = db.getWritableDatabase();
            Cursor c = db.getAllTransactions(username);

                c.moveToFirst();
                    do {

                        num = num+1;
                        String orderID = c.getString(c.getColumnIndex("_id"));
                        String phone = c.getString(c.getColumnIndex("Number"));
                       String amount = c.getString(c.getColumnIndex("_amt"));
                        String service = c.getString(c.getColumnIndex("Service"));
                        String created_at = c.getString(c.getColumnIndex("created_at"));
                        //results.add("Order Id: "+ orderID);
                        results.add(num + "). " + created_at + ",   " + "                           Order ID: " + orderID  + " ,    Service: " + service + " ,      Service Number: " + phone + " ,   Amount: Rs."+ amount);
                    }while (c.moveToNext());


        } catch (SQLiteException se ) {
            Log.e(getClass().getSimpleName(), "Could not create or Open the database");
        } finally {

            newDB.close();
        }

    }
}
