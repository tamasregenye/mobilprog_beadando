package com.example.beadando;

import static com.example.beadando.ActUtils.*;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

public class CreditsActivity extends AppCompatActivity {
    LinearLayout mainMenuBtn;
    TextView textViewGit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_credits);

        initComponents();
        setOnclickListeners();
    }

    private void initComponents() {
        mainMenuBtn = findViewById(R.id.button_mainmenu);
        textViewGit = findViewById(R.id.textview_git);
    }

    private void setOnclickListeners() {
        mainMenuBtn.setOnClickListener(v -> goBackToMainMenu());

        textViewGit.setOnClickListener(v -> openGitProfile());
    }

    private void goBackToMainMenu() {
        startActivityAndFinishCurrent(this, HomeActivity.class);
    }

    private void openGitProfile() {
        String gitLink = "https://github.com/tamasregenye";
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(gitLink));
        startActivity(intent);
    }
}