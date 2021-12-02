package com.example.logintest.ui.login;

import android.os.Build;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.example.logintest.R;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.TimeZone;

public class FallRiskResults extends AppCompatActivity {

    public FallRiskResults(Object zonedDateTime) {
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fall_risk_results);

        TextView ResultTextView = findViewById(R.id.textViewFallRisk);
        TextView TimestampTextView = findViewById(R.id.textViewTimeStamp);

        String resultString = (int) (Results.getResultDouble()*100) + "%";
        ResultTextView.setText(resultString);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

        TimeZone tz = TimeZone.getDefault();
        TimeZone UTC = TimeZone.getTimeZone("UTC");
        ZoneId zoneID = UTC.toZoneId();
        ZonedDateTime zonedResult= Results.getTimestampResult().atZone(zoneID);

        ZonedDateTime zdt = Results.getTimestampResult().atZone(tz.toZoneId());



        String formatDateTime = String.format(zonedResult.toString(), formatter);

//        TimestampTextView.setText(String.format(Results.getTimestampResult().toString(), formatter));
        TimestampTextView.setText(zdt.toString());

    }
}