package com.example.logintest.ui.login;

import android.os.Bundle;
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

        TextView textViewFontSize = (TextView) findViewById(R.id.textViewFontSize);
        TextView textViewStyle= (TextView) findViewById(R.id.textViewStyle);

        RadioGroup fontRadioGroup = (RadioGroup) findViewById(R.id.radioGroupFontSize);
        RadioButton checkedRadioButton = (RadioButton)fontRadioGroup.findViewById(fontRadioGroup.getCheckedRadioButtonId());

        // This overrides the radioGroup onCheckListener
        fontRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            public void onCheckedChanged(RadioGroup group, int checkedId)
            {
                // This will get the radiobutton that has changed in its check state
                RadioButton checkedRadioButton = (RadioButton)group.findViewById(checkedId);
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
            }
        });

        textViewFontSize.setTextSize(fontSize);
        textViewStyle.setTextSize(fontSize);
    }
    private void changeFont() {

        TextView textViewFontSize = findViewById(R.id.textViewFontSize);
        TextView textViewStyle= findViewById(R.id.textViewStyle);

        RadioButton fontSize20 = findViewById(R.id.radioButtonFontSmall);
        RadioButton fontSize34 = findViewById(R.id.radioButtonFontNormal);
        RadioButton fontSize48 = findViewById(R.id.radioButtonFontLarge);

        if (fontSize20.isChecked()){
            fontSize = 20;
        }
        else if (fontSize34.isChecked()){
            fontSize = 34;
        }
        else if (fontSize48.isChecked()){
            fontSize = 48;
        }

        textViewFontSize.setTextSize(fontSize);
        textViewStyle.setTextSize(fontSize);

    }

    public static Integer getFontSize() {
        return fontSize;
    }

}