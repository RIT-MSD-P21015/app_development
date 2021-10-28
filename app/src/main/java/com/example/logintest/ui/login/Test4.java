package com.example.logintest.ui.login;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.example.logintest.R;
import com.example.logintest.data.sensors.DataCollector;
import com.example.logintest.data.sensors.TestDataManager;

public class Test4 extends AppCompatActivity {

    // TODO serialize the data manager
    private TestDataManager tdm;
    private DataCollector dataCollector;

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test4);

        // Grab all the stuff on screen
        Button startTest = findViewById(R.id.button_start_test4);
        Button endTest = findViewById(R.id.button_end_test4);
        Button returnDashboardButton = findViewById(R.id.button_return_main_test4);
        TextView textViewTest4 = findViewById(R.id.textViewTest4Title);
        TextView textViewTest4Instr1 = findViewById(R.id.textViewTest4Instr1);
        TextView textViewTest4Instr2 = findViewById(R.id.textViewTest4Instr2);
        TextView textViewTest4Instr3 = findViewById(R.id.textViewTest4Instr3);
        TextView textViewTest4Instr4 = findViewById(R.id.textViewTest4Instr4);

        // Set size of textViews
        textViewTest4.setTextSize(SettingsStyle.getFontSize());
        textViewTest4Instr1.setTextSize(SettingsStyle.getFontSize());
        textViewTest4Instr2.setTextSize(SettingsStyle.getFontSize());
        textViewTest4Instr3.setTextSize(SettingsStyle.getFontSize());
        textViewTest4Instr4.setTextSize(SettingsStyle.getFontSize());

        // Set size of buttons
//        startTest.setTextSize(SettingsStyle.getFontSize());
//        endTest.setTextSize(SettingsStyle.getFontSize());
//        returnDashboardButton.setTextSize(SettingsStyle.getFontSize());

        // Set the on click listener
        startTest.setOnClickListener(v -> collectFourthTest());
        endTest.setOnClickListener(v -> endCollectFourthTest());
        returnDashboardButton.setOnClickListener(v -> openDashboardActivity());

        tdm = new TestDataManager();
        dataCollector = new DataCollector(this, tdm);
    }

    private void collectFourthTest() {
        // start data collection for first test...start logging data
        dataCollector.start();

        String FourthTestStart = "Fourth Test Data Collection Started";
        Toast.makeText(getApplicationContext(), FourthTestStart, Toast.LENGTH_LONG).show();
    }

    private void endCollectFourthTest() {
        // end data collection for first test
        dataCollector.stop();

        // TODO send data to server

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