package com.example.mahe.billpayment;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.CursorIndexOutOfBoundsException;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Mahe on 6/22/2017.
 */

public class DbRegister extends SQLiteOpenHelper {

    private static final int Db_Version=3;
    private static final String Db_Name="UserRegister";
    public static final String U_Name="Users";
    //common columns
    private static final String F_Name="First_Name";
    private static final String L_Name="Last_Name";

    //user table
    private static final String Email="Email_id";
    private static final String User_Name="User_Name";
    private static final String Password="PassWord";

    //transaction table
    public static final String T_Name = "Transactions";
    private static final String order_id = "_id";
    private static final String Service = "Service";
    private static final String Number = "Number";
    private static final String Amt = "_amt";
    private static final String created_at = "created_at";

    SQLiteDatabase db= this.getReadableDatabase();
    Cursor c,m;

    public DbRegister(Context context)
    {
        super(context, Db_Name, null, Db_Version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        try {
            String CreateQuery = "CREATE TABLE " + U_Name + "( " + User_Name + " TEXT PRIMARY KEY, " + F_Name + " TEXT, " + L_Name + " TEXT, " + Email + " TEXT, " + Password + " TEXT)";
            db.execSQL(CreateQuery);

            String CreateQuery2 = "CREATE TABLE " + T_Name + "( " + created_at + " TEXT, " + User_Name + " TEXT, " + order_id + " TEXT PRIMARY KEY, " + F_Name + " TEXT, " + L_Name + " TEXT, " + Service + " TEXT, " + Number + " TEXT, " + Amt + " TEXT)";
            db.execSQL(CreateQuery2);
        }
        catch (SQLException se){}
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + U_Name);
        db.execSQL("DROP TABLE IF EXISTS " + T_Name);
        onCreate(db);
    }

    public void addUser(User user)
    {
        SQLiteDatabase db= getWritableDatabase();
        ContentValues values= new ContentValues();
        values.put(User_Name,user.getUsername());
        values.put(F_Name,user.getFname());
        values.put(L_Name,user.getLname());
        values.put(Email,user.getEmail());
        values.put(Password, user.getPassword());


        try {
            db.insert(U_Name, null, values);
            db.close();
        }
        catch(SQLException se){}
    }

    public Cursor getUser(String username, String password)
    {
        Cursor d=null;
        try {
            c = db.query(U_Name, new String[]{User_Name, Password}, User_Name + "=?", new String[]{String.valueOf(username)}, null, null, null, null);
            try {
                if (c != null) {
                    c.moveToFirst();
                    if (c.getString(1).matches(password))
                        d = c;
                    else
                        d = null;
                }
            } catch (SQLException se) {

            }
        }
        catch(Exception e) {
        }

        return d;
    }

    public Cursor getnewUser(String username, String password, String email)
    {
        Cursor d=null;
        try {
            c = db.query(U_Name, new String[]{User_Name, Password, Email}, User_Name + "=?", new String[]{String.valueOf(username)}, null, null, null, null);
            try {
                if (c != null) {
                    //String name = c.getString(c.getColumnIndex(Email));

                    c.moveToFirst();
                    if ((c.getString(1).matches(password)) && (c.getString(2).matches(email)))
                        d = c;
                    else
                        d = null;
                }
            } catch (SQLException se) {

            }
        }
        catch(Exception e) {
        }

        return d;
    }


    public void addTransaction(Transaction t) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(User_Name,t.getUname());
        values.put(order_id, t.getOrder_id());
        values.put(F_Name, t.getFname());
        values.put(L_Name, t.getLname());
        values.put(Service, t.getService());
        values.put(Number, t.getNumber());
        values.put(Amt, t.getAmount());
        values.put(created_at, t.getDateTime());


        try {
            db.insert(T_Name, null, values);
            db.close();
        } catch (SQLException se) {
        }
    }

    public Cursor getAllTransactions(String username) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor d = null;

        //Cursor res =  db.rawQuery( "SELECT * FROM " + T_Name , null );
        Cursor res = db.query(T_Name, new String[]{Service, Number, order_id,Amt, created_at}, User_Name + "=?", new String[]{String.valueOf(username)}, null, null, created_at + " DESC", "6");

        //Cursor res = db.query(T_Name, new String[]{F_Name, L_Name, order_id,Service,Number,Amt}, null, null, null, null, null);
        if(res!=null){
            d=res;
            d.moveToFirst();
        }
        return d;
    }

    public Cursor getTheUser(String username){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor d = null;
        Cursor res = db.query(U_Name, new String[]{F_Name, L_Name, Email}, User_Name + "=?", new String[]{String.valueOf(username)}, null, null, null, null);
        if(res!=null){
            d=res;
            d.moveToFirst();

        }
        return d;
    }

    public int getUName(String username){
        SQLiteDatabase db = this.getReadableDatabase();
        int fnum=0;
        try {
            Cursor res = db.query(U_Name, new String[]{User_Name}, User_Name + "=?", new String[]{String.valueOf(username)}, null, null, null, null);
            fnum = res.getCount();
            res.moveToFirst();
            String name = res.getString(res.getColumnIndex(User_Name));
            if (fnum > 0) {
                return fnum;
            }
        }
        catch(Exception e){
        }
        return 0;
    }

    public String pasRet(String username)
    {
        String password=null;

        c=db.query(U_Name, new String[]{Password}, User_Name + "=?", new String[]{String.valueOf(username)}, null, null, null, null);;
        try {
            if (c != null) {
                c.moveToFirst();
                password = c.getString(0);
            }
            else
                password=null;

        }
        catch (SQLException se)
        {}

        return password;
    }



    public Integer PassChange(String username,String password) {
        int ret=0;

        ContentValues values = new ContentValues();

        try {
            c = db.query(U_Name, new String[]{Password}, User_Name + "=?", new String[]{String.valueOf(username)}, null, null, null, null);

            if (c != null) {
                c.moveToFirst();

                values.put(Password, password);
                db.update(U_Name, values, User_Name + "=?", new String[]{String.valueOf(username)});
                ret=1;
            }
        } catch (SQLException se) {
        }

        return ret;
    }

    public int getUserCount() {
        String countQuery = "SELECT  * FROM " + U_Name;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        int cnt = cursor.getCount();
        cursor.close();
        return cnt;
    }

    public int getTransactionCount(String username) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.query(T_Name, new String[]{User_Name,F_Name, L_Name, Service, Number, order_id,Amt}, User_Name + "=?", new String[]{String.valueOf(username)}, null, null, null, null);
        int fnum = res.getCount();

        res.close();
        return fnum;
    }


}
