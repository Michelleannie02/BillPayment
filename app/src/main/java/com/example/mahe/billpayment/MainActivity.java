package com.example.mahe.billpayment;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity{

    Bundle bundle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void GoToLogin(View view)
    {
        Intent intent= new Intent(this,Login.class);
        startActivity(intent);
    }

    public void GoToSignUp(View view)
    {
        Intent intent= new Intent(this,SignUp.class);
        startActivity(intent);
    }
}
