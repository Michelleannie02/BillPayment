package com.example.mahe.billpayment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v4.app.TaskStackBuilder;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Mahe on 6/22/2017.
 */

public class Changepwd extends AppCompatActivity {

    Bundle bundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_changepwd);
        bundle=getIntent().getExtras();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
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

   /** @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, BillPayment.class);
        startActivity(intent);
        Changepwd.this.finish();
    }**/
    public void GoToBillPayment(View view)
    {
        EditText et1,et2,et3;
        DbRegister db= new DbRegister(this);

        et1=(EditText)findViewById(R.id.oldpass);
        et2=(EditText)findViewById(R.id.newpass);
        et3=(EditText)findViewById(R.id.renewpass);
        String pwd = et2.getText().toString().trim();

        if (et1.getText().toString().trim().matches("") || et2.getText().toString().trim().matches("") || et3.getText().toString().trim().matches(""))
        {
            Toast.makeText(getApplicationContext(),"Enter all the details", Toast.LENGTH_SHORT).show();
        }

        else if (!(isValidPassword(pwd))) {
            //  Toast.makeText(getApplicationContext(), "Invalid password. Please refer format!", Toast.LENGTH_SHORT).show();
            if(pwd.length()<6){
                Toast.makeText(getApplicationContext(), "Password should be atleast 6 digits long!", Toast.LENGTH_SHORT).show();
            }
            if(!pwd.matches(".*\\d+.*")){
                Toast.makeText(getApplicationContext(), "Password should contain atleast 1 number!", Toast.LENGTH_SHORT).show();
            }
            else if(!pwd.matches(".*[A-Z]+.*")){
                Toast.makeText(getApplicationContext(), "Password should contain atleast 1 Capital letter!", Toast.LENGTH_SHORT).show();
            }
            else if(!pwd.matches(".*[a-z]+.*")){
                Toast.makeText(getApplicationContext(), "Password should contain atleast 1 small letter!", Toast.LENGTH_SHORT).show();
            }
            else if(!pwd.matches(".*[@#$%&^*_=+]+.*")){
                Toast.makeText(getApplicationContext(), "Password should contain atleast 1 special character!", Toast.LENGTH_SHORT).show();
            }
            else{
                Toast.makeText(getApplicationContext(), "Invalid password. Refer format!", Toast.LENGTH_SHORT).show();
            }
        }

        else if (et1.getText().toString().trim().matches(bundle.getString("Password")))
        {
            int check;

            String password=et3.getText().toString().trim();
            if (et2.getText().toString().trim().matches(et3.getText().toString().trim()))
            {
                check=db.PassChange(bundle.getString("Username"), password);
                Intent intent=new Intent(this,Login.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                Changepwd.this.finish();
            }
        }
        else
        {
            Toast.makeText(getApplicationContext(),"New Passwords do not match",Toast.LENGTH_SHORT).show();
        }
    }

    public boolean isValidPassword(String password) {

        Pattern pattern;
        Matcher matcher;

        final String PASSWORD_PATTERN = "^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[$@#_!%*?&])[A-Za-z0-9$@#_!%*?&].{4,}$";

        pattern = Pattern.compile(PASSWORD_PATTERN);
        matcher = pattern.matcher(password);

        return matcher.matches();

    }
}
