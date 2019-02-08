package com.example.speed_typing;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

public class PauseActivity extends BaseActivity {

    Button cancelBtn;
    Button quitBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pause);

        cancelBtn = findViewById(R.id.cancelBtn);
        quitBtn = findViewById(R.id.quitBtn);
        configureNavigationBtn(cancelBtn, GameActivity.class);
        configureNavigationBtn(quitBtn, AccueilActivity.class);


    }
}
