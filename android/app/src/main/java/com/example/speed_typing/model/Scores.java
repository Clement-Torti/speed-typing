package com.example.speed_typing.model;

public class Scores {
    private String name;
    private int time;
    private int nbWordWrite;
    private int nbWordFailed;
    private int nbCaractere;

    /*
    * Le chemin d'acces à l'image du joueur
     */
    private String photoPath;

    public Scores(String name, int time, int nbWordWrite, int nbWordFailed, int nbCaractere, String photoPath) {
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


}
