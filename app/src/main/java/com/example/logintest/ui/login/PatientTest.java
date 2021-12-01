package com.example.logintest.ui.login;

import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.logintest.R;

import java.util.Locale;

public class PatientTest extends AppCompatActivity {

    TextToSpeech tts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_test);

        tts=new TextToSpeech(PatientTest.this, status -> {
            if(status == TextToSpeech.SUCCESS){
                tts.setLanguage(Locale.US);
                if(Settings.getTextToSpeechBool()) {
                    ConvertTextToSpeech();
                }
            }
        });

        // Grab all the stuff on screen
        Button startTestButton = findViewById(R.id.button_start_test_first);
        Button returnDashboardButton = findViewById(R.id.button_return_main_patient_test);
        TextView textViewTestPageInstr1 = findViewById(R.id.textViewTestPageInstr1);
        TextView textViewTestPageInstr2 = findViewById(R.id.textViewTestPageInstr2);
        TextView textViewTestPageInstr3 = findViewById(R.id.textViewTestPageInstr3);
        TextView textViewTestPageInstr4 = findViewById(R.id.textViewTestPageInstr4);

        // Set size of textViews
        textViewTestPageInstr1.setTextSize(SettingsStyle.getFontSize());
        textViewTestPageInstr2.setTextSize(SettingsStyle.getFontSize());
        textViewTestPageInstr3.setTextSize(SettingsStyle.getFontSize());
        textViewTestPageInstr4.setTextSize(SettingsStyle.getFontSize());

        // Set size of buttons
        startTestButton.setTextSize(SettingsStyle.getFontSize());
        returnDashboardButton.setTextSize(SettingsStyle.getFontSize());

        // Set the on click listener
        startTestButton.setOnClickListener(v -> openTestActivity());
        returnDashboardButton.setOnClickListener(v -> openDashboardActivity());

    }

    private void ConvertTextToSpeech() {
        TextView textViewTestPageInstr1 = findViewById(R.id.textViewTestPageInstr1);
        TextView textViewTestPageInstr2 = findViewById(R.id.textViewTestPageInstr2);
        TextView textViewTestPageInstr3 = findViewById(R.id.textViewTestPageInstr3);
        TextView textViewTestPageInstr4 = findViewById(R.id.textViewTestPageInstr4);

        String toSpeakBox1 = textViewTestPageInstr1.getText().toString();
        String toSpeakBox2 = textViewTestPageInstr2.getText().toString();
        String toSpeakBox3 = textViewTestPageInstr3.getText().toString();
        String toSpeakBox4 = textViewTestPageInstr4.getText().toString();

        tts.speak(toSpeakBox1, TextToSpeech.QUEUE_FLUSH, null);
        tts.speak(toSpeakBox2, TextToSpeech.QUEUE_ADD, null);
        tts.speak(toSpeakBox3, TextToSpeech.QUEUE_ADD, null);
        tts.speak(toSpeakBox4, TextToSpeech.QUEUE_ADD, null);
    }



    @Override
    protected void onResume() {
        super.onResume();

        // Grab all the stuff on screen
        Button startTestButton = findViewById(R.id.button_start_test_first);
        Button returnDashboardButton = findViewById(R.id.button_return_main_patient_test);
        TextView textViewTestPageInstr1 = findViewById(R.id.textViewTestPageInstr1);
        TextView textViewTestPageInstr2 = findViewById(R.id.textViewTestPageInstr2);
        TextView textViewTestPageInstr3 = findViewById(R.id.textViewTestPageInstr3);
        TextView textViewTestPageInstr4 = findViewById(R.id.textViewTestPageInstr4);

        // Set size of textViews
        textViewTestPageInstr1.setTextSize(SettingsStyle.getFontSize());
        textViewTestPageInstr2.setTextSize(SettingsStyle.getFontSize());
        textViewTestPageInstr3.setTextSize(SettingsStyle.getFontSize());
        textViewTestPageInstr4.setTextSize(SettingsStyle.getFontSize());

        // Set size of buttons
        startTestButton.setTextSize(SettingsStyle.getFontSize());
        returnDashboardButton.setTextSize(SettingsStyle.getFontSize());
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

    // Make sure text to speech stops when this activity is paused
    @Override
    protected void onPause() {
        super.onPause();

        tts.stop();
        tts.shutdown();
    }
}