package com.example.speed_typing;

import android.os.Bundle;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TextView;

import com.example.speed_typing.model.Observer.GameTimer;
import com.example.speed_typing.model.Observer.IObserver;
import com.example.speed_typing.model.Word;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class GameActivity extends BaseActivity implements IObserver {

    private TextView tempsView;
    private TextView nbCaracteresView;
    private TextView nbMotsEcritsView;
    private Button pauseBtn;
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


        // Création du timer
        gameTimer = new GameTimer(this);
        gameTimer.attach(this);
        gameTimer.attach(partie);
        gameTimer.start();

    }

    @Override
    public void update() {

        // Récupérer le mot
        Word word = partie.getLastWord();


        // Creer la vue du mots
        TextView wordView = new TextView(this);
        wordView.setText(word.getText());
        wordView.setTextColor(getResources().getColor(R.color.colorWhite));

        // La positionner dans le linearLayout wordsView
        wordView.setLayoutParams(new TableLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.wordsView);

        linearLayout.addView( wordView, 0);

        // L'ajouter à la lite des views
        wordViewList.add(wordView);


    }

    @Override
    public void chronoUpdate() {
        updateUI();
    }


    private void updateUI() {
        // Temps
        tempsView.setText("Temps: " + partie.getChrono());

    }
}
