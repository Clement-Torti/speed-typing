package com.example.speed_typing;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.speed_typing.model.Observer.Partie;

import java.io.Serializable;
import java.util.Map;

public class BaseActivity extends AppCompatActivity {
    protected Partie partie;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Récupérer la partie de l'activité précedente
        if(getIntent().hasExtra("game")) {
            partie = (Partie) getIntent().getExtras().getSerializable("game");
        }

    }

    /**
     * Encapsule le code lié à la navigation d'une activité à l'autre par un bouton.
     */
    protected void configureNavigationBtn(Button btn, final Class<?> cls, final Map<String, Serializable> args) {
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeActivity(cls, args);
            }
        });

    }

    /*
     * Action a exécuter lorsque l'on navigue d'une page à l'autre
     */
    protected void changeActivity(final Class<?> cls, final Map<String, Serializable> args) {
        Intent navIntent = new Intent(getApplicationContext(), cls);
        for(String key: args.keySet()) {
            navIntent.putExtra(key, args.get(key));
        }
        // Le passage de la partie se fait systématiquement
        navIntent.putExtra("game", partie);
        startActivity(navIntent);
    }


}