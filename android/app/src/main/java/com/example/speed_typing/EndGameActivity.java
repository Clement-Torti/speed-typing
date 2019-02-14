package com.example.speed_typing;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

public class EndGameActivity extends BaseActivity {

    private Button homeBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end_game);

        homeBtn = findViewById(R.id.homeBtn);
        configureNavigationBtn(homeBtn, AccueilActivity.class);

    }
}
