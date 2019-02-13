package com.example.speed_typing;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.speed_typing.model.WordDatabase;
import com.example.speed_typing.model.WordType;

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

       /* WordDatabase wd = new WordDatabase(WordType.Francais,getApplicationContext());        //mode debug => file not found
        Log.d("test",wd.getRandomWord().toString());
        Log.d("test",wd.getRandomWord().toString());
        Log.d("test",wd.getRandomWord().toString());
        Log.d("test",wd.getRandomWord().toString());
        */
    }
}
