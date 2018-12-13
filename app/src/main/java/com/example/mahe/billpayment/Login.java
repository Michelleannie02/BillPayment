package com.example.mahe.billpayment;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v4.app.TaskStackBuilder;
import android.support.v7.app.AppCompatActivity;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by Mahe on 6/22/2017.
 */

public class Login extends AppCompatActivity {

    Bundle bundle;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        bundle = getIntent().getExtras();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        final EditText editText= (EditText)findViewById(R.id.passtext);
        CheckBox checkBox= (CheckBox)findViewById(R.id.showpass);

        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (!isChecked) {

                    editText.setTransformationMethod(PasswordTransformationMethod.getInstance());
                } else {

                    editText.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                Intent upIntent = new Intent(this, MainActivity.class);
                if (NavUtils.shouldUpRecreateTask(this, upIntent)) {

                    TaskStackBuilder.from(this)
                            .addNextIntent(upIntent)
                            .startActivities();
                    finish();
                } else {
                   NavUtils.navigateUpTo(this, upIntent);
                }
                return true;
        }
        return super.onOptionsItemSelected(item);
    }


    public void GoToBillPayment(View view)
    {
        EditText Username,Password;
        DbRegister db= new DbRegister(this);
        Cursor entry;

        Username=(EditText)findViewById(R.id.usertext);
        Password=(EditText)findViewById(R.id.passtext);

        if (Username.getText().toString().trim().equals("") || Password.getText().toString().trim().equals(""))
        {
            Toast.makeText(getApplicationContext(), "Enter the details", Toast.LENGTH_SHORT).show();
        }
        else {
            entry = db.getUser(Username.getText().toString(), Password.getText().toString());

            if (entry != null) {
                Intent intent = new Intent(this, BillPayment.class);
                Bundle bundle = new Bundle();
                bundle.putString("Username", Username.getText().toString().trim());
                bundle.putString("Password", Password.getText().toString().trim());

                intent.putExtras(bundle);
                startActivity(intent);
                Login.this.finish();
            } else if(entry == null){
                Toast.makeText(getApplicationContext(), "No such user exists. Please Sign Up.", Toast.LENGTH_LONG).show();
            }
        }
    }
}



