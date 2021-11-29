package com.example.logintest.ui.login;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.logintest.R;

public class SettingsStyle extends AppCompatActivity {

    private static Integer fontSize = 34;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings_style);

        TextView textViewFontSize = findViewById(R.id.textViewFontSize);
        TextView textViewStyle= findViewById(R.id.textViewStyle);
        Button returnDashboardButton = findViewById(R.id.button_return_main_settings_style);

        RadioGroup fontRadioGroup = findViewById(R.id.radioGroupFontSize);

        // Set the on click listener
        returnDashboardButton.setOnClickListener(v -> openDashboardActivity());

        // This overrides the radioGroup onCheckListener
        fontRadioGroup.setOnCheckedChangeListener((group, checkedId) -> {
            // This will get the radiobutton that has changed in its check state
            RadioButton checkedRadioButton = group.findViewById(checkedId);
            // This puts the value (true/false) into the variable
            boolean isChecked = checkedRadioButton.isChecked();
            // If the radiobutton that has changed in check state is now checked...
            if (isChecked)
            {
                if (checkedRadioButton.getText().equals("Small")){
                    fontSize = 24;
                }
                else if (checkedRadioButton.getText().equals("Normal")){
                    fontSize = 34;
                }
                else if (checkedRadioButton.getText().equals("Large")){
                    fontSize = 44;
                }

                textViewFontSize.setTextSize(fontSize);
                textViewStyle.setTextSize(fontSize);
            }
        });

        textViewFontSize.setTextSize(fontSize);
        textViewStyle.setTextSize(fontSize);
    }

    public static Integer getFontSize() {
        return fontSize;
    }

    private void openDashboardActivity() {
        Intent intent = new Intent(this, Dashboard.class);
        startActivity(intent);
        // make sure to close this activity, since we aren't returning to it
        this.finish();
    }

}