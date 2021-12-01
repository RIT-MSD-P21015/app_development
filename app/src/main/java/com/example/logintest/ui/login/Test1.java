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
import com.example.logintest.data.AppData;
import com.example.logintest.data.SendData;
import com.example.logintest.data.sensors.DataCollector;
import com.example.logintest.data.sensors.TestDataManager;

public class Test1 extends AppCompatActivity {

    private DataCollector dataCollector;

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test1);

        // Grab all the stuff on screen
        Button startTest = findViewById(R.id.button_start_test1);
        Button endTest = findViewById(R.id.button_end_test1);
        Button returnDashboardButton = findViewById(R.id.button_return_main_test_1);
        TextView textViewTest1 = findViewById(R.id.textViewTest1Title);
        TextView textViewTest1Instr1 = findViewById(R.id.textViewTest1Instr1);
        TextView textViewTest1Instr2 = findViewById(R.id.textViewTest1Instr2);
        TextView textViewTest1Instr3 = findViewById(R.id.textViewTest1Instr3);
        TextView textViewTest1Instr4 = findViewById(R.id.textViewTest1Instr4);
        TextView textViewTest1Instr5 = findViewById(R.id.textViewTest1Instr5);

        // Set size of textViews
        textViewTest1.setTextSize(SettingsStyle.getFontSize());
        textViewTest1Instr1.setTextSize(SettingsStyle.getFontSize());
        textViewTest1Instr2.setTextSize(SettingsStyle.getFontSize());
        textViewTest1Instr3.setTextSize(SettingsStyle.getFontSize());
        textViewTest1Instr4.setTextSize(SettingsStyle.getFontSize());
        textViewTest1Instr5.setTextSize(SettingsStyle.getFontSize());

        // Set size of buttons
//        startTest.setTextSize(SettingsStyle.getFontSize());
//        endTest.setTextSize(SettingsStyle.getFontSize());
//        returnDashboardButton.setTextSize(SettingsStyle.getFontSize());

        // Set color of button backgrounds
        startTest.setBackgroundColor(SettingsStyle.getPrimaryColor());
        endTest.setBackgroundColor(SettingsStyle.getSecondaryColor());
        returnDashboardButton.setBackgroundColor(SettingsStyle.getPrimaryColor());

        // Disable end test button until start test has been pressed
        endTest.setEnabled(false);

        // Set the on click listener
        startTest.setOnClickListener(v -> collectFirstTest());
        endTest.setOnClickListener(v -> endCollectFirstTest());
        returnDashboardButton.setOnClickListener(v -> openDashboardActivity());

        // TODO serialize the data manager
        TestDataManager tdm = new TestDataManager();
        dataCollector = new DataCollector(this, tdm);
    }


    private void collectFirstTest() {
        Button startTest = findViewById(R.id.button_start_test1);
        Button endTest = findViewById(R.id.button_end_test1);

        // start data collection for first test...start logging data
        dataCollector.start();

        String FirstTestStart = getString(R.string.first_test_start);
        Toast.makeText(getApplicationContext(), FirstTestStart, Toast.LENGTH_LONG).show();

        // disable start test and enable end test
        startTest.setEnabled(false);
        endTest.setEnabled(true);
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

        // add test data to global AppData
        AppData testData = (AppData) getApplicationContext();
        testData.updateTdm(1, dataCollector.getTdm());

        // fire and forget submit data to database in async
        String base64TestData = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            base64TestData = testData.getBase64TestData();
        }
        new SendData().execute("tests", base64TestData, testData.getToken());

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