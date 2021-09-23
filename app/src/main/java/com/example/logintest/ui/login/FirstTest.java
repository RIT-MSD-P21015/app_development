package com.example.logintest.ui.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.example.logintest.R;

public class FirstTest extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_test);

        Button startTest = findViewById(R.id.button_start_first_test);
        Button endTest = findViewById(R.id.button_end_first_test);
        Button returnDashboardButton = findViewById(R.id.button_return_main_test_1);

        startTest.setOnClickListener(v -> collectFirstTest());
        endTest.setOnClickListener(v -> endCollectFirstTest());
        returnDashboardButton.setOnClickListener(v -> openDashboardActivity());
    }

    private void collectFirstTest() {
        // TODO: start data collection for first test...start logging data
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
        Intent intent = new Intent(this, SecondTest.class);
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