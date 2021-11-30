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

public class Test2 extends AppCompatActivity {

    private DataCollector dataCollector;

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test2);

        // Grab all the stuff on screen
        Button startTest = findViewById(R.id.button_start_test2);
        Button endTest = findViewById(R.id.button_end_test2);
        Button returnDashboardButton = findViewById(R.id.button_return_to_main_test_2);
        TextView textViewTest2 = findViewById(R.id.textViewTest2Title);
        TextView textViewTest2Instr1 = findViewById(R.id.textViewTest2Instr1);
        TextView textViewTest2Instr2 = findViewById(R.id.textViewTest2Instr2);
        TextView textViewTest2Instr3 = findViewById(R.id.textViewTest2Instr3);
        TextView textViewTest2Instr4 = findViewById(R.id.textViewTest2Instr4);
        TextView textViewTest2Instr5 = findViewById(R.id.textViewTest2Instr5);

        // Set size of textViews
        textViewTest2.setTextSize(SettingsStyle.getFontSize());
        textViewTest2Instr1.setTextSize(SettingsStyle.getFontSize());
        textViewTest2Instr2.setTextSize(SettingsStyle.getFontSize());
        textViewTest2Instr3.setTextSize(SettingsStyle.getFontSize());
        textViewTest2Instr4.setTextSize(SettingsStyle.getFontSize());
        textViewTest2Instr5.setTextSize(SettingsStyle.getFontSize());

        // Set size of buttons
//        startTest.setTextSize(SettingsStyle.getFontSize());
//        endTest.setTextSize(SettingsStyle.getFontSize());
//        returnDashboardButton.setTextSize(SettingsStyle.getFontSize());

        // Disable end test button until start test has been pressed
        endTest.setEnabled(false);

        // Set the on click listener
        startTest.setOnClickListener(v -> collectSecondTest());
        endTest.setOnClickListener(v -> endCollectSecondTest());
        returnDashboardButton.setOnClickListener(v -> openDashboardActivity());

        TestDataManager tdm = new TestDataManager();
        dataCollector = new DataCollector(this, tdm);
    }

    private void collectSecondTest() {
        Button startTest = findViewById(R.id.button_start_test2);
        Button endTest = findViewById(R.id.button_end_test2);

        // start data collection for first test...start logging data
        dataCollector.start();

        String SecondTestStart = "Second Test Dat Collection Started";
        Toast.makeText(getApplicationContext(), SecondTestStart, Toast.LENGTH_LONG).show();

        // disable start test and enable end test
        startTest.setEnabled(false);
        endTest.setEnabled(true);
    }

    private void endCollectSecondTest() {
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

        String SecondTestEnd = "Second Test ENDED";
        Toast.makeText(getApplicationContext(), SecondTestEnd, Toast.LENGTH_LONG).show();

        Intent intent = new Intent(this, Test3.class);
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