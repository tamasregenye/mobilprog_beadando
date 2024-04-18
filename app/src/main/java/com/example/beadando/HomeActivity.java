package com.example.beadando;

import static com.example.beadando.ActUtils.*;
import static com.example.beadando.ThemeManager.*;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class HomeActivity extends AppCompatActivity {
    CardView buttonPlay;
    CardView buttonAutoMode;
    CardView buttonLanguage;
    CardView buttonModes;
    CardView buttonCredits;
    CardView buttonLogout;
    ImageView imageView_mode;
    TextView textView_mode;
    TextView textview_auto_mode;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LanguageManager.loadLocale(this); // Load the saved language preference


        setContentView(R.layout.activity_main);
        ThemeManager.applyTheme(this);  // Apply the saved theme preference


        initComponents();
        updateUI();
        setOnclickListeners();
    }

    private void initComponents(){
        buttonPlay = findViewById(R.id.button_play);
        buttonAutoMode = findViewById(R.id.button_auto_mode);
        buttonLanguage = findViewById(R.id.button_language);
        buttonModes = findViewById(R.id.button_modes);
        buttonCredits = findViewById(R.id.button_credits);
        buttonLogout = findViewById(R.id.button_logout);

        imageView_mode= findViewById(R.id.imageView_mode);

        textView_mode= findViewById(R.id.textView_mode);
        textview_auto_mode= findViewById(R.id.textView_auto_mode);
    }

    private void setOnclickListeners(){
        buttonCredits.setOnClickListener(v -> getCredits());
        buttonAutoMode.setOnClickListener(v -> setAutoMode());
        buttonModes.setOnClickListener(v -> setMode());
        buttonLanguage.setOnClickListener(v -> changeLanguage());
        buttonLogout.setOnClickListener(v -> exitApp());
    }

    private void exitApp() {
        SensorManagerHelper.unregisterSensorListener();
        finishAffinity();
        System.exit(0);
    }

    private void getCredits(){
        startActivityAndFinishCurrent(this, CreditsActivity.class);
    }

    private void setAutoMode() {
        ThemeManager.toggleAutoMode(this);
        updateUI();
    }
    private void setMode() {
        ThemeManager.toggleTheme(this); // Toggle between light and dark mode
        updateUI();
    }

    private void changeLanguage() {
        LanguageManager.switchToNextLanguage(this);
        updateUI();
    }


    public void updateUI() {
        if (isAutoMode())
            textview_auto_mode.setText(getString(R.string.mode_auto) + " " + getString(R.string.auto_mode_enabled));
        else {
            textview_auto_mode.setText(getString(R.string.mode_auto)+" "+getString(R.string.auto_mode_disabled));
        }

        if (isNightMode()) {
            imageView_mode.setImageResource(R.drawable.ic_dark_mode);
            textView_mode.setText(getString(R.string.mode_dark));
        } else {
            imageView_mode.setImageResource(R.drawable.ic_light_mode);
            textView_mode.setText(getString(R.string.mode_light));
        }
    }
}