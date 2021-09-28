package com.example.logintest.ui.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import com.example.logintest.R;

public class Test4 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test4);

        Button startTest = findViewById(R.id.button_start_test4);
        Button endTest = findViewById(R.id.button_end_test4);
        Button returnDashboardButton = findViewById(R.id.button_return_main_test4);

        startTest.setOnClickListener(v -> collectFourthTest());
        endTest.setOnClickListener(v -> endCollectFourthTest());
        returnDashboardButton.setOnClickListener(v -> openDashboardActivity());
    }

    private void collectFourthTest() {
        // TODO: start data collection for second test...start logging data

        String FourthTestStart = "Fourth Test Data Collection Started";
        Toast.makeText(getApplicationContext(), FourthTestStart, Toast.LENGTH_LONG).show();
    }

    private void endCollectFourthTest() {
        // TODO: end data collection for second test...send to server

        String FourthTestEnd = "Fourth Test ENDED";
        Toast.makeText(getApplicationContext(), FourthTestEnd, Toast.LENGTH_LONG).show();

        Intent intent = new Intent(this, Test5.class);
        startActivity(intent);
        // make sure to close this activity, since we aren't returning to it
        this.finish();
    }


    private void openDashboardActivity() {
        Intent intent = new Intent(this, Dashboard.class);
        startActivity(intent);
        // make sure to close this activity, since we aren't returning to it
        this.finish();
    }
}