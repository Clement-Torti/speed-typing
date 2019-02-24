package com.example.speed_typing.model;

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

    public Scores(String name, int time, int nbWordWrite, int nbWordFailed, int nbCaractere,Date date, String photoPath) {
        this.name = name;
        this.time = time;
        this.nbWordWrite = nbWordWrite;
        this.nbWordFailed = nbWordFailed;
        this.nbCaractere = nbCaractere;
        this.date = date;
        this.photoPath = photoPath;
    }

    public Scores(String name, int time, int nbWordWrite, int nbWordFailed, int nbCaractere, Date date) {
        this(name, time, nbWordWrite, nbWordFailed, nbCaractere,date, "");
    }

    /*
    * Getters
     */
    public String name() { return name; }
    public int time() { return time; }
    public int getNbWordWrite() { return nbWordWrite; }
    public int getNbWordFailed() { return nbWordFailed; }
    public float getNbCaracterePerSec() { return (float)nbCaractere/time; }


}
