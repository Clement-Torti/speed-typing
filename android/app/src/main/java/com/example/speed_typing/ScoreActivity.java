package com.example.speed_typing;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;

import com.example.speed_typing.model.ScoreAdapter;
import com.example.speed_typing.model.Scores;
import com.example.speed_typing.model.Util.ScoreReader;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

public class ScoreActivity extends BaseActivity {
    private ListView scoresListView;
    private Button returnBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);

        // Configuration des boutons de navigation
        returnBtn = findViewById(R.id.returnBtn);
        configureNavigationBtn(returnBtn, AccueilActivity.class, new HashMap<String, Serializable>());

        // Configuration de la listView
        scoresListView = (ListView) findViewById(R.id.scoresListView);
        configureListView();



    }

    private void configureListView() {
        List<Scores> scores = ScoreReader.read(this);
        System.out.println("scores : " + scores.size());
        ScoreAdapter adapter = new ScoreAdapter(this, scores, getLayoutInflater());
        scoresListView.setAdapter(adapter);
    }





}





