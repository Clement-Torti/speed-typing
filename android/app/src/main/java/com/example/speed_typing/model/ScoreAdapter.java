package com.example.speed_typing.model;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.speed_typing.R;
import com.example.speed_typing.model.Util.ScoreReader;

import java.text.DecimalFormat;
import java.util.List;

public class ScoreAdapter extends BaseAdapter {

    private List<Scores> scores;
    private Context context;
    private LayoutInflater layoutInflater;

    public ScoreAdapter(Context context, List<Scores> scores, LayoutInflater layoutInflater) {
        this.context = context;
        this.scores = scores;
        this.layoutInflater = layoutInflater;
    }

    @Override
    public int getCount() {
        return scores.size();
    }

    @Override
    public Object getItem(int position) {
        return scores.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Scores score = scores.get(position);

        // On récupère la cellule
        convertView = layoutInflater.inflate(R.layout.score_list_view_layout, null);


        // On ajoute les éléments de vue
        TextView nameView = (TextView) convertView.findViewById(R.id.nameView);
        nameView.setText(score.getName());

        TextView timeView = (TextView) convertView.findViewById(R.id.scoreTime);
        timeView.setText(timeView.getText() + ": " + score.getTime());


        TextView nbCaractereView = (TextView) convertView.findViewById(R.id.scoreNbCaractere);
        nbCaractereView.setText(nbCaractereView.getText() + ": " + score.getNbCaracterePerSec());

        TextView nbWordWrittenView = (TextView) convertView.findViewById(R.id.scoreNbWordWritten);
        nbWordWrittenView.setText(nbWordWrittenView.getText() + ": " + score.getNbWordWrite());

        DecimalFormat format = new DecimalFormat();
        format.setMaximumFractionDigits(1);
        float precision = (score.getNbWordWrittenCaractere() /  score.getNbCaractere()) * 100;
        TextView precisionView = (TextView) convertView.findViewById(R.id.scorePrecision);
        precisionView.setText(precisionView.getText() + ": " + format.format(precision));

        // On récupère l'imageView
        ImageView imageView = (ImageView) convertView.findViewById(R.id.scorePhotoView);
        imageView.setImageBitmap(ScoreReader.readImage(score.getPhotoPath(), parent.getContext()));


        return convertView;
    }
}

