package com.example.speed_typing.model;

import android.util.Log;

import java.util.Date;

public class Scores {
    private String name;
    private int time;
    private int nbWordWrite;
    private int nbCaractere;
    private int nbWordWrittenCaractere;

    /*
    * Le chemin d'acces à l'image du joueur
     */
    private String photoPath;

    public Scores(String name, int time, int nbWordWrite, int nbWordWrittenCaractere, int nbCaractere, String photoPath) {

        this.name = name;
        this.time = time;
        this.nbWordWrite = nbWordWrite;
        this.nbWordWrittenCaractere = nbWordWrittenCaractere;
        this.nbCaractere = nbCaractere;
        this.photoPath = photoPath;
    }

    public Scores(String name, int time, int nbWordWrite, int nbWordWrittenCaractere, int nbCaractere) {
        this(name, time, nbWordWrite, nbWordWrittenCaractere, nbCaractere, "");
    }

    /*
    * Getters
     */
    public String name() { return name; }
    public int time() { return time; }
    public int getNbWordWrite() { return nbWordWrite; }
    public float getNbCaracterePerSec() { return (float)nbCaractere/time; }
    public int getNbWordWrittenCaractere() { return nbWordWrittenCaractere; }
    public int getNbCaractere() { return nbCaractere; }

    public String getPhotoPath() {
        return photoPath;
    }

    public void setPhotoPath(String photoPath) {
        this.photoPath = photoPath;
    }

    public String getName() { return name; }

    public int getTime() { return time; }

    @Override public String toString() {
        return name+"_"+time+"_"+nbWordWrite+"_"+nbCaractere+"_"+photoPath;
    }

}
