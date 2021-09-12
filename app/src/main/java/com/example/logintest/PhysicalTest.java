package com.example.logintest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class PhysicalTest extends AppCompatActivity {
    private Button startTestButton, returnDashboardButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_physical_test);

        startTestButton = (Button) findViewById(R.id.button_start_test);
        returnDashboardButton = (Button) findViewById(R.id.button_return_dashboard);

        startTestButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openTestActivity();
            }
        });

        returnDashboardButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDashboardActivity();
            }
        });
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