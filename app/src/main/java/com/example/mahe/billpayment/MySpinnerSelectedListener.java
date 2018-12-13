package com.example.mahe.billpayment;

import android.view.View;
import android.widget.AdapterView;

/**
 * Created by Mahe on 7/21/2017.
 */

public class MySpinnerSelectedListener implements AdapterView.OnItemSelectedListener {

    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
        String selected = parent.getItemAtPosition(pos).toString();
    }

    public void onNothingSelected(AdapterView parent) {
        // Do nothing.
    }
}