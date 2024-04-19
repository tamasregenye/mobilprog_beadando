package com.example.beadando;

import static com.example.beadando.ActUtils.startActivityAndFinishCurrent;

import android.os.Bundle;
import android.os.Handler;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import androidx.appcompat.app.AppCompatActivity;

public class PlayActivity extends AppCompatActivity {

    LinearLayout mainMenuBtn;
    RelativeLayout playLayout;
    boolean lightsOn = false;
    long lightsOutTime = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);

        initComponents();
        setOnclickListeners();
        startGame();
    }

    private void initComponents() {
        mainMenuBtn = findViewById(R.id.button_mainmenu);
        playLayout = findViewById(R.id.layout_play);
    }

    private void setOnclickListeners() {
        mainMenuBtn.setOnClickListener(v -> goBackToMainMenu());
        playLayout.setOnClickListener(v -> clickOnLayout());
    }

    private void goBackToMainMenu() {
        startActivityAndFinishCurrent(this, HomeActivity.class);
    }

    private void clickOnLayout() {
        if (lightsOn) {
            long responseTime = System.currentTimeMillis() - lightsOutTime;
            // Calculate and display response time
            lightsOn = false;
            // Start the next round or end the game
        } else {
            // User clicked when lights were already off, ignore
        }
    }

    private void startGame() {
        // Start the game
        // Turn the lights on
        setLightsOn();
        // Set a random delay to turn the lights off
        new Handler().postDelayed(this::setLightsOff, getRandomDelay());
    }

    private void setLightsOn() {
        // Code to turn the lights on
        lightsOn = true;
    }

    private void setLightsOff() {
        // Code to turn the lights off
        lightsOutTime = System.currentTimeMillis();
    }

    private long getRandomDelay() {
        // Generate a random delay between 1 and 3 seconds
        return (long) (1000 + Math.random() * 2000);
    }
}
