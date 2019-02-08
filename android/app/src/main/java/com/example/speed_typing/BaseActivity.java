package com.example.speed_typing;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class BaseActivity extends AppCompatActivity {

    /**
     * Encapsule le code lié à la navigation d'une activité à l'autre par un bouton.
     */
    void configureNavigationBtn(Button btn, final Class<?> cls) {
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent navIntent = new Intent(getApplicationContext(), cls);
                startActivity(navIntent);
            }
        });
    }
}
