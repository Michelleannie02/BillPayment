package com.example.mahe.billpayment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v4.app.TaskStackBuilder;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

/**
 * Created by Mahe on 6/23/2017.
 */

public class Settings extends AppCompatActivity {

    Bundle bundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        bundle=getIntent().getExtras();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                Intent intent = new Intent(this, BillPayment.class);
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
        intent.putExtras(bundle);
        startActivity(intent);
        Settings.this.finish();
    }**/

    public void GoToChangepwd(View view){
        Intent intent = new Intent(this, Changepwd.class);
        intent.putExtras(bundle);
        startActivity(intent);
       // Settings.this.finish();
    }

    public void GoToViewProfile(View view) {
        Intent intent = new Intent(this, ViewProfile.class);
        intent.putExtras(bundle);
        startActivity(intent);
      //  Settings.this.finish();
    }


}
