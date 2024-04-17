package com.example.beadando;
import static com.example.beadando.ActUtils.*;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.os.Bundle;

public class HomeActivity extends AppCompatActivity {
    CardView buttonPlay;
    CardView buttonMultiplayer;
    CardView buttonProfiles;
    CardView buttonStats;
    CardView buttonCredits;
    CardView buttonLogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initComponents();
        setOnclickListeners();

    }

    private void initComponents(){
        buttonPlay = findViewById(R.id.button_play);
        buttonMultiplayer = findViewById(R.id.button_multiplayer);
        buttonProfiles = findViewById(R.id.button_profiles);
        buttonStats = findViewById(R.id.button_stats);
        buttonCredits = findViewById(R.id.button_credits);
        buttonLogout = findViewById(R.id.button_logout);
    }

    private void setOnclickListeners(){
        buttonCredits.setOnClickListener(v -> getCredits());
    }
    public void getCredits(){
        startActivityAndFinishCurrent(this, CreditsActivity.class);
    }
}