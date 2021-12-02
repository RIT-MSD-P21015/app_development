package com.example.logintest.ui.login;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import top.defaults.colorpicker.ColorPickerPopup;

import androidx.appcompat.app.AppCompatActivity;

import com.example.logintest.R;

public class SettingsStyle extends AppCompatActivity {

    private static Integer fontSize = 34;
    private static Integer primaryColor = 0xFFF44336;
    private static Integer secondaryColor = 0xFF00BCD4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings_style);

        TextView textViewFontSize = findViewById(R.id.textViewFontSize);
        TextView textViewStyle= findViewById(R.id.textViewStyle);
        Button returnDashboardButton = findViewById(R.id.button_return_main_settings_style);
        Button primaryColorButton = findViewById(R.id.buttonPrimaryColor);
        Button secondaryColorButton = findViewById(R.id.buttonSecondaryColor);
        View primaryColorView= findViewById(R.id.viewPrimaryColor);
        View secondaryColorView= findViewById(R.id.viewSecondaryColor);

        RadioGroup fontRadioGroup = findViewById(R.id.radioGroupFontSize);

        // Set color of views
        primaryColorView.setBackgroundColor(getPrimaryColor());
        secondaryColorView.setBackgroundColor(getSecondaryColor());

        // Set color of button backgrounds
        returnDashboardButton.setBackgroundColor(SettingsStyle.getPrimaryColor());

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

        primaryColorButton.setOnClickListener(
                v -> new ColorPickerPopup.Builder(SettingsStyle.this).initialColor(
                        getPrimaryColor()) // set initial color
                        // of the color
                        // picker dialog
                        .enableBrightness(
                                false) // enable color brightness
                        // slider or not
                        .enableAlpha(
                                false) // enable color alpha
                        // changer on slider or
                        // not
                        .okTitle(
                                "Choose") // this is top right
                        // Choose button
                        .cancelTitle(
                                "Cancel") // this is top left
                        // Cancel button which
                        // closes the
                        .showIndicator(
                                true) // this is the small box
                        // which shows the chosen
                        // color by user at the
                        // bottom of the cancel
                        // button
                        .showValue(
                                false) // this is the value which
                        // shows the selected
                        // color hex code
                        // the above all values can be made
                        // false to disable them on the
                        // color picker dialog.
                        .build()
                        .show(
                                v,
                                new ColorPickerPopup.ColorPickerObserver() {
                                    @Override
                                    public void
                                    onColorPicked(int color) {
                                        // set the color
                                        // which is returned
                                        // by the color
                                        // picker
                                        primaryColor = color;

                                        // now as soon as
                                        // the dialog closes
                                        // set the preview
                                        // box to returned
                                        // color
                                        primaryColorView.setBackgroundColor(primaryColor);
                                    }
                                }));
        secondaryColorButton.setOnClickListener(
                v -> new ColorPickerPopup.Builder(SettingsStyle.this).initialColor(
                        getSecondaryColor()) // set initial color
                        // of the color
                        // picker dialog
                        .enableBrightness(
                                false) // enable color brightness
                        // slider or not
                        .enableAlpha(
                                false) // enable color alpha
                        // changer on slider or
                        // not
                        .okTitle(
                                "Choose") // this is top right
                        // Choose button
                        .cancelTitle(
                                "Cancel") // this is top left
                        // Cancel button which
                        // closes the
                        .showIndicator(
                                true) // this is the small box
                        // which shows the chosen
                        // color by user at the
                        // bottom of the cancel
                        // button
                        .showValue(
                                false) // this is the value which
                        // shows the selected
                        // color hex code
                        // the above all values can be made
                        // false to disable them on the
                        // color picker dialog.
                        .build()
                        .show(
                                v,
                                new ColorPickerPopup.ColorPickerObserver() {
                                    @Override
                                    public void
                                    onColorPicked(int color) {
                                        // set the color
                                        // which is returned
                                        // by the color
                                        // picker
                                        secondaryColor = color;

                                        // now as soon as
                                        // the dialog closes
                                        // set the preview
                                        // box to returned
                                        // color
                                        secondaryColorView.setBackgroundColor(secondaryColor);
                                    }
                                }));

        textViewFontSize.setTextSize(fontSize);
        textViewStyle.setTextSize(fontSize);
    }

    public static Integer getFontSize() {
        return fontSize;
    }

    public static Integer getPrimaryColor() {
        return primaryColor;
    }

    public static Integer getSecondaryColor() {
        return secondaryColor;
    }

    private void openDashboardActivity() {
        Intent intent = new Intent(this, Dashboard.class);
        startActivity(intent);
        // make sure to close this activity, since we aren't returning to it
        this.finish();
    }

}