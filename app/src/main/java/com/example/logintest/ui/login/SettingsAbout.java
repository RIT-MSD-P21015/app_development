package com.example.logintest.ui.login;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.logintest.R;

public class SettingsAbout extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings_about);

        Button returnDashboardButton = findViewById(R.id.button_return_main_settings_about);

        returnDashboardButton.setOnClickListener(v -> openDashboardActivity());

        returnDashboardButton.setBackgroundColor(SettingsStyle.getPrimaryColor());
    }

    private void openDashboardActivity() {
        Intent intent = new Intent(this, Dashboard.class);
        startActivity(intent);
        // make sure to close this activity, since we aren't returning to it
        this.finish();
    }
}