package com.example.speed_typing.model.Util;

import android.content.Context;
import android.content.res.AssetManager;
import android.util.Log;

import com.example.speed_typing.model.Scores;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;

public class ScoreWriter {

    public static void write(String fileName, Context context, ArrayList<Scores> scores){
        String ligne[];

        int nbWordWrite;
        int nbWordFailed;
        float nbCharactere;
        String name;
        Date date;
        String photoPath;
        int time;
        Log.d("jonathan","scorewiter");

        //recuperation de l'asset manager permettant de retrouver les fichiers
        //AssetManager assetManager = context.getAssets();

        try
        {

            //File fichier = context.getFileStreamPath("Scores.txt");
            //Log.d("jonathan", fichier.getAbsolutePath());
            //BufferedReader br = new BufferedReader(new FileReader(fichier));

            FileWriter fw=new FileWriter("Scores.txt");
            //FileWriter fw=new FileWriter(context.getFileStreamPath("Scores.txt"));

               for (int i = 0; i<scores.size(); i++){
                   name = scores.get(i).name();
                   Log.d("jonathan",name);
                   time = scores.get(i).time();
                   nbWordWrite = scores.get(i).getNbWordWrite();
                   nbWordFailed = scores.get(i).getNbWordFailed();
                   nbCharactere = scores.get(i).getNbCaracterePerSec();
                   date = scores.get(i).getDate();
                   photoPath = scores.get(i).getPhotoPath();

                   fw.write(name+"_"+time+"_"+nbWordWrite+"_"+nbWordFailed+"_"+nbCharactere+"_"+date.toString()+"_"+photoPath);
                }

            } catch (IOException e) {
            Log.d("jonathan","IO exeption");
        }
    }


}
