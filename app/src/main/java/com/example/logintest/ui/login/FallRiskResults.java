package com.example.logintest.ui.login;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.example.logintest.R;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.TimeZone;

public class FallRiskResults extends AppCompatActivity {

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fall_risk_results);

        TextView ResultTextView = findViewById(R.id.textViewFallRisk);
        TextView TimestampTextView = findViewById(R.id.textViewTimeStamp);
        Button returnToDashboard = findViewById(R.id.buttonReturnMainFallRisks);

        String resultString = (int) (Results.getResultDouble()*100) + "%";
        ResultTextView.setText(resultString);

        // Set color of button backgrounds
        returnToDashboard.setBackgroundColor(SettingsStyle.getPrimaryColor());

        TimeZone tz = TimeZone.getDefault();
        TimeZone UTC = TimeZone.getTimeZone("UTC");
        ZoneId zoneID = UTC.toZoneId();
        ZonedDateTime zonedResult= Results.getTimestampResult().atZone(zoneID);

        ZonedDateTime zdt = zonedResult.withZoneSameInstant(tz.toZoneId());


        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm yyyy-MM-dd").withZone(tz.toZoneId());

        String test = zdt.format(formatter);

        String formatDateTime = String.format(zonedResult.toString(), formatter);


        TimestampTextView.setText(test);
//        TimestampTextView.setText(String.format(Results.getTimestampResult().toString(), formatter));
//        TimestampTextView.setText(zdt.toString());

        returnToDashboard.setOnClickListener(v -> openDashboardActivity());

    }

    private void openDashboardActivity() {
        Intent intent = new Intent(this, Dashboard.class);
        startActivity(intent);
        // make sure to close this activity, since we aren't returning to it
        this.finish();
    }
}