package com.example.speed_typing;

import android.app.ActionBar;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import java.io.Serializable;
import java.util.HashMap;

public class GameActivity extends BaseActivity {

    private Button pauseBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        // Cache la bar du haut
        this.getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        this.getSupportActionBar().hide();


        pauseBtn = findViewById(R.id.pauseBtn);
        configureNavigationBtn(pauseBtn, PauseActivity.class, new HashMap<String, Serializable>());

    }
}
