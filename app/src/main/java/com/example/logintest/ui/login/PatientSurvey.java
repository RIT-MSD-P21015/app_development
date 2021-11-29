package com.example.logintest.ui.login;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.logintest.R;
import com.example.logintest.data.AppData;
import com.example.logintest.data.SendData;

import org.json.JSONObject;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.HashMap;

public class PatientSurvey extends AppCompatActivity {

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_survey);

        // Grab all the stuff on screen
        Button submitSurveyButton = findViewById(R.id.button_submit_survey);
        TextView textViewGender = findViewById(R.id.textViewSelectGender);
        TextView textViewAge = findViewById(R.id.textViewInsertAge);
        TextView textViewHeight = findViewById(R.id.textViewInsertHeight);
        TextView textViewWeight = findViewById(R.id.textViewInsertWeight);
        TextView textViewBodySide = findViewById(R.id.textViewAffectedSide);
        TextView textViewNumFalls = findViewById(R.id.textViewExperienceFall);
        TextView textViewMedication = findViewById(R.id.textViewMedication);
        TextView textViewProblemsHearing = findViewById(R.id.textViewProblemsHearing);
        TextView textViewUrineStool = findViewById(R.id.textViewLooseUrine);
        TextView textViewParkinsons = findViewById(R.id.textViewSufferParkinsons);
        TextView textViewWalkingAid = findViewById(R.id.textViewWalkingAid);
        TextView textViewTrapsHome = findViewById(R.id.textViewTrapsHome);
        RadioButton radioButtonMale = findViewById(R.id.radioButton_male);
        RadioButton radioButtonFemale = findViewById(R.id.radioButton_female);
        RadioButton radioButtonOther = findViewById(R.id.radioButton_other);
        RadioButton radioButtonRight = findViewById(R.id.radioButton_right_body_side);
        RadioButton radioButtonLeft = findViewById(R.id.radioButton_left_body_side);
        RadioButton radioButtonBoth = findViewById(R.id.radioButton_both_body_side);
        RadioButton radioButtonMedicationYes = findViewById(R.id.radioButton_t_medication);
        RadioButton radioButtonMedicationNo = findViewById(R.id.radioButton_f_medications);
        RadioButton radioButtonHearingYes = findViewById(R.id.radioButton_t_problems_hearing);
        RadioButton radioButtonHearingNo = findViewById(R.id.radioButton_f_problems_hearing);
        RadioButton radioButtonUrineYes = findViewById(R.id.radioButton_t_loose_urine);
        RadioButton radioButtonUrineNo = findViewById(R.id.radioButton_f_loose_urine);
        RadioButton radioButtonParkinsonsYes = findViewById(R.id.radioButton_t_suffer);
        RadioButton radioButtonParkinsonsNo = findViewById(R.id.radioButton_f_suffer);
        RadioButton radioButtonWalkingAidYes = findViewById(R.id.radioButton_t_walking_aid);
        RadioButton radioButtonWalkingAidNo = findViewById(R.id.radioButton_f_walking_aid);
        RadioButton radioButtonTrapsFallYes = findViewById(R.id.radioButton_t_traps_fall);
        RadioButton radioButtonTrapsFallNo = findViewById(R.id.radioButton_f_traps_fall);
        EditText editTextAge = findViewById(R.id.editTextNumber_age);
        EditText editTextFeet = findViewById(R.id.editTextNumber_feet);
        EditText editTextInches = findViewById(R.id.editTextNumber_inches);
        EditText editTextWeight = findViewById(R.id.editTextNumber_weight);
        EditText editTextNumFalls = findViewById(R.id.editTextNumberOfFalls);

        // Set size of textViews
        textViewGender.setTextSize(SettingsStyle.getFontSize());
        textViewAge.setTextSize(SettingsStyle.getFontSize());
        textViewHeight.setTextSize(SettingsStyle.getFontSize());
        textViewWeight.setTextSize(SettingsStyle.getFontSize());
        textViewBodySide.setTextSize(SettingsStyle.getFontSize());
        textViewNumFalls.setTextSize(SettingsStyle.getFontSize());
        textViewMedication.setTextSize(SettingsStyle.getFontSize());
        textViewProblemsHearing.setTextSize(SettingsStyle.getFontSize());
        textViewUrineStool.setTextSize(SettingsStyle.getFontSize());
        textViewParkinsons.setTextSize(SettingsStyle.getFontSize());
        textViewWalkingAid.setTextSize(SettingsStyle.getFontSize());
        textViewTrapsHome.setTextSize(SettingsStyle.getFontSize());

        // Set size of buttons
        submitSurveyButton.setTextSize(SettingsStyle.getFontSize());

        // Set size of radio buttons
        radioButtonMale.setTextSize(SettingsStyle.getFontSize());
        radioButtonFemale.setTextSize(SettingsStyle.getFontSize());
        radioButtonOther.setTextSize(SettingsStyle.getFontSize());
        radioButtonRight.setTextSize(SettingsStyle.getFontSize());
        radioButtonLeft.setTextSize(SettingsStyle.getFontSize());
        radioButtonBoth.setTextSize(SettingsStyle.getFontSize());
        radioButtonMedicationYes.setTextSize(SettingsStyle.getFontSize());
        radioButtonMedicationNo.setTextSize(SettingsStyle.getFontSize());
        radioButtonHearingYes.setTextSize(SettingsStyle.getFontSize());
        radioButtonHearingNo.setTextSize(SettingsStyle.getFontSize());
        radioButtonUrineYes.setTextSize(SettingsStyle.getFontSize());
        radioButtonUrineNo.setTextSize(SettingsStyle.getFontSize());
        radioButtonParkinsonsYes.setTextSize(SettingsStyle.getFontSize());
        radioButtonParkinsonsNo.setTextSize(SettingsStyle.getFontSize());
        radioButtonWalkingAidYes.setTextSize(SettingsStyle.getFontSize());
        radioButtonWalkingAidNo.setTextSize(SettingsStyle.getFontSize());
        radioButtonTrapsFallYes.setTextSize(SettingsStyle.getFontSize());
        radioButtonTrapsFallNo.setTextSize(SettingsStyle.getFontSize());

        // Set size of edit texts
        editTextAge.setTextSize(SettingsStyle.getFontSize());
        editTextFeet.setTextSize(SettingsStyle.getFontSize());
        editTextInches.setTextSize(SettingsStyle.getFontSize());
        editTextWeight.setTextSize(SettingsStyle.getFontSize());
        editTextNumFalls.setTextSize(SettingsStyle.getFontSize());

        // Set the on click listener
        submitSurveyButton.setOnClickListener(v -> submitSurvey());
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private void submitSurvey(){
        // Grab all the stuff on screen
        RadioButton radioButtonMale = findViewById(R.id.radioButton_male);
        RadioButton radioButtonFemale = findViewById(R.id.radioButton_female);
        RadioButton radioButtonOther = findViewById(R.id.radioButton_other);
        RadioButton radioButtonRight = findViewById(R.id.radioButton_right_body_side);
        RadioButton radioButtonLeft = findViewById(R.id.radioButton_left_body_side);
        RadioButton radioButtonBoth = findViewById(R.id.radioButton_both_body_side);
        RadioButton radioButtonMedicationYes = findViewById(R.id.radioButton_t_medication);
        RadioButton radioButtonMedicationNo = findViewById(R.id.radioButton_f_medications);
        RadioButton radioButtonHearingYes = findViewById(R.id.radioButton_t_problems_hearing);
        RadioButton radioButtonHearingNo = findViewById(R.id.radioButton_f_problems_hearing);
        RadioButton radioButtonUrineYes = findViewById(R.id.radioButton_t_loose_urine);
        RadioButton radioButtonUrineNo = findViewById(R.id.radioButton_f_loose_urine);
        RadioButton radioButtonParkinsonsYes = findViewById(R.id.radioButton_t_suffer);
        RadioButton radioButtonParkinsonsNo = findViewById(R.id.radioButton_f_suffer);
        RadioButton radioButtonWalkingAidYes = findViewById(R.id.radioButton_t_walking_aid);
        RadioButton radioButtonWalkingAidNo = findViewById(R.id.radioButton_f_walking_aid);
        RadioButton radioButtonTrapsFallYes = findViewById(R.id.radioButton_t_traps_fall);
        RadioButton radioButtonTrapsFallNo = findViewById(R.id.radioButton_f_traps_fall);
        EditText editTextAge = findViewById(R.id.editTextNumber_age);
        EditText editTextFeet = findViewById(R.id.editTextNumber_feet);
        EditText editTextInches = findViewById(R.id.editTextNumber_inches);
        EditText editTextWeight = findViewById(R.id.editTextNumber_weight);
        EditText editTextNumFalls = findViewById(R.id.editTextNumberOfFalls);

        try {
            submitData(
                    radioButtonMale.isChecked(), radioButtonFemale.isChecked(), radioButtonOther.isChecked(),
                    radioButtonRight.isChecked(), radioButtonLeft.isChecked(), radioButtonBoth.isChecked(),
                    radioButtonMedicationYes.isChecked(), radioButtonMedicationNo.isChecked(),
                    radioButtonHearingYes.isChecked(), radioButtonHearingNo.isChecked(),
                    radioButtonUrineYes.isChecked(), radioButtonUrineNo.isChecked(),
                    radioButtonParkinsonsYes.isChecked(), radioButtonParkinsonsNo.isChecked(),
                    radioButtonWalkingAidYes.isChecked(), radioButtonWalkingAidNo.isChecked(),
                    radioButtonTrapsFallYes.isChecked(), radioButtonTrapsFallNo.isChecked(),
                    Integer.parseInt(editTextAge.getText().toString()),
                    Integer.parseInt(editTextFeet.getText().toString()),
                    Integer.parseInt(editTextInches.getText().toString()),
                    Integer.parseInt(editTextWeight.getText().toString()),
                    Integer.parseInt(editTextNumFalls.getText().toString())
            );
        } catch (Exception e){
            e.printStackTrace();
            //Popup to make sure all survey data is entered
            new AlertDialog.Builder(this)
                    .setTitle("Survey Error")
                    .setMessage("Please make sure no entries are left blank")
                    .setPositiveButton("Okay", null)
                    .show();
        }
    }
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private void submitData(
            boolean male, boolean female, boolean other,
            boolean right, boolean left, boolean both,
            boolean medicationYes, boolean medicationNo,
            boolean hearingYes, boolean hearingNo,
            boolean urineYes, boolean urineNo,
            boolean parkinsonsYes, boolean parkinsonsNo,
            boolean walkingAidYes, boolean walkingAidNo,
            boolean trapsFallYes, boolean trapsFallNo,
            int age, int feet, int inches, int weight, int numFalls
    ) {

        // Construct the JSON payload
        HashMap<String, Integer> surveyFields = new HashMap<>();
        surveyFields.put("gender", 0);
        surveyFields.put("strokeSide", 0);
        surveyFields.put("medication", 0);
        surveyFields.put("hearing", 0);
        surveyFields.put("urine", 0);
        surveyFields.put("parkinsons", 0);
        surveyFields.put("walkingAid", 0);
        surveyFields.put("trapsFall", 0);
        surveyFields.put("age", age);
        surveyFields.put("feet", feet);
        surveyFields.put("inches", inches);
        surveyFields.put("weight", weight);
        surveyFields.put("numFalls", numFalls);

        // update gender: male 0, female 1, other 2
        if(female) {
            surveyFields.put("gender", 1);
        }
        else if(other) {
            surveyFields.put("gender", 2);
        }

        // update strokeSide: left 0, right 1, both 2
        if(right) {
            surveyFields.put("strokeSide", 1);
        }
        else if(both) {
            surveyFields.put("strokeSide", 2);
        }

        // update medication
        if(medicationYes) {
            surveyFields.put("medication", 1);
        }

        // update hearing aids
        if(hearingYes) {
            surveyFields.put("hearing", 1);
        }

        // update urine
        if(urineYes) {
            surveyFields.put("urine", 1);
        }

        // update parkinsons
        if(parkinsonsYes) {
            surveyFields.put("parkinsons", 1);
        }

        // update walkingAid
        if(walkingAidYes) {
            surveyFields.put("walkingAid", 1);
        }

        // update trapsFall
        if(trapsFallYes) {
            surveyFields.put("trapsFall", 1);
        }

        String surveyJson = new JSONObject(surveyFields).toString();
        byte[] authBytes = surveyJson.getBytes(StandardCharsets.UTF_8);
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            surveyJson = Base64.getEncoder().encodeToString(authBytes);
        }

        AppData tokenData = (AppData) getApplicationContext();

        // fire and forget submit data to database in async
        new SendData().execute("survey", surveyJson, tokenData.getToken());

        //Popup that shows the server was submitted successfully
        new AlertDialog.Builder(this)
                .setTitle("Survey Sent")
                .setMessage("Survey was sent successfully")
                .setPositiveButton("Okay", (dialog, which) -> openDashboardActivity())
                .show();

    }

    private void openDashboardActivity() {
        Intent intent = new Intent(this, Dashboard.class);
        startActivity(intent);
        // make sure to close this activity, since we aren't returning to it
        this.finish();
    }

    // Override back button for confirm leave
    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
//                .setIcon(android.R.drawable.ic_dialog_alert)
                .setTitle("Cancel Survey")
                .setMessage("Are you sure you want to cancel survey?")
                .setPositiveButton("Yes", (dialog, which) -> finish())
                .setNegativeButton("No", null)
                .show();
    }
}