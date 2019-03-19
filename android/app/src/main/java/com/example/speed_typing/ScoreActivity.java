package com.example.speed_typing;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.speed_typing.model.ScoreAdapter;
import com.example.speed_typing.model.Scores;
import com.example.speed_typing.model.Util.ScoreReader;
import com.example.speed_typing.model.Util.ScoreWriter;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
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
        System.out.println("nbScores: " + scores.size());
        ScoreAdapter adapter = new ScoreAdapter(this, scores, getLayoutInflater());
        scoresListView.setAdapter(adapter);
    }





}





