package com.example.speed_typing.model;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
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
    private WordType wordtype;
    private List<Word> list = new ArrayList<Word>();
    private Random rand;

    public WordDatabase(WordType wordType, Context context){
        String line;
        wordtype = wordType;
        rand = new Random();

        //recuperation de l'asset manager permettant de retrouver les fichiers
        AssetManager assetManager = context.getAssets();

        try{
            //creation buffer
            InputStream ips = assetManager.open("WordFile/"+wordtype);
            InputStreamReader ipsr = new InputStreamReader(ips);
            BufferedReader br = new BufferedReader(ipsr);

            //lecture du fichier
            while ((line = br.readLine()) != null)
                    list.add(new Word(line));

            Log.d("test", list.get(1).toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Word getRandomWord(){
        Log.d("test", String.valueOf(list.size()));
        int index = rand.nextInt(list.size());
        return list.get(index);
    }
}
