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

public class Test3 extends AppCompatActivity {

    private DataCollector dataCollector;

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test3);

        // Grab all the stuff on screen
        Button startTest = findViewById(R.id.button_start_test3);
        Button endTest = findViewById(R.id.button_end_test3);
        Button returnDashboardButton = findViewById(R.id.button_return_main_test3);
        TextView textViewTest3 = findViewById(R.id.textViewTest3Title);
        TextView textViewTest3Instr1 = findViewById(R.id.textViewTest3Instr1);
        TextView textViewTest3Instr2 = findViewById(R.id.textViewTest3Instr2);
        TextView textViewTest3Instr3 = findViewById(R.id.textViewTest3Instr3);
        TextView textViewTest3Instr4 = findViewById(R.id.textViewTest3Instr4);

        // Set size of textViews
        textViewTest3.setTextSize(SettingsStyle.getFontSize());
        textViewTest3Instr1.setTextSize(SettingsStyle.getFontSize());
        textViewTest3Instr2.setTextSize(SettingsStyle.getFontSize());
        textViewTest3Instr3.setTextSize(SettingsStyle.getFontSize());
        textViewTest3Instr4.setTextSize(SettingsStyle.getFontSize());

        // Set size of buttons
        startTest.setTextSize(SettingsStyle.getFontSize());
        endTest.setTextSize(SettingsStyle.getFontSize());
        returnDashboardButton.setTextSize(SettingsStyle.getFontSize());

        // Set color of button backgrounds
        startTest.setBackgroundColor(SettingsStyle.getPrimaryColor());
        endTest.setBackgroundColor(SettingsStyle.getSecondaryColor());
        returnDashboardButton.setBackgroundColor(SettingsStyle.getPrimaryColor());

        // Disable end test button until start test has been pressed
        endTest.setEnabled(false);

        // Set the on click listener
        startTest.setOnClickListener(v -> collectThirdTest());
        endTest.setOnClickListener(v -> endCollectThirdTest());
        returnDashboardButton.setOnClickListener(v -> openDashboardActivity());

        TestDataManager tdm = new TestDataManager();
        dataCollector = new DataCollector(this, tdm);
    }

    private void collectThirdTest() {
        Button startTest = findViewById(R.id.button_start_test3);
        Button endTest = findViewById(R.id.button_end_test3);

        // start data collection for first test...start logging data
        dataCollector.start();

        String ThirdTestStart = "Third Test Data Collection Started";
        Toast.makeText(getApplicationContext(), ThirdTestStart, Toast.LENGTH_LONG).show();

        // disable start test and enable end test
        startTest.setEnabled(false);
        endTest.setEnabled(true);
    }

    private void endCollectThirdTest() {
        // end data collection for first test
        dataCollector.stop();

        // add test data to global AppData
        AppData testData = (AppData) getApplicationContext();
        testData.updateTdm(3, dataCollector.getTdm());

        // fire and forget submit data to database in async
        String base64TestData = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            base64TestData = testData.getBase64TestData();
        }
        new SendData().execute("tests", base64TestData, testData.getToken());

        String ThirdTestEnd = "Third Test ENDED";
        Toast.makeText(getApplicationContext(), ThirdTestEnd, Toast.LENGTH_LONG).show();

        Intent intent = new Intent(this, Test4.class);
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