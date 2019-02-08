package com.example.speed_typing;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AccueilActivity extends BaseActivity {

    Button startBtn;
    Button scoresBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accueil);

        startBtn = findViewById(R.id.startBtn);
        scoresBtn = findViewById(R.id.scoresBtn);
        configureNavigationBtn(startBtn, GameActivity.class);
        configureNavigationBtn(scoresBtn, ScoreActivity.class);

    }
}
