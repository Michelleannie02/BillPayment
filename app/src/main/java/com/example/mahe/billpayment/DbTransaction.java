package com.example.mahe.billpayment;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Mahe on 6/23/2017.
 */

public class DbTransaction extends SQLiteOpenHelper {
    private static final int Db_Version = 1;
    private static final String Db_Name = "TransactionRegister";
    public static final String T_Name = "Transactions";
    private static final String order_id = "_id";
    private static final String F_Name = "First_Name";
    private static final String L_Name = "Last_Name";
    private static final String Service = "Service";
    private static final String Amt = "_amt";


    SQLiteDatabase db = this.getReadableDatabase();
    Cursor c;

    public DbTransaction(Context context) {
        super(context, Db_Name, null, Db_Version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        try {
            String CreateQuery = "CREATE TABLE " + T_Name + "( " + order_id + " INTEGER PRIMARY KEY, " + F_Name + " TEXT, " + L_Name + " TEXT, " + Service + " TEXT, " + Amt + " INTEGER)";
            db.execSQL(CreateQuery);
        } catch (SQLException se) {
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + T_Name);
        onCreate(db);
    }

    public void addTransaction(Transaction t) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(order_id, t.getOrder_id());
        values.put(F_Name, t.getFname());
        values.put(L_Name, t.getLname());
        values.put(Service, t.getService());
        values.put(Amt, t.getAmount());


        try {
            db.insert(T_Name, null, values);
            db.close();
        } catch (SQLException se) {
        }
    }

    public Cursor getTransaction(int order_id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM " + T_Name + " WHERE " +
                order_id + "=?", new String[]{Integer.toString(order_id)});
        return res;
    }
    public Cursor getAllTransactions() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "SELECT * FROM " + T_Name, null );
        return res;
    }
}






