package com.example.speed_typing;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.speed_typing.model.Observer.GameTimer;
import com.example.speed_typing.model.Observer.IObserver;
import com.example.speed_typing.model.Util.SoundBox;
import com.example.speed_typing.model.Word;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Vector;

public class GameActivity extends BaseActivity implements IObserver {
    private static int CHAR_SIZE = 40;
    private static int FONT_SIZE = 20;
    private float DIFICULTY = 35;
    private static double SCREEN_BOTTOM;
    private TextView tempsView;
    private TextView nbCaracteresView;
    private TextView nbMotsEcritsView;
    private TextView nbMotsRatesView;
    private TextView nbLifeView;
    private Button pauseBtn;
    private EditText editText;
    private List<TextView> wordViewList = new ArrayList<>();
    private GameTimer gameTimer;
    private boolean screenIsVertical;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        // get l'orientation de l'ecran et enregistre si il est vertical
        screenIsVertical = getResources().getConfiguration().orientation == 1;

        Log.d("jonathan", screenIsVertical +" ");

        // Configuration des boutons
        pauseBtn = findViewById(R.id.pauseBtn);

        configureNavigationBtn(pauseBtn, PauseActivity.class, new HashMap<String, Serializable>());

        // Setup textView
        tempsView = findViewById(R.id.temps);
        tempsView.setTypeface(tempsView.getTypeface(), Typeface.BOLD);

        nbCaracteresView = findViewById(R.id.nbCaracteres);
        nbCaracteresView.setTypeface(nbCaracteresView.getTypeface(), Typeface.BOLD);

        nbMotsEcritsView = findViewById(R.id.nbMotEcrits);
        nbMotsEcritsView.setTypeface(nbMotsEcritsView.getTypeface(), Typeface.BOLD);

        nbMotsRatesView = findViewById(R.id.nbMotsRates);
        nbMotsRatesView.setTypeface(nbMotsRatesView.getTypeface(), Typeface.BOLD);

        nbLifeView = findViewById(R.id.nbLife);
        nbLifeView.setTypeface(nbLifeView.getTypeface(), Typeface.BOLD);

