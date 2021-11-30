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

public class Test5 extends AppCompatActivity {

    private DataCollector dataCollector;

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test5);

        Button startTest = findViewById(R.id.button_start_test5);
        Button endTest = findViewById(R.id.button_end_test5);
        Button returnDashboardButton = findViewById(R.id.button_return_main_test5);
        TextView textViewTest5 = findViewById(R.id.textViewTest5Title);
        TextView textViewTest5Instr1 = findViewById(R.id.textViewTest5Instr1);
        TextView textViewTest5Instr2 = findViewById(R.id.textViewTest5Instr2);
        TextView textViewTest5Instr3 = findViewById(R.id.textViewTest5Instr3);

        // Set size of textViews
        textViewTest5.setTextSize(SettingsStyle.getFontSize());
        textViewTest5Instr1.setTextSize(SettingsStyle.getFontSize());
        textViewTest5Instr2.setTextSize(SettingsStyle.getFontSize());
        textViewTest5Instr3.setTextSize(SettingsStyle.getFontSize());

        // Set size of buttons
//        startTest.setTextSize(SettingsStyle.getFontSize());
//        endTest.setTextSize(SettingsStyle.getFontSize());
//        returnDashboardButton.setTextSize(SettingsStyle.getFontSize());

        // Disable end test button until start test has been pressed
        endTest.setEnabled(false);

        // Set the on click listener
        startTest.setOnClickListener(v -> collectFifthTest());
        endTest.setOnClickListener(v -> endCollectFifthTest());
        returnDashboardButton.setOnClickListener(v -> openDashboardActivity());

        TestDataManager tdm = new TestDataManager();
        dataCollector = new DataCollector(this, tdm);
    }

    private void collectFifthTest() {
        Button startTest = findViewById(R.id.button_start_test5);
        Button endTest = findViewById(R.id.button_end_test5);

        // start data collection for first test...start logging data
        dataCollector.start();

        String FifthTestStart = "Fifth Test Data Collection Started";
        Toast.makeText(getApplicationContext(), FifthTestStart, Toast.LENGTH_LONG).show();

        // disable start test and enable end test
        startTest.setEnabled(false);
        endTest.setEnabled(true);
    }

    private void endCollectFifthTest() {
        // end data collection for first test
        dataCollector.stop();

        // add test data to global AppData
        AppData testData = (AppData) getApplicationContext();
        testData.updateTdm(2, dataCollector.getTdm());

        // fire and forget submit data to database in async
        String base64TestData = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            base64TestData = testData.getBase64TestData();
        }
        new SendData().execute("tests", base64TestData, testData.getToken());

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