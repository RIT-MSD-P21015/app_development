package com.example.logintest.ui.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;

import com.example.logintest.R;

public class Results extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

        // Grab all the stuff on screen
        final ProgressBar progressBar = (ProgressBar) findViewById(R.id.progressBar_results);
        Button seeResultsButton = (Button) findViewById(R.id.button_see_results);
        Button returnDashboardButton = findViewById(R.id.button_return_main_results);

        // Set size of buttons
        seeResultsButton.setTextSize(SettingsStyle.getFontSize());
        returnDashboardButton.setTextSize(SettingsStyle.getFontSize());

        // Set the on click listener
        seeResultsButton.setOnClickListener(v -> progressBar.setVisibility(View.VISIBLE));
        returnDashboardButton.setOnClickListener(v -> openDashboardActivity());

        // Set color of button backgrounds
        seeResultsButton.setBackgroundColor(SettingsStyle.getPrimaryColor());
        returnDashboardButton.setBackgroundColor(SettingsStyle.getSecondaryColor());
    }

    private void openDashboardActivity() {
        Intent intent = new Intent(this, Dashboard.class);
        startActivity(intent);
        // make sure to close this activity, since we aren't returning to it
        this.finish();
    }
}