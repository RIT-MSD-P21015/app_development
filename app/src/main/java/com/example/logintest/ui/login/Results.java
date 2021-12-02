package com.example.logintest.ui.login;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.logintest.R;
import com.example.logintest.data.AppData;
import com.example.logintest.data.NetworkManager;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Results extends AppCompatActivity {

    private static double resultDouble = 0.0;
    private static LocalDateTime timestampResult;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

        // Grab all the stuff on screen
        Button seeResultsButton = findViewById(R.id.button_see_results);
        Button returnDashboardButton = findViewById(R.id.button_return_main_results);

        // Set size of buttons
        seeResultsButton.setTextSize(SettingsStyle.getFontSize());
        returnDashboardButton.setTextSize(SettingsStyle.getFontSize());

        // Set the on click listener
        seeResultsButton.setOnClickListener(v -> {
            try {
                getServerResults();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
        });
        returnDashboardButton.setOnClickListener(v -> openDashboardActivity());

        // Set color of button backgrounds
        seeResultsButton.setBackgroundColor(SettingsStyle.getPrimaryColor());
        returnDashboardButton.setBackgroundColor(SettingsStyle.getSecondaryColor());
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void getServerResults() throws IOException, JSONException {

        AppData tokenData = (AppData) getApplicationContext();

        // get result and timestamp
        String json = NetworkManager.sendGET("/api/user", tokenData.getToken());
        JSONObject jsonObject = new JSONObject(json);
        String result = jsonObject.getString("result");
        String resultTimestamp = jsonObject.getString("result_timestamp");
        String surveyTimestamp = jsonObject.getString("survey_timestamp");
        String testTimestamp = jsonObject.getString("tests_timestamp");

        Log.e("RESULT",result);
        Log.e("TIME_RESULT",resultTimestamp);
        Log.e("TIME_SURVEY",surveyTimestamp);
        Log.e("TIME_TEST",testTimestamp);

        ProgressBar progressBar = findViewById(R.id.progressBar_results);
        progressBar.setVisibility(View.VISIBLE);

        try{
            if(surveyTimestamp == null && testTimestamp == null){
                new AlertDialog.Builder(this)
                        .setTitle("Result Error")
                        .setMessage("Please take the survey and tests before retrying for your results")
                        .setPositiveButton("Okay", null)
                        .show();
            }
            else if(testTimestamp == null){
                new AlertDialog.Builder(this)
                        .setTitle("Result Error")
                        .setMessage("Please take the tests before retrying for your results")
                        .setPositiveButton("Okay", null)
                        .show();
            }
            else if(surveyTimestamp == null){
                new AlertDialog.Builder(this)
                        .setTitle("Result Error")
                        .setMessage("Please take the survey before retrying for your results")
                        .setPositiveButton("Okay", null)
                        .show();
            }
            else if(resultTimestamp == null){
                new AlertDialog.Builder(this)
                        .setTitle("Result Error")
                        .setMessage("Your results are not ready yet, please retry in 45 minutes")
                        .setPositiveButton("Okay", null)
                        .show();
            }

            // Result timestamp is not null and should be available
            else {
                LocalDateTime resultLocalDate = LocalDateTime.parse(resultTimestamp, DateTimeFormatter.ISO_LOCAL_DATE_TIME);
                LocalDateTime surveyLocalDate = LocalDateTime.parse(surveyTimestamp, DateTimeFormatter.ISO_LOCAL_DATE_TIME);
                LocalDateTime testLocalDate = LocalDateTime.parse(testTimestamp, DateTimeFormatter.ISO_LOCAL_DATE_TIME);

                Log.e("LOCAL_DATE_RESULT",resultLocalDate.toString());
                Log.e("LOCAL_DATE_SURVEY",surveyLocalDate.toString());
                Log.e("LOCAL_DATE_TEST",testLocalDate.toString());

                // Check to make sure the results published have the newest timestamp
                if ((resultLocalDate.compareTo(surveyLocalDate) > 0) && (resultLocalDate.compareTo(testLocalDate) > 0)) {

                    resultDouble = Double.parseDouble(result);
                    timestampResult = resultLocalDate;

                    Intent intent = new Intent(this, FallRiskResults.class);
                    startActivity(intent);
                    // make sure to close this activity, since we aren't returning to it
                    this.finish();
                }
                else{
                    new AlertDialog.Builder(this)
                            .setTitle("Result Error")
                            .setMessage("Your results have not been updated, please try again in 45 minutes")
                            .setPositiveButton("Okay", null)
                            .show();
                    progressBar.setVisibility(View.GONE);
                }

            }
        } catch(Exception e){
            e.printStackTrace();
            //Popup to make sure all survey data is entered
            new AlertDialog.Builder(this)
                    .setTitle("Result Error")
                    .setMessage("Your result is not ready yet")
                    .setPositiveButton("Okay", null)
                    .show();
            progressBar.setVisibility(View.GONE);
        }
    }

    public static double getResultDouble() {
        return resultDouble;
    }

    public static LocalDateTime getTimestampResult(){
        return timestampResult;
    }

    private void openDashboardActivity() {
        Intent intent = new Intent(this, Dashboard.class);
        startActivity(intent);
        // make sure to close this activity, since we aren't returning to it
        this.finish();
    }
}