package com.example.speed_typing;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

import java.io.Serializable;
import java.util.HashMap;

public class PauseActivity extends BaseActivity {

    private Button cancelBtn;
    private Button quitBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pause);

        cancelBtn = findViewById(R.id.cancelBtn);
        quitBtn = findViewById(R.id.quitBtn);
        configureNavigationBtn(cancelBtn, GameActivity.class, new HashMap<String, Serializable>());
        configureNavigationBtn(quitBtn, AccueilActivity.class, new HashMap<String, Serializable>());


    }
}
