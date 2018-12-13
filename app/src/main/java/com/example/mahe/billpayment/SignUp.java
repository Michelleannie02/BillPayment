package com.example.mahe.billpayment;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v4.app.TaskStackBuilder;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Mahe on 6/22/2017.
 */

public class SignUp extends AppCompatActivity {

    Bundle bundle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

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


    public void GoToLogin(View view)
    {
        EditText Username,Firstname, Lastname, Email, Password, Repassword;
        DbRegister db=new DbRegister(this);
        Bundle bundle1=new Bundle();
        Cursor entry;

        Username=(EditText)findViewById(R.id.username);
        Firstname=(EditText)findViewById(R.id.firstname);
        Lastname=(EditText)findViewById(R.id.lastname);
        Email=(EditText)findViewById(R.id.email);
        Password=(EditText)findViewById(R.id.password);
        Repassword=(EditText)findViewById(R.id.reenterpass);

        String email = Email.getText().toString().trim();
        String pwd = Password.getText().toString().trim();

        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
        String emailPattern2 = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+\\.+[a-z]+";
        //String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=-_!*])(?=\\S+$).{4,}$";


        //entry = db.getnewUser(Username.getText().toString(), Password.getText().toString());
        int name = db.getUName(Username.getText().toString());


        if(Username.getText().toString().trim().equals("") || Firstname.getText().toString().trim().equals("") || Lastname.getText().toString().trim().equals("") || Email.getText().toString().trim().equals("") || Password.getText().toString().trim().equals("") || Repassword.getText().toString().trim().equals(""))
        {
            Toast.makeText(getApplicationContext(), "Please fill in all the details", Toast.LENGTH_SHORT).show();
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

        else if(!Password.getText().toString().trim().equals(Repassword.getText().toString())){
            Toast.makeText(getApplicationContext(), "Passwords donot match.", Toast.LENGTH_LONG).show();

        }

           else if ((db.getnewUser(Username.getText().toString(), Password.getText().toString(), Email.getText().toString())) != null) {
                Toast.makeText(getApplicationContext(), "User already exists. Please Login.", Toast.LENGTH_LONG).show();

            }


               else if ((name > 0) && ((db.getnewUser(Username.getText().toString(), Password.getText().toString(), Email.getText().toString()) == null))) {
                    Toast.makeText(getApplicationContext(), "Username already exists. Please type another username.", Toast.LENGTH_LONG).show();
                }

                else  if (!((email.matches(emailPattern2))||(email.matches(emailPattern))))
        {
            Toast.makeText(getApplicationContext(),"Invalid email address",Toast.LENGTH_SHORT).show();
        }

       else if (Password.getText().toString().trim().equals(Repassword.getText().toString())){
                    User user = new User(Username.getText().toString(), Firstname.getText().toString(), Lastname.getText().toString(), Email.getText().toString(), Password.getText().toString());
                    db.addUser(user);

                    bundle1.putString("Username", Username.getText().toString());
                    bundle1.putString("FirstName", Firstname.getText().toString());
                    bundle1.putString("LastName", Lastname.getText().toString());
                    bundle1.putString("Email", Email.getText().toString());
                    bundle1.putString("Password", Password.getText().toString());
                    bundle1.putString("From", "BillPayment");

                    Intent intent = new Intent(this, Login.class);
                    intent.putExtras(bundle1);
                    startActivity(intent);
                    SignUp.this.finish();
                }



        }

    public boolean isValidPassword(String password) {

        Pattern pattern;
        Matcher matcher;

        //final String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=-_!])(?=\\S+$).{4,}$";
        final String PASSWORD_PATTERN = "^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[$@#_!%*?&])[A-Za-z0-9$@#_!%*?&].{4,}$";

        pattern = Pattern.compile(PASSWORD_PATTERN);
        matcher = pattern.matcher(password);

        return matcher.matches();

    }

}

