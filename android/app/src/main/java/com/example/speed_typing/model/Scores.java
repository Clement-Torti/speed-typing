package com.example.speed_typing.model;

import android.util.Log;

import java.util.Date;

public class Scores {
    private String name;
    private int time;
    private int nbWordWrite;
    private int nbWordFailed;
    private int nbCaractere;
    private Date date;

    /*
    * Le chemin d'acces à l'image du joueur
     */
    private String photoPath;

    public Scores(String name, int time, int nbWordWrite, int nbWordFailed, int nbCaractere, String photoPath) {
        Log.d("jonathan","contructeur scores");
        this.name = name;
        this.time = time;
        this.nbWordWrite = nbWordWrite;
        this.nbWordFailed = nbWordFailed;
        this.nbCaractere = nbCaractere;
        this.photoPath = photoPath;
    }

    public Scores(String name, int time, int nbWordWrite, int nbWordFailed, int nbCaractere) {
        this(name, time, nbWordWrite, nbWordFailed, nbCaractere, "");
    }

    /*
    * Getters
     */
    public String name() { return name; }
    public int time() { return time; }
    public int getNbWordWrite() { return nbWordWrite; }
    public int getNbWordFailed() { return nbWordFailed; }
    public float getNbCaracterePerSec() { return (float)nbCaractere/time; }


    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getPhotoPath() {
        return photoPath;
    }

    public void setPhotoPath(String photoPath) {
        this.photoPath = photoPath;
    }
}
