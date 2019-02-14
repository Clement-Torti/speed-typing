package com.example.speed_typing.model.Observer;

import android.content.Context;

import com.example.speed_typing.model.Word;
import com.example.speed_typing.model.WordDatabase;
import com.example.speed_typing.model.WordFactory;
import com.example.speed_typing.model.WordType;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


/*
* Partie doit être serializable pour être passée d'une vue à l'autre
 */
public class Partie extends Subject {
    public static final int NB_LIFE = 10;
    private int chrono;
    private int nbWordWrite;
    private int nbWordFailed;
    private int nbLife;
    private int nbCaractere;

    private WordDatabase wordDb;
    private List<Word> displayedWord = new ArrayList<>();

    public Partie(Context context, WordType wordType) {
        chrono = 0;
        nbWordWrite = 0;
        nbWordFailed = 0;
        nbLife = NB_LIFE;
        nbCaractere = 0;

        wordDb = WordFactory.createWordDatabase(context, wordType);
    }

    /*
    * Augmente le temps de 1 sec
     */
    public void incrementChrono() {
        chrono++;
    }

    /*
    * Appelez lorsque qu'un mot de displayedWord vient d'être écrit
     */
    public boolean wordWritten(Word word) {
        return displayedWord.remove(word);
    }

    /*
     * Appelez lorsque qu'un mot de displayedWord n'a pas été écrit à temps
     */
    public boolean wordMissed(Word word) {
        boolean res = displayedWord.remove(word);
        nbLife--;

        return res;
    }

    /*
    * Lorsqu'un caractere est écrit
     */
    public void caractereWritten() {
        nbCaractere++;
    }

    /*
    * Nombre de caratere écrit par seconde
     */
    public float nbCaracterePerSec() {
        return ((float) nbCaractere)/ chrono;
    }

    /*
    * Ajout d'une mot dans displayedWord, notify les observateurs du changement
     */
    public void addNewWord() {
        Word word = wordDb.getRandomWord();
        displayedWord.add(word);
        notifier();
    }

    public void pause() {}
    public void start() {}

    public boolean isEnded() { return NB_LIFE == 0; }

}
