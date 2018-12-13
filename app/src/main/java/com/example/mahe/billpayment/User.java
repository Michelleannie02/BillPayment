package com.example.mahe.billpayment;

/**
 * Created by Mahe on 6/22/2017.
 */

public class User {

    String username;
    String fname;
    String lname;
    String email;
    String password;


    public User()
    {
        this.username=null;
        this.fname=null;
        this.lname=null;
        this.email=null;
        this.password=null;

    }

    public User(String username, String fname, String lname, String email, String password)
    {
        this.username=username;
        this.fname= fname;
        this. lname=lname;
        this.email=email;
        this.password=password;

    }

    public void setUsername(String username) { this.username=username; }

    public String getUsername() { return this.username; }

    public void setFname(String fname)
    {
        this.fname=fname;
    }

    public String getFname()
    {
        return this.fname;
    }

    public void setLname(String lname)
    {
        this.lname=lname;
    }

    public String getLname()
    {
        return this.lname;
    }

    public void setEmail(String email)
    {
        this.email=email;
    }

    public String getEmail()
    {
        return this.email;
    }

    public void setPassword(String password)
    {
        this.password=password;
    }

    public String getPassword()
    {
        return this.password;
    }


}
