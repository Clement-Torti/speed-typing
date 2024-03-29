package com.example.speed_typing;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.example.speed_typing.model.Observer.Partie;
import com.example.speed_typing.model.WordFactory;
import com.example.speed_typing.model.WordType;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class AccueilActivity extends BaseActivity implements AdapterView.OnItemSelectedListener {

    private Button startBtn;
    private Button scoresBtn;
    //private Button fragmentBtn;
    private Spinner wordsSpinner;

    private WordType wordType = WordType.Francais;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accueil);

        // Boutons de navigation
        startBtn = findViewById(R.id.startBtn);
        scoresBtn = findViewById(R.id.scoresBtn);
        //fragmentBtn = findViewById(R.id.fragmentBtn);

        configureNavigationBtn(startBtn, GameActivity.class, new HashMap<String, Serializable>());
        configureNavigationBtn(scoresBtn, ScoreActivity.class, new HashMap<String, Serializable>());
        //configureNavigationBtn(fragmentBtn, FragmentActivity.class, new HashMap<String, Serializable>()); fragments non fonctionnel

        // Spinner
        wordsSpinner = findViewById(R.id.wordsSpinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.wordsFamily, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        wordsSpinner.setAdapter(adapter);
        wordsSpinner.setOnItemSelectedListener(this);
    }


    /*
    * Choix d'un élément dans le spinner. récupère l'élément sélectionné et le converti en WordType
     */
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String texte = parent.getItemAtPosition(position).toString();

        wordType = WordFactory.getWordType(texte);
    }

    @Override
    protected void changeActivity(Class<?> cls, Map<String, Serializable> args) {
        // Creation de la partie
        partie = new Partie(this.getApplicationContext(), wordType);
        super.changeActivity(cls, args);



    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }



}
