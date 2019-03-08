package com.example.speed_typing.model.Util;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.util.Log;

import com.example.speed_typing.model.Scores;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class ScoreWriter {

    public static void write(Context context, ArrayList<Scores> scores){
        String ligne[];

        int nbWordWrite;
        int nbWordFailed;
        float nbCharactere;
        String name;
        Date date;
        String photoPath;
        int time;

        try
        {

            FileWriter writer = new FileWriter(context.getFilesDir() + "/Scores.txt");

               for (int i = 0; i<scores.size(); i++){
                   name = scores.get(i).name();
                   time = scores.get(i).time();
                   nbWordWrite = scores.get(i).getNbWordWrite();
                   nbWordFailed = scores.get(i).getNbWordFailed();
                   nbCharactere = scores.get(i).getNbCaracterePerSec();
                   date = scores.get(i).getDate();
                   photoPath = scores.get(i).getPhotoPath();

                   writer.write(name+"_"+time+"_"+nbWordWrite+"_"+nbWordFailed+"_"+nbCharactere+"_"+date.toString()+"_"+photoPath);
                }

            } catch (IOException e) {
            Log.d("jonathan","IO exeption");
        }
    }

    // Ecrit une image dans un fichier binaire
    public static String writeImage(Bitmap img, Context context) {
        String pathName = generateRandomPath();

        // Création du fichier
        File f = new File(context.getFilesDir() + "/" + pathName);

        // Ouverture du fichier
        // et enregistrement en binaire
        try {
            FileOutputStream ops = context.openFileOutput(pathName, Context.MODE_PRIVATE);
            img.compress(Bitmap.CompressFormat.PNG, 100, ops);
        } catch (FileNotFoundException e) {
            Log.d("writeImage","Création d'un FileOutput stream impossible");
        }

        // Ecriture de l'image au format binaire


        return pathName;
    }

    public static String generateRandomPath() {
        Random r = new Random();

        int length = 16;
        char lettres[] = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
        String str = "";

        for (int i = 0; i < length; i++) {
            str += lettres[r.nextInt(lettres.length)];
        }

            return str;

    }
}
