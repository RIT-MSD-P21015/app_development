package com.example.logintest.ui.login;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.logintest.R;

public class PatientSurvey extends AppCompatActivity {

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
        submitSurveyButton.setOnClickListener(v -> openDashboardActivity());
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