package com.example.speed_typing.model.Observer;

import android.content.Context;
import android.util.Log;

import com.example.speed_typing.model.Word;
import com.example.speed_typing.model.WordDatabase;
import com.example.speed_typing.model.WordFactory;
import com.example.speed_typing.model.WordType;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;


/*
 * Partie doit être serializable pour être passée d'une vue à l'autre
 */
public class Partie extends Subject implements Serializable, IObserver{
    public static final long serialversionUID = 129348938L;
    public static final int NB_LIFE = 5;
    private int chrono;
    private int nbWordWrite;
    private int nbWordFailed;
    private int nbLife;
    private int nbCaractere;

    private WordDatabase wordDb;
    private List<Word> displayedWord = new ArrayList<>();
    private List<Vector<Integer>> wordsPositions = new ArrayList<>();

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
        boolean bool =  displayedWord.remove(word);

        if(bool) {
            nbWordWrite++;
        } else {
            nbWordFailed++;
        }

        return bool;
    }

    /*
     * Appelez lorsque qu'un mot de displayedWord n'a pas été écrit à temps
     */
    public boolean wordMissed(Word word) {
        boolean res = displayedWord.remove(word);
        nbLife--;
        // Notifier
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
    * Nombre de caracteres total
     */
    public int getNbCaractere() { return nbCaractere; }

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

    /*
     * @return retourne true si la partie est fini
     */
    public boolean isEnded() { return nbLife <= 0; }

    /*
     * @return retourne le dernier mot de la liste displayedWord
     */
    public Word getLastWord() {
        int index  = displayedWord.size() - 1;
        return displayedWord.get(index);
    }

    public int getChrono() { return chrono; }

    public int getNbWordWrite() { return nbWordWrite; }

    public int getNbWordFailed() { return nbWordFailed; }

    public int getNbLife() { return nbLife; }

    @Override
    public void update() {

    }

    @Override
    public void chronoUpdate() {
        chrono++;

        if(chrono % 3 == 0) {
            addNewWord();
        }

    }

    /*
     * Lorsque le jeu est mis en pause. GameActivity transmet les données de la vue à Partie pour pouvoir ré-afficher les mots au bon endroit
     */
    public void setWordsPositions(List<Vector<Integer>> positions) {
        wordsPositions = positions;
        Log.d("jonathan","      partie nb wordposition "+wordsPositions.size());
    }

    /*
     * Lorsque le GameActivity se relance, attribuer la bonne position aux mots
     */
    public List<Vector<Integer>> getWordsPositions() {
        return wordsPositions;
    }


    public List<Word> getWords() { return displayedWord; }

}

