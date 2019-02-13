package com.example.speed_typing;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.speed_typing.model.Word;
import com.example.speed_typing.model.WordDatabase;
import com.example.speed_typing.model.WordFactory;
import com.example.speed_typing.model.WordType;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

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

    @Override
    protected void onResume() {
        super.onResume();


        WordDatabase wd = WordFactory.createWordDatabase(getApplicationContext(), WordType.Anglais);
        System.out.println(wd.getRandomWord());
        System.out.println(wd.getRandomWord());
        System.out.println(wd.getRandomWord());
    }
}
