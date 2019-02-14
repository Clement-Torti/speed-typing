package com.example.speed_typing;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

public class ScoreActivity extends BaseActivity {

    private Button returnBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);

        returnBtn = findViewById(R.id.returnBtn);
        configureNavigationBtn(returnBtn, AccueilActivity.class);

    }
}
