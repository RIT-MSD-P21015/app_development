package com.example.logintest.ui.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import com.example.logintest.R;

public class Test1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test1);

        Button startTest = findViewById(R.id.button_start_test1);
        Button endTest = findViewById(R.id.button_end_test1);
        Button returnDashboardButton = findViewById(R.id.button_return_main_test_1);

        startTest.setOnClickListener(v -> collectFirstTest());
        endTest.setOnClickListener(v -> endCollectFirstTest());
        returnDashboardButton.setOnClickListener(v -> openDashboardActivity());
    }

    private void collectFirstTest() {
        // TODO: start data collection for first test...start logging data

        String FirstTestStart = getString(R.string.first_test_start);
        Toast.makeText(getApplicationContext(), FirstTestStart, Toast.LENGTH_LONG).show();
    }

    private void endCollectFirstTest() {
        // TODO: end data collection for first test...send to server
        // once data collection finishing, change activity to second test
//        int sentToServer = 0;
//        while(sentToServer != 1) {
//            if (sentToServer == 1) {
//                Intent intent = new Intent(this, SecondTest.class);
//                startActivity(intent);
//                // make sure to close this activity, since we aren't returning to it
//                this.finish();
//            }
//        }

        String FirstTestEnd = "First Test ENDED";
        Toast.makeText(getApplicationContext(), FirstTestEnd, Toast.LENGTH_LONG).show();

        Intent intent = new Intent(this, Test2.class);
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