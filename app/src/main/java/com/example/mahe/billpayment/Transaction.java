package com.example.mahe.billpayment;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Mahe on 6/23/2017.
 */

public class Transaction {

    String uname;
    String fname;
    String lname;
    String service;
    String number;
    int order_id;
    int amount;


    public Transaction() {
    }

    public Transaction(String uname, String fname, String lname, String service, String number, int order_id, int amount) {
        super();
        this.uname = uname;
        this.fname = fname;
        this.lname = lname;
        this.service = service;
        this.number = number;
        this.order_id = order_id;
        this.amount = amount;

    }

    public String getUname(){ return uname; }

    public void setUname(String uname){ this.uname = uname ; }

    public String getNumber(){ return number; }

    public void setNumber(String number){ this.number = number; }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public int getOrder_id() {
        return order_id;
    }

    public void setOrder_id(int order_id) {
        this.order_id = order_id;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getDateTime() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        Date date = new Date();
        return dateFormat.format(date);
    }

}
