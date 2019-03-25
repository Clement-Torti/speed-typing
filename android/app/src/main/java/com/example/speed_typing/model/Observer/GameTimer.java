package com.example.speed_typing.model.Observer;

import android.app.Activity;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class GameTimer extends Subject {
    private Timer timer;
    private Activity activity;

    public GameTimer(Activity activity) {
         timer = new Timer();
         this.activity = activity;
    }

    /*
    * Doit etre lancé sur le thread graphique car va update la vue
     */
     public void start() {

         TimerTask task = new TimerTask() {
             @Override
             public void run() {
                 activity.runOnUiThread(new Runnable() {
                     public void run() {
                        notifierChrono();
                     }
                 });

             }
         };

         timer.scheduleAtFixedRate(task, 0l, 500l);
    }

     public void pause() {
        timer.cancel();
    }


}

