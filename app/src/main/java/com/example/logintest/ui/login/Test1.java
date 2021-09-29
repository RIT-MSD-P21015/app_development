package com.example.logintest.ui.login;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import com.example.logintest.R;
import com.example.logintest.data.sensors.DataCollector;
import com.example.logintest.data.sensors.TestDataManager;

public class Test1 extends AppCompatActivity {

    // TODO serialize the data manager
    private TestDataManager tdm;
    private DataCollector dataCollector;

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
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

        tdm = new TestDataManager();
        dataCollector = new DataCollector(this, tdm);
    }

    private void collectFirstTest() {
        // start data collection for first test...start logging data
        dataCollector.start();

        String FirstTestStart = getString(R.string.first_test_start);
        Toast.makeText(getApplicationContext(), FirstTestStart, Toast.LENGTH_LONG).show();
    }

    private void endCollectFirstTest() {

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

        // end data collection for first test
        dataCollector.stop();

        // TODO send data to server

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