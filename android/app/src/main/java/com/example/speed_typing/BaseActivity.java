package com.example.speed_typing;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.io.Serializable;
import java.util.Map;

public class BaseActivity extends AppCompatActivity {

    /**
     * Encapsule le code lié à la navigation d'une activité à l'autre par un bouton.
     */
    void configureNavigationBtn(Button btn, final Class<?> cls, final Map<String, Serializable> args) {
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent navIntent = new Intent(getApplicationContext(), cls);
                for(String key: args.keySet()) {
                    navIntent.putExtra(key, args.get(key));
                }
                startActivity(navIntent);
            }
        });
    }
}
