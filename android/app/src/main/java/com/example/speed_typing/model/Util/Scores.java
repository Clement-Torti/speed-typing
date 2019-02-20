package com.example.speed_typing.model.Util;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Scores {

    @PrimaryKey
    private String name;
    @PrimaryKey
    private int time;

    @ColumnInfo
    private int nbWordWrite;
    @ColumnInfo
    private int nbWordFailed;
    @ColumnInfo
    private int nbCaractere;

    /*
    * Le chemin d'acces à l'image du joueur
     */
    @ColumnInfo
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
