package com.example.speed_typing;

import android.app.ActionBar;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class GameActivity extends BaseActivity {

    Button pauseBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        pauseBtn = findViewById(R.id.pauseBtn);
        configureNavigationBtn(pauseBtn, PauseActivity.class);

    }
}
