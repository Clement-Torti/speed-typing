package com.example.speed_typing.model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import android.content.Context;
import android.content.res.AssetManager;
import android.util.Log;

public class WordDatabase {

    private List<Word> words = new ArrayList<>();
    private Random rand;

    public WordDatabase(String fileName, Context context){
        String line;
        rand = new Random();

        //recuperation de l'asset manager permettant de retrouver les fichiers
        AssetManager assetManager = context.getAssets();

        try{
            //creation buffer
            InputStream ips = assetManager.open(fileName);
            InputStreamReader ipsr = new InputStreamReader(ips);
            BufferedReader br = new BufferedReader(ipsr);

            //lecture du fichier
            while ((line = br.readLine()) != null) {
                words.add(new Word(line));
            }


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Word getRandomWord(){
        int index = rand.nextInt(words.size());
        return words.get(index);
    }
}
