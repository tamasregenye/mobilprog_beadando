package com.example.beadando;

import static com.example.beadando.ActUtils.startActivityAndFinishCurrent;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class PlayActivity extends AppCompatActivity {

    LinearLayout mainMenuBtn;
    RelativeLayout playLayout, gameOverLayout;
    TextView textviewGameOver;
    Button playAgainBtn;
    boolean lightsOn = false;
    boolean isGameActive = false;
    long lightsOutTime = 0;
    long clickOnTime = 0;
    ImageView light1, light2, light3;
    private Handler mHandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);

        initComponents();
        setOnclickListeners();
    }

    private void initComponents() {
        mainMenuBtn = findViewById(R.id.button_mainmenu);
        playAgainBtn = findViewById(R.id.button_play_again);
        playLayout = findViewById(R.id.layout_play);
        gameOverLayout = findViewById(R.id.layout_gameover);

        textviewGameOver = findViewById(R.id.textview_gameover);

        light1 = findViewById(R.id.light1);
        light2 = findViewById(R.id.light2);
        light3 = findViewById(R.id.light3);
    }

    private void setOnclickListeners() {
        mainMenuBtn.setOnClickListener(v -> goBackToMainMenu());
        playLayout.setOnClickListener(v -> clickOnLayout());
        playAgainBtn.setOnClickListener(v -> startGame());
    }

    private void goBackToMainMenu() {
        startActivityAndFinishCurrent(this, HomeActivity.class);
    }

    private void clickOnLayout() {
        if(isGameActive)
        {
            if (lightsOn) {
                //user clicked when lights were on, game over
                //wait until a
                Log.d("ReactionTime", "Too early!");
                textviewGameOver.setText(getString(R.string.early));
                gameOverLayout.setVisibility(LinearLayout.VISIBLE);

            } else {
                // User clicked when lights were already off, measure reaction time
                clickOnTime = System.currentTimeMillis();
                long reactionTime = clickOnTime - lightsOutTime;
                Log.d("ReactionTime", "Lights out after " + reactionTime + " ms");
                textviewGameOver.setText(getString(R.string.reactiontime) + ": "+reactionTime+" ms");
                gameOverLayout.setVisibility(LinearLayout.VISIBLE);

            }
            isGameActive = false;
        }

    }

    private void startGame() {
        isGameActive = true;
        gameOverLayout.setVisibility(LinearLayout.INVISIBLE);
        mHandler.removeCallbacksAndMessages(null);
        setLightsOff();

        // Start the game by turning on each light after a second delay
        mHandler.postDelayed(() -> {
            if (isGameActive) {
                setLightOn(light1);
            }
        }, 1000);
        mHandler.postDelayed(() -> {
            if (isGameActive) {
                setLightOn(light2);
            }
        }, 2000);
        mHandler.postDelayed(() -> {
            if (isGameActive) {
                setLightOn(light3);
            }
        }, 3000);

        // Schedule turning the lights off after a random delay
        mHandler.postDelayed(() -> {
            if (isGameActive) {
                setLightsOff();
            }
        }, getRandomDelay() + 3000);
        lightsOn = true;
    }

    private void setLightOn(ImageView light) {
        // Turn on the specified light
        light.setImageResource(R.drawable.ic_light_on);
    }

    private void setLightsOff() {
        light1.setImageResource(R.drawable.ic_light_off);
        light2.setImageResource(R.drawable.ic_light_off);
        light3.setImageResource(R.drawable.ic_light_off);
        lightsOutTime = System.currentTimeMillis();
        lightsOn = false;
    }

    private long getRandomDelay() {
        // Generate a random delay between 0.1 and 5 seconds
        return (long) (100 + Math.random() * 5000);
    }
}
