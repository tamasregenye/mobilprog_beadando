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
        //set gameOverLayout invisible
        gameOverLayout.setVisibility(LinearLayout.INVISIBLE);

        // Start the game by turning on the first light
        new Handler().postDelayed(() -> setLightOn(light1), 1000); // 1 second delay
        // Schedule turning on the next light with a delay
        new Handler().postDelayed(() -> setLightOn(light2), 2000); // 2 second delay
        new Handler().postDelayed(() -> setLightOn(light3), 3000); // 3 seconds delay
        // Schedule turning the lights off after a random delay
        new Handler().postDelayed(this::setLightsOff, getRandomDelay() + 3000); // 3 seconds delay
        lightsOn = true;
    }

    private void setLightOn(ImageView light) {
        // Turn on the specified light
        light.setImageResource(R.drawable.ic_light_on);
    }

    private void setLightsOff() {
        // If all three lights are on, set them off and start the timer
        if (lightsOn) {
            light1.setImageResource(R.drawable.ic_light_off);
            light2.setImageResource(R.drawable.ic_light_off);
            light3.setImageResource(R.drawable.ic_light_off);
            lightsOutTime = System.currentTimeMillis();
            lightsOn = false;
        }
    }

    private long getRandomDelay() {
        // Generate a random delay between 0 and 5 seconds
        return (long) (0 + Math.random() * 5000);
    }
}