        // Definit le bas de l'écran
        SCREEN_BOTTOM = getWindowManager().getDefaultDisplay().getHeight() * 0.3;
    }


    /*
     * Appelée lorsque la partie ajoute un mot
     */
    @Override
    public void update() {

        // Récupérer le mot
        Word word = partie.getLastWord();

        // Définir un x aléatoire
        Random r = new Random();
        int windowsWidth = getWindowManager().getDefaultDisplay().getWidth();
        int x = r.nextInt(windowsWidth - (word.getText().length() * CHAR_SIZE));

        // Inserer la wordView dans notre vue
        insertWordView(word, x, 0);

    }

    @Override
    public void chronoUpdate() {
        // Increment la difficultee progressivement
        DIFICULTY += 2;

        // Met à jour la vue
        updateUI();
    }

    @Override
    protected void onResume() {
        super.onResume();


        removeWordView(wordViewList, wordViewList);
        //wordViewList.clear();

        // Démarre la musique
        SoundBox.playBackgroundSound(this);

        // S'abonner à la partie
        partie.attach(this);

        // Création du timer
        gameTimer = new GameTimer(this);
        gameTimer.attach(partie);
        gameTimer.attach(this);



        if (screenIsVertical) {
            // Remise en place des wordViews si on revient de PauseActivity
            configureWordView();

            // Setup editText
            configureEditText();

            gameTimer.start();

            // Ouvre le clavier
            showKeyboard();
        }
        updateUI();


    }

    private void quit() {

        // Arret du timer
        gameTimer.pause();

        if (screenIsVertical) {
            // Ferme le clavier
            closeKeyboard();

            // Sauvegarde des positions des éléments
            List<Vector<Integer>> wordsPos = new ArrayList<>();
            for (TextView wordView : wordViewList) {
                Vector<Integer> pos = new Vector<>();
                pos.add((int) wordView.getX());
                pos.add((int) wordView.getY());

                wordsPos.add(pos);
            }
            partie.setWordsPositions(wordsPos);

        }

        // On se desabonne à la partie
        partie.dettach(this);
        gameTimer.dettach(this);
        gameTimer.dettach(partie);

        if (screenIsVertical) {

            // Ferme le clavier
            closeKeyboard();
        }

        // Arret du timer
        gameTimer.pause();
    }

    /*
     * Travail à fournir en plus lorsque le jeu est mis en pause
     */
    @Override
    protected void changeActivity(Class<?> cls, Map<String, Serializable> args) {

        // Pour les performances
        if(cls == EndGameActivity.class) {
            partie.clearWords();
        }

        SoundBox.stopMusic();
        quit();
        super.changeActivity(cls, args);


    }

    @Override
    protected void onPause() {
        // Arrete le son
        SoundBox.pauseMusic();

        quit();
        super.onPause();
    }

    /*
     * Met à jour la vue
     */
    private void updateUI() {
        // On vérifie si c'est la fin de la partie
        if(partie.isEnded()) {
            endGame();
            return;
        }

        // wordView a supprimer
        List<TextView> deletedWordView = new ArrayList<>();

        // Temps
        tempsView.setText(getResources().getString(R.string.time) + ": " + partie.getChrono());
        DecimalFormat format = new DecimalFormat();
        format.setMaximumFractionDigits(2);
        nbCaracteresView.setText(getResources().getString(R.string.nbCaracterePerSec) + ": " + format.format(partie.nbCaracterePerSec()));
        nbMotsEcritsView.setText(getResources().getString(R.string.nbWordWrite) + ": " + partie.getNbWordWrite());
        nbLifeView.setText(getResources().getString(R.string.nbLife) + ": " + partie.getNbLife());

        if (screenIsVertical) {
            // Fait descendre la vue plus ou mpins vite en fonction de la difficulté
            for (TextView t : wordViewList) {
                float newY = t.getY() + DIFICULTY;
                t.setY(newY);
                // Check si le mot est descendu en bas
                if (isAtBottom(newY)) {
                    SoundBox.playFailSound(this);
                    String texte = t.getText().toString();
                    Word word = new Word(texte);
                    partie.wordMissed(word);
                    deletedWordView.add(t);

                }

                if (!editText.getText().toString().isEmpty() && t.getText().toString().startsWith(editText.getText().toString())) {
                    // Mettre en bleu les mot en cours d'écriture
                    t.setTextColor(Color.argb(255, 150, 150, 255));
                } else {
                    // Changer la couleur du texte en fonction de la hauteur
                    int redValue = (int) ((255 * newY) / SCREEN_BOTTOM);
                    t.setTextColor(Color.argb(255, redValue, 255 - redValue, 0));
                }


            }
        }

        removeWordView(deletedWordView, wordViewList);
        /*
        // Supprime les textView arrivées en bas
        for (TextView textView : deletedWordView) {
            ((ViewGroup) textView.getParent()).removeView(textView);
            wordViewList.remove(textView);
        }*/
    }

    // Supprime les textView
    private void removeWordView (List<TextView> wordViewList, List<TextView> aDeletedWordView){
        for (Iterator<TextView> a = wordViewList.iterator(); a.hasNext(); ){
            TextView textView = a.next();
            ((ViewGroup) textView.getParent()).removeView(textView);
        }
        aDeletedWordView.removeAll(wordViewList);
    }

    /*
    * @boolean True si la wordView est en dessous du seuil
     */
    private boolean isAtBottom(float x) {
        return x >= SCREEN_BOTTOM;
    }


    /*
     * Remettre en place les wordView en cause de restart de la partie
     */
    private void configureWordView() {
        int i = 0;

        Log.d("jonathan","nb mot a replacer "+partie.getWordsPositions().size());
        Log.d("jonathan","nb mot existant "+partie.getWords());
        for(Vector<Integer> pos : partie.getWordsPositions()) {

            Word word = partie.getWords().get(i);

            insertWordView(word, pos.get(0), pos.get(1)-(int)DIFICULTY);

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

                // Cherche un mot de la vue correspondant pour le supprimer
                String text = editText.getText().toString().replace(" ", "");
                Word written = new Word(text);

                // On vérifie si ce mot est assez long pour exister dans la vue
                if(text.length() >= partie.minWordLength()) {

                    // on verifie s'il existe
                    if(partie.wordWritten(written)) {
                        deleteViewWithText(text);
                        editText.setText("");
                        SoundBox.playSuccessSound(getApplicationContext());
                    }
                }

            }

            @Override
            public void afterTextChanged(Editable s) {
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
        wordView.setTextSize(FONT_SIZE);
        wordView.setTextColor(Color.argb(255, 0, 255, 0));
        wordView.setText(word.getText());


        // La positionner dans le linearLayout wordsView
        wordView.setX(x);
        wordView.setY(y);
        wordView.setLayoutParams(new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        RelativeLayout relativeLayout = (RelativeLayout) findViewById(R.id.wordsView);

        relativeLayout.addView( wordView, 0);

        // L'ajouter à la lite des views
        wordViewList.add(wordView);

    }


    /*
    * Appelez en fin de partie
     */
    private void endGame() {
        // Ferme le clavier
        closeKeyboard();

        // Arrete la musique
        SoundBox.stopMusic();

        // On se desabonne à la partie
        partie.dettach(this);
        gameTimer.dettach(this);
        gameTimer.dettach(partie);

        // Arret du timer
        gameTimer.pause();

        changeActivity(EndGameActivity.class, new HashMap<String, Serializable>());
    }


    /*
    * Ouvre le clavier dès le lancement de l'activité
     */
    private void showKeyboard() {
        editText.requestFocus();
        InputMethodManager imm = (InputMethodManager)   getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0);
    }

    /*
    * Ferme le clavier à la fermture de l'activité
     */
    private void closeKeyboard() {
        InputMethodManager imm = (InputMethodManager)getSystemService(
                Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(editText.getWindowToken(), 0);
    }

}