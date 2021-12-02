package com.example.logintest.ui.login;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.example.logintest.R;
import com.example.logintest.data.AppData;
import com.example.logintest.data.NetworkManager;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Base64;

public class PatientData extends AppCompatActivity {

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_data);

        // Grab all the stuff on screen
        Button returnDashboardButton = findViewById(R.id.button_return_main_patient_data);
        TextView textViewPatientData = findViewById(R.id.textViewPatientData);
        TextView textViewGender = findViewById(R.id.textViewDataGender);
        TextView textViewAge = findViewById(R.id.textViewDataAge);
        TextView textViewHeight = findViewById(R.id.textViewDataHeight);
        TextView textViewWeight = findViewById(R.id.textViewDataWeight);
        TextView textViewBodySide = findViewById(R.id.textViewDataBodySide);
        TextView textViewNumFalls = findViewById(R.id.textViewDataNumFalls);
        TextView textViewMedication= findViewById(R.id.textViewDataMedication);
        TextView textViewHearing= findViewById(R.id.textViewDataHearing);
        TextView textViewLooseUrine= findViewById(R.id.textViewDataLooseUrine);
        TextView textViewSuffer= findViewById(R.id.textViewDataSuffer);
        TextView textViewWalking= findViewById(R.id.textViewDataWalking);
        TextView textViewTrapHouse= findViewById(R.id.textViewDataTrapsHome);

        TextView textViewPatientGender = findViewById(R.id.textViewPatientGender);
        TextView textViewPatientAge = findViewById(R.id.textViewPatientAge);
        TextView textViewPatientHeight = findViewById(R.id.textViewPatientHeight);
        TextView textViewPatientWeight = findViewById(R.id.textViewPatientWeight);
        TextView textViewPatientBodySide = findViewById(R.id.textViewPatientBodySide);
        TextView textViewPatientNumFalls = findViewById(R.id.textViewPatientNumFalls);
        TextView textViewPatientMedication= findViewById(R.id.textViewPatientMeds);
        TextView textViewPatientHearing= findViewById(R.id.textViewPatientHearing);
        TextView textViewPatientLooseUrine= findViewById(R.id.textViewPatientUrine);
        TextView textViewPatientSuffer= findViewById(R.id.textViewPatientSuffer);
        TextView textViewPatientWalking= findViewById(R.id.textViewPatientWalking);
        TextView textViewPatientTrapHouse= findViewById(R.id.textViewPatientFallTraps);

        // Set size of textViews
        textViewPatientData.setTextSize(SettingsStyle.getFontSize());
        textViewGender.setTextSize(SettingsStyle.getFontSize());
        textViewAge.setTextSize(SettingsStyle.getFontSize());
        textViewHeight.setTextSize(SettingsStyle.getFontSize());
        textViewWeight.setTextSize(SettingsStyle.getFontSize());
        textViewBodySide.setTextSize(SettingsStyle.getFontSize());
        textViewNumFalls.setTextSize(SettingsStyle.getFontSize());
        textViewMedication.setTextSize(SettingsStyle.getFontSize());
        textViewHearing.setTextSize(SettingsStyle.getFontSize());
        textViewLooseUrine.setTextSize(SettingsStyle.getFontSize());
        textViewSuffer.setTextSize(SettingsStyle.getFontSize());
        textViewWalking.setTextSize(SettingsStyle.getFontSize());
        textViewTrapHouse.setTextSize(SettingsStyle.getFontSize());
        textViewPatientGender.setTextSize(SettingsStyle.getFontSize());
        textViewPatientAge.setTextSize(SettingsStyle.getFontSize());
        textViewPatientHeight.setTextSize(SettingsStyle.getFontSize());
        textViewPatientWeight.setTextSize(SettingsStyle.getFontSize());
        textViewPatientBodySide.setTextSize(SettingsStyle.getFontSize());
        textViewPatientNumFalls.setTextSize(SettingsStyle.getFontSize());
        textViewPatientMedication.setTextSize(SettingsStyle.getFontSize());
        textViewPatientHearing.setTextSize(SettingsStyle.getFontSize());
        textViewPatientLooseUrine.setTextSize(SettingsStyle.getFontSize());
        textViewPatientSuffer.setTextSize(SettingsStyle.getFontSize());
        textViewPatientWalking.setTextSize(SettingsStyle.getFontSize());
        textViewPatientTrapHouse.setTextSize(SettingsStyle.getFontSize());


        // Set size of buttons
        returnDashboardButton.setTextSize(SettingsStyle.getFontSize());

        // Set the on click listener
        returnDashboardButton.setOnClickListener(v -> openDashboardActivity());

        // Set color of button backgrounds
        returnDashboardButton.setBackgroundColor(SettingsStyle.getPrimaryColor());

        try {
            String json = getData();
            JSONObject jsonObject = new JSONObject(json);
            String survey = jsonObject.getString("survey");

            byte[] decodedBytes = Base64.getDecoder().decode(survey);
            String decodedString = new String(decodedBytes);
            JSONObject surveyJson = new JSONObject(decodedString);
            String gender = surveyJson.getString("gender");
            String strokeSide = surveyJson.getString("strokeSide");
            String medication = surveyJson.getString("medication");
            String hearing = surveyJson.getString("hearing");
            String urine = surveyJson.getString("urine");
            String parkinsons = surveyJson.getString("parkinsons");
            String walkingAid = surveyJson.getString("walkingAid");
            String trapsFall = surveyJson.getString("trapsFall");
            String age = surveyJson.getString("age");
            String feet = surveyJson.getString("feet");
            String inches = surveyJson.getString("inches");
            String weight = surveyJson.getString("weight");
            String numFalls = surveyJson.getString("numFalls");

            // Decode true/false
            if (Integer.parseInt(gender) == 1){
                textViewGender.setText("Female");
            }
            else if (Integer.parseInt(gender) == 2){
                textViewGender.setText("Other");
            }
            else{
                textViewGender.setText("Male");
            }

            textViewAge.setText(age);
            textViewHeight.setText(feet + "'" + inches + "''");
            if (strokeSide.equals("1")){
                textViewBodySide.setText("Right side");
            }
            else if(strokeSide.equals("2")){
                textViewBodySide.setText("Both sides");
            }
            else{
                textViewBodySide.setText("Left Side");
            }
            textViewWeight.setText(weight + "lbs");
            textViewNumFalls.setText(numFalls);
            if (medication.equals("1")){
                textViewMedication.setText("True");
            }
            else{
                textViewMedication.setText("False");
            }
            if (hearing.equals("1")){
                textViewHearing.setText("True");
            }
            else{
                textViewHearing.setText("False");
            }
            if (urine.equals("1")){
                textViewLooseUrine.setText("True");
            }
            else{
                textViewLooseUrine.setText("False");
            }
            if (parkinsons.equals("1")){
                textViewSuffer.setText("True");
            }
            else{
                textViewSuffer.setText("False");
            }
            if (walkingAid.equals("1")){
                textViewWalking.setText("True");
            }
            else{
                textViewWalking.setText("False");
            }
            if (trapsFall.equals("1")){
                textViewTrapHouse.setText("True");
            }
            else{
                textViewTrapHouse.setText("False");
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        // Grab all the stuff on screen
        Button returnDashboardButton = findViewById(R.id.button_return_main_patient_data);
        TextView textViewPatientData = findViewById(R.id.textViewPatientData);
        TextView textViewGender = findViewById(R.id.textViewDataGender);
        TextView textViewAge = findViewById(R.id.textViewDataAge);
        TextView textViewHeight = findViewById(R.id.textViewDataHeight);
        TextView textViewWeight = findViewById(R.id.textViewDataWeight);
        TextView textViewBodySide = findViewById(R.id.textViewDataBodySide);
        TextView textViewNumFalls = findViewById(R.id.textViewDataNumFalls);
        TextView textViewMedication= findViewById(R.id.textViewDataMedication);
        TextView textViewHearing= findViewById(R.id.textViewDataHearing);
        TextView textViewLooseUrine= findViewById(R.id.textViewDataLooseUrine);
        TextView textViewSuffer= findViewById(R.id.textViewDataSuffer);
        TextView textViewWalking= findViewById(R.id.textViewDataWalking);
        TextView textViewTrapHouse= findViewById(R.id.textViewDataTrapsHome);

        // Set size of textViews
        textViewPatientData.setTextSize(SettingsStyle.getFontSize());

        // Set size of buttons
        returnDashboardButton.setTextSize(SettingsStyle.getFontSize());
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private String getData() throws IOException, JSONException {
        /// Set the patient data
        AppData tokenData = (AppData) getApplicationContext();

        String json = NetworkManager.sendGET("/api/user?survey=1&tests=0", tokenData.getToken());
        Log.e("JSON", json);
        return json;
    }

    private void openDashboardActivity() {
        Intent intent = new Intent(this, Dashboard.class);
        startActivity(intent);
        // make sure to close this activity, since we aren't returning to it
        this.finish();
    }

}