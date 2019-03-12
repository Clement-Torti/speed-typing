package com.example.speed_typing;

import android.content.Intent;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.speed_typing.model.Scores;
import com.example.speed_typing.model.Util.ScoreReader;
import com.example.speed_typing.model.Util.ScoreWriter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EndGameActivity extends BaseActivity {

    private ImageView userPhotoView;
    private TextView timeView;
    private TextView nbWordWrittenView;
    private TextView nbWordMissedView;
    private TextView precisionView;
    private TextView nbCaractereView;
    private Button homeBtn;
    private Bitmap image;
    private EditText nameView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end_game);

        // Configuration des boutons de navigation
        homeBtn = findViewById(R.id.homeBtn);
        configureNavigationBtn(homeBtn, AccueilActivity.class, new HashMap<String, Serializable>());
        homeBtn.setEnabled(false);

        // affchage des résultats
        userPhotoView = (ImageView) findViewById(R.id.userPhotoView);
        timeView = (TextView) findViewById(R.id.resTime);
        nbWordWrittenView = (TextView) findViewById(R.id.resNbMotsEcrits);
        nbWordMissedView = (TextView) findViewById(R.id.resNbMotsRates);
        precisionView = (TextView) findViewById(R.id.resPrecision);
        nbCaractereView = (TextView) findViewById(R.id.resNbCaracteres);
        updateUI();

        // Donne un nom
        nameView = (EditText) findViewById(R.id.nameView);

    }

    /*
    * Met à jour tous les éléments de vues avec les informations de la partie
     */
    private void updateUI() {
        DecimalFormat format = new DecimalFormat();
        format.setMaximumFractionDigits(1);
        timeView.setText(partie.getChrono() + "");
        nbWordWrittenView.setText(partie.getNbWordWrite() + "");
        nbWordMissedView.setText(partie.getNbWordFailed() + "");
        float precision = (partie.getNbWordWrite()  / ((float) partie.getNbWordFailed() + partie.getNbWordWrite())) * 100;
        precisionView.setText(format.format(precision)+ "");
        format.setMaximumFractionDigits(2);
        nbCaractereView.setText(format.format(partie.nbCaracterePerSec()) + "");
    }

    /*
    * Prend une photo avec l'appareil
     */
    public void takePicture(View view) {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, 0);
    }

    /*
    * Evenement appelez lorsque l'utilisateur à pris une photo
     */

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        image = (Bitmap) data.getExtras().get("data");
        userPhotoView.setImageBitmap(image);
        homeBtn.setEnabled(true);

    }

    @Override
    protected void changeActivity(Class<?> cls, Map<String, Serializable> args) {
        super.changeActivity(cls, args);

        // Sauvegarder la partie
        saveGame();
    }

    /*
    * sauvegarde la partie dans un fichier
     */
    private void saveGame() {
        // On créer le nouveau score
        String photoPath = ScoreWriter.writeImage(image, getApplicationContext());
        String name = nameView.getText().toString();
        int time = partie.getChrono();
        int nbWordWrite = partie.getNbWordWrite();
        int nbWordFailed = partie.getNbWordFailed();
        int nbCaractere = partie.getNbCaractere();

        Scores newScore = new Scores(name, time, nbWordWrite, nbWordFailed, nbCaractere, photoPath);

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
        // Ajoute à la fin pour le moment, à implémenter...
        scores.add(score);
        return scores;
    }
}
