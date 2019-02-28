package com.example.speed_typing;

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

        /*
        ArrayList<Scores> scores = ScoreReader.read("Scores.txt", getApplicationContext());
        Log.d("jonathan","      nom du score 1 " + scores.get(0).name());

        scores.add(new Scores("nom2",25,35,45,55,"path"));

        //ScoreWriter.write("Scores.txt",getApplicationContext(),scores);
        */

        // Configuration des boutons de navigation
        returnBtn = findViewById(R.id.returnBtn);
        configureNavigationBtn(returnBtn, AccueilActivity.class, new HashMap<String, Serializable>());

        // Configuration de la listView
        scoresListView = (ListView) findViewById(R.id.scoresListView);
        configureListView();

    }

    private void configureListView() {
        List<Scores> scores = new ArrayList<>();
        scores.add(new Scores("clement", 1, 2, 3, 4));
        scores.add(new Scores("jonathan", 3, 2, 1, 0));
        scores.add(new Scores("florent", 1, 2, 4, 8));

        scoresListView.setAdapter(new ArrayAdapter<Scores>(this, R.layout.score_list_view_layout));
    }







    public class ScoreAdapter extends BaseAdapter {

        List<Scores> scores;

        public ScoreAdapter(List<Scores> scores) {
            this.scores = scores;
        }

        @Override
        public int getCount() {
            return scores.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            convertView = getLayoutInflater().inflate(R.layout.score_list_view_layout, null);
            TextView nameView = (TextView) convertView.findViewById(R.id.nameView);

            nameView.setText(scores.get(position).getName());
            return null;
        }
    }


}





