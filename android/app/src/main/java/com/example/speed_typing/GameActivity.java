package com.example.speed_typing;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import android.widget.TextView;

import com.example.speed_typing.model.Observer.GameTimer;
import com.example.speed_typing.model.Observer.IObserver;
import com.example.speed_typing.model.Word;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Vector;

public class GameActivity extends BaseActivity implements IObserver {
    private static int DIFICULTY = 50;
    private TextView tempsView;
    private TextView nbCaracteresView;
    private TextView nbMotsEcritsView;
    private TextView nbMotsRatesView;
    private TextView nbLifeView;
    private Button pauseBtn;
    private EditText editText;
    private List<TextView> wordViewList = new ArrayList<>();
    private GameTimer gameTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        // Cache la bar du haut
        this.getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        this.getSupportActionBar().hide();

        // S'abonner à la partie
        partie.attach(this);

        // Configuration des boutons
        pauseBtn = findViewById(R.id.pauseBtn);

        configureNavigationBtn(pauseBtn, PauseActivity.class, new HashMap<String, Serializable>());

        // Setup textView
        tempsView = findViewById(R.id.temps);
        nbCaracteresView = findViewById(R.id.nbCaracteres);
        nbMotsEcritsView = findViewById(R.id.nbMotEcrits);
        nbMotsRatesView = findViewById(R.id.nbMotsRates);
        nbLifeView = findViewById(R.id.nbLife);

        // Setup editText
        configureEditText();


        // Remise en place des wordViews si on revient de PauseActivity
        configureWordView();

        // Création du timer
        gameTimer = new GameTimer(this);
        gameTimer.attach(this);
        gameTimer.attach(partie);
        gameTimer.start();

    }


    /*
     * Appelée lorsque la partie ajout un mot
     */
    @Override
    public void update() {

        // Récupérer le mot
        Word word = partie.getLastWord();

        // word.getText().length() * constant;
        // Définir un x aléatoire
        Random r = new Random();
        int windowsWidth = getWindowManager().getDefaultDisplay().getWidth();
        int x = r.nextInt(windowsWidth);

        // Inserer la wordView dans notre vue
        insertWordView(word, x, 0);


    }


    @Override
    public void chronoUpdate() {
        updateUI();
    }


    /*
     * Travail à fournir en plus lorsque le jeu est mis en pause
     */
    @Override
    protected void navigationButtonAction(Class<?> cls, Map<String, Serializable> args) {


        // Sauvegarde des positions des éléments
        List<Vector<Integer>> wordsPos = new ArrayList<>();
        for(TextView wordView : wordViewList) {
            Vector<Integer> pos = new Vector<>();
            pos.add((int) wordView.getX());
            pos.add((int) wordView.getY());

            wordsPos.add(pos);
        }
        partie.setWordsPositions(wordsPos);

        // On se desabonne à la partie
        partie.dettach(this);
        gameTimer.dettach(this);
        gameTimer.dettach(partie);

        // Arret du timer
        gameTimer.pause();

        super.navigationButtonAction(cls, args);
    }




    /*
     * Met à jour la vue
     */
    private void updateUI() {
        // Temps
        tempsView.setText(getResources().getString(R.string.time) + ": " + partie.getChrono());
        nbCaracteresView.setText(getResources().getString(R.string.nbCaracterePerSec) + ": " + partie.nbCaracterePerSec());
        nbMotsEcritsView.setText(getResources().getString(R.string.nbWordWrite) + ": " + partie.getNbWordWrite());
        nbMotsRatesView.setText(getResources().getString(R.string.nbWordMissed) + ": " + partie.getNbWordFailed());
        nbLifeView.setText(getResources().getString(R.string.nbLife) + ": " + partie.getNbLife());


        // Fait descendre la vue plus ou mpins vite en fonction de la difficulté
        for(TextView t : wordViewList) {
            float newY = t.getY() + DIFICULTY;
            t.setY(newY);
            // Check si le mot est descendu en bas

            // Changer la couleur du texte en fonction de la hauteur

        }

    }

    /*
     * Remettre en place les wordView en cause de restart de la partie
     */
    private void configureWordView() {
        int i = 0;

        for(Vector<Integer> pos : partie.getWordsPositions()) {
            Word word = partie.getWords().get(i);

            insertWordView(word, pos.get(0), pos.get(1)-DIFICULTY);

            i++;
        }
    }

    /*
    * Récupérer l'editText de la vue et lui ajouter les bons listeners
     */
    private void configureEditText() {
        editText = findViewById(R.id.editText);

        // Lorsque l'utilisateur écrit une lettre
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // Informer la partie qu'un caractère vient d'être écrit
                partie.caractereWritten();

            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        // Lorsque l'utilisateur clique sur la touche entrée
        editText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = editText.getText().toString().replace(" ", "");
                Word wordWritten = new Word(text);

                // On vérifie si ce mot existe
                if(partie.wordWritten(wordWritten)) {
                    deleteViewWithText(text);
                }

                // On efface le texte de l'editText
                editText.setText("");
            }
        });
    }

    /*
    * Cherche le wordView avec ce texte et le supprime de la vue
     */
    private void deleteViewWithText(String text) {
        for(TextView t : wordViewList) {
            String tText = t.getText().toString().replace(" ", "");
            if(tText.equals(text)) {
                // Supprime la wordView de son parent
                ((ViewGroup)t.getParent()).removeView(t);
                wordViewList.remove(t);
                // On ne supprime qu'une wordView avec comme contenu text
                return;
            }
        }
    }


    /*
     * Permet l'ajout d'un wordView avec un certain mot et une certaine position
     */
    private void insertWordView(Word word, int x, int y) {

        // Creer la vue du mots
        TextView wordView = new TextView(this);
        wordView.setText(word.getText());
        wordView.setTextColor(getResources().getColor(R.color.colorWhite));


        // La positionner dans le linearLayout wordsView
        wordView.setX(x);
        wordView.setY(y);
        wordView.setLayoutParams(new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        RelativeLayout relativeLayout = (RelativeLayout) findViewById(R.id.wordsView);

        relativeLayout.addView( wordView, 0);

        // L'ajouter à la lite des views
        wordViewList.add(wordView);

    }


}