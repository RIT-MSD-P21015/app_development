package com.example.logintest.ui.login;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.logintest.R;

public class PhysicalTest extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_physical_test);

        // Grab all the stuff on screen
        Button startTestButton = findViewById(R.id.button_start_test);
        Button returnDashboardButton = findViewById(R.id.button_return_main_physical_test_page);
        TextView textViewPhysicalTest = findViewById(R.id.textViewPhysicalTest);

        // Set size of textViews
        textViewPhysicalTest.setTextSize(SettingsStyle.getFontSize());

        // Set size of buttons
        startTestButton.setTextSize(SettingsStyle.getFontSize());
        returnDashboardButton.setTextSize(SettingsStyle.getFontSize());

        // Set the on click listener
        startTestButton.setOnClickListener(v -> openTestActivity());
        returnDashboardButton.setOnClickListener(v -> openDashboardActivity());

        // Set color of button backgrounds
        startTestButton.setBackgroundColor(SettingsStyle.getPrimaryColor());
        returnDashboardButton.setBackgroundColor(SettingsStyle.getSecondaryColor());
    }

    @Override
    protected void onResume() {
        super.onResume();

        // Grab all the stuff on screen
        Button startTestButton = findViewById(R.id.button_start_test);
        Button returnDashboardButton = findViewById(R.id.button_return_main_physical_test_page);
        TextView textViewPhysicalTest = findViewById(R.id.textViewPhysicalTest);

        // Set size of textViews
        textViewPhysicalTest.setTextSize(SettingsStyle.getFontSize());

        // Set size of buttons
        startTestButton.setTextSize(SettingsStyle.getFontSize());
        returnDashboardButton.setTextSize(SettingsStyle.getFontSize());
    }

    private void openDashboardActivity() {
        Intent intent = new Intent(this, Dashboard.class);
        startActivity(intent);
        // make sure to close this activity, since we aren't returning to it
        this.finish();
    }

    // move to the PatientTest activity page...start the tests
    private void openTestActivity() {
        Intent intent = new Intent(this, PatientTest.class);
        startActivity(intent);
    }
}