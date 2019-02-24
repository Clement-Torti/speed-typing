package com.example.speed_typing;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import com.example.speed_typing.model.Scores;
import com.example.speed_typing.model.Util.ScoreReader;
import com.example.speed_typing.model.Util.ScoreWriter;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

public class ScoreActivity extends BaseActivity {

    private Button returnBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);


        Log.d("jonathan","ScoreActivity");
        ArrayList<Scores> scores = ScoreReader.read("Scores.txt",getApplicationContext());
        Log.d("jonathan","      nom du score 1 " + scores.get(0).name());

        scores.add(new Scores("nom2",25,35,45,55,"path"));

        //ScoreWriter.write("Scores.txt",getApplicationContext(),scores);

        returnBtn = findViewById(R.id.returnBtn);
        configureNavigationBtn(returnBtn, AccueilActivity.class, new HashMap<String, Serializable>());

    }
}
