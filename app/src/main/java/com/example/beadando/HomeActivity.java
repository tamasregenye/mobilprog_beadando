package com.example.beadando;
import static android.content.ContentValues.TAG;
import static com.example.beadando.ActUtils.*;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.cardview.widget.CardView;

import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Locale;

public class HomeActivity extends AppCompatActivity {
    CardView buttonPlay;
    CardView buttonMultiplayer;
    CardView buttonLanguage;
    CardView buttonModes;
    CardView buttonCredits;
    CardView buttonLogout;
    ImageView imageView_mode;
    TextView textView_mode;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        sharedPreferences = getSharedPreferences("MODE", MODE_PRIVATE);
        super.onCreate(savedInstanceState);
        LanguageManager.loadLocale(this); // Load the saved language preference
        setContentView(R.layout.activity_main);

        initComponents();
        setOnclickListeners();
    }

    private void initComponents(){
        buttonPlay = findViewById(R.id.button_play);
        buttonMultiplayer = findViewById(R.id.button_multiplayer);
        buttonLanguage = findViewById(R.id.button_language);
        buttonModes = findViewById(R.id.button_modes);
        buttonCredits = findViewById(R.id.button_credits);
        buttonLogout = findViewById(R.id.button_logout);
        imageView_mode= findViewById(R.id.imageView_mode);
        textView_mode= findViewById(R.id.textView_mode);

        boolean isNightMode = sharedPreferences.getBoolean("night", false); //default is light mode
        setNightMode(isNightMode);
        updateUI(isNightMode);
    }

    private void setOnclickListeners(){
        buttonCredits.setOnClickListener(v -> getCredits());
        buttonModes.setOnClickListener(v -> setMode());
        buttonLanguage.setOnClickListener(v -> changeLanguage());
    }
    private void getCredits(){
        startActivityAndFinishCurrent(this, CreditsActivity.class);
    }
    private void setMode() {
        boolean newMode = !isNightMode();
        setNightMode(newMode);
        editor = sharedPreferences.edit();
        editor.putBoolean("night", newMode);
        editor.apply();
        updateUI(newMode);
    }

    private void changeLanguage() {
        LanguageManager.switchToNextLanguage(this);
        recreate();
    }


    private void updateUI(boolean isNightMode) {
        if (isNightMode) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
            imageView_mode.setImageResource(R.drawable.ic_dark_mode);
            textView_mode.setText(getString(R.string.mode_dark)); // Use getString() to get string resources
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
            imageView_mode.setImageResource(R.drawable.ic_light_mode);
            textView_mode.setText(getString(R.string.mode_light)); // Use getString() to get string resources
        }
    }
}