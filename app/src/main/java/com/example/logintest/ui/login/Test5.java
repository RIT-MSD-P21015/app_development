package com.example.logintest.ui.login;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import com.example.logintest.R;
import com.example.logintest.data.sensors.DataCollector;
import com.example.logintest.data.sensors.TestDataManager;

public class Test5 extends AppCompatActivity {

    // TODO serialize the data manager
    private TestDataManager tdm;
    private DataCollector dataCollector;

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test5);

        Button startTest = findViewById(R.id.button_start_test5);
        Button endTest = findViewById(R.id.button_end_test5);
        Button returnDashboardButton = findViewById(R.id.button_return_main_test5);

        startTest.setOnClickListener(v -> collectFifthTest());
        endTest.setOnClickListener(v -> endCollectFifthTest());
        returnDashboardButton.setOnClickListener(v -> openDashboardActivity());

        tdm = new TestDataManager();
        dataCollector = new DataCollector(this, tdm);
    }

    private void collectFifthTest() {
        // start data collection for first test...start logging data
        dataCollector.start();

        String FifthTestStart = "Fifth Test Data Collection Started";
        Toast.makeText(getApplicationContext(), FifthTestStart, Toast.LENGTH_LONG).show();
    }

    private void endCollectFifthTest() {
        // end data collection for first test
        dataCollector.stop();

        // TODO send data to server

        String FifthTestEnd = "Fifth Test ENDED";
        Toast.makeText(getApplicationContext(), FifthTestEnd, Toast.LENGTH_LONG).show();

        String FinishedTests = "All tests finished, returning to main menu";
        Toast.makeText(getApplicationContext(), FinishedTests, Toast.LENGTH_LONG).show();

        Intent intent = new Intent(this, Dashboard.class);
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