package com.example.logintest.ui.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.example.logintest.R;

public class PatientTest extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_test);

        Button startTestButton = findViewById(R.id.button_start_test_first);
        Button returnDashboardButton = findViewById(R.id.button_return_main_test);

        startTestButton.setOnClickListener(v -> openTestActivity());
        returnDashboardButton.setOnClickListener(v -> openDashboardActivity());
    }


    private void openTestActivity() {
        Intent intent = new Intent(this, Test1.class);
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