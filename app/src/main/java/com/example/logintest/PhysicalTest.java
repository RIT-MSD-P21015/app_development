package com.example.logintest;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class PhysicalTest extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_physical_test);

        Button startTestButton = (Button) findViewById(R.id.button_start_test);
        Button returnDashboardButton = (Button) findViewById(R.id.button_return_dashboard);

        startTestButton.setOnClickListener(v -> openTestActivity());

        returnDashboardButton.setOnClickListener(v -> openDashboardActivity());
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