package com.example.speed_typing;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.speed_typing.model.Scores;
import com.example.speed_typing.model.Util.ScoreReader;
import com.example.speed_typing.model.Util.ScoreWriter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.sql.Time;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EndGameActivity extends BaseActivity {

    private Button cameraButton;
    private ImageView userPhotoView;
    private TextView timeView;
    private TextView nbWordWrittenView;
    private TextView precisionView;
    private TextView nbCaractereView;
    private Button homeBtn;
    private Bitmap image;
    private EditText nameView;
    private String photoPath = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end_game);



        // Configuration des boutons de navigation
        homeBtn = findViewById(R.id.homeBtn);
        configureNavigationBtn(homeBtn, AccueilActivity.class, new HashMap<String, Serializable>());
        homeBtn.setEnabled(false);
        cameraButton = (Button) findViewById(R.id.cameraBtn);
        configureNavigationBtn(cameraButton, CameraActivity.class, new HashMap<String, Serializable>());

        // affchage des résultats
        userPhotoView = (ImageView) findViewById(R.id.userPhotoView);
        timeView = (TextView) findViewById(R.id.resTime);
        nbWordWrittenView = (TextView) findViewById(R.id.resNbMotsEcrits);
        precisionView = (TextView) findViewById(R.id.resPrecision);
        nbCaractereView = (TextView) findViewById(R.id.resNbCaracteres);
        updateUI();

        // Donne un nom
        nameView = (EditText) findViewById(R.id.nameView);

        // Si l'on revient de CameraActivity, on a une image enregistrée, on l'affiche
        if(getIntent().hasExtra("photoPath")) {
            photoPath = (String) getIntent().getExtras().getSerializable("photoPath");
            Bitmap image = ScoreReader.readImage(photoPath, this);
            // Affichage dans l'imageView
            userPhotoView.setImageBitmap(image);
            homeBtn.setEnabled(true);

        }


    }

    /*
    * Met à jour tous les éléments de vues avec les informations de la partie
     */
    private void updateUI() {
        DecimalFormat format = new DecimalFormat();
        format.setMaximumFractionDigits(2);
        timeView.setText(partie.getChrono() + "");
        nbWordWrittenView.setText(partie.getNbWordWrite() + "");
        float precision = ((float)partie.getNbWordWrittenCarateres()/  partie.getNbCaractere()) * 100;
        precisionView.setText(format.format(precision)+ "");
        format.setMaximumFractionDigits(2);
        nbCaractereView.setText(format.format(partie.nbCaracterePerSec()) + "");
    }



    @Override
    protected void changeActivity(Class<?> cls, Map<String, Serializable> args) {
        super.changeActivity(cls, args);

        if(cls == AccueilActivity.class) {
            // Sauvegarder la partie
            saveGame();
        }

    }

    /*
    * sauvegarde la partie dans un fichier
     */
    private void saveGame() {
        // On créer le nouveau score
        String name = nameView.getText().toString();
        int time = partie.getChrono();
        int nbWordWrite = partie.getNbWordWrite();
        int nbCaractere = partie.getNbCaractere();
        int nbWordWrittenCaractere = partie.getNbWordWrittenCarateres();

        Scores newScore = new Scores(name, time, nbWordWrite, nbWordWrittenCaractere, nbCaractere, photoPath);
        // On récupère la liste des scores
        List<Scores> scores = ScoreReader.read(getApplicationContext());

        // On insere le nouveau score au bon endroit
        scores = insertScore(newScore, scores);

        // On enregistre ces scores dans le fichier
        ScoreWriter.write(getApplicationContext(), scores);

    }

    /*
    * Ajoute le score dans la liste au bon endroit
     */
    private List<Scores> insertScore(Scores score, List<Scores> scores) {

        int i;

        for(i=0; i<scores.size(); i++) {
            if(scores.get(i).compareTo(score) < 0) {
                scores.add(i, score);
                return scores;
            }
        }

        // Ce score est la plus mauvais, on l'ajoute à la fin
        scores.add(score);

        return scores;
    }
}
