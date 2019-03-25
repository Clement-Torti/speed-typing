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
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.List;
import java.util.Random;

public class ScoreWriter {

    public static void write(Context context, List<Scores> scores){
        String ligne[];

        int nbCaractere;
        int nbWordWrite;
        int nbWordWrittenCaracteres;
        String name;
        String photoPath;
        int time;

        try
        {
            String fileName = "Scores.txt";
            // Supprime le contenu actuelle
            File f = new File(context.getFilesDir(), fileName);
            f.delete();
            FileOutputStream fos = context.openFileOutput(fileName, Context.MODE_PRIVATE);
            OutputStreamWriter writer = new OutputStreamWriter(fos);
            PrintWriter printWriter = new PrintWriter(writer);

               for (int i = 0; i<scores.size(); i++) {
                   name = scores.get(i).getName();
                   time = scores.get(i).getTime();
                   nbWordWrite = scores.get(i).getNbWordWrite();
                   nbWordWrittenCaracteres = scores.get(i).getNbWordWrittenCaractere();
                   nbCaractere = scores.get(i).getNbCaractere();
                   photoPath = scores.get(i).getPhotoPath();
                   String scoreStr = name + "_" + time + "_" + nbWordWrite + "_" + nbWordWrittenCaracteres + "_" + nbCaractere + "_" + photoPath;
                   writer.write(scoreStr);
                    printWriter.println();
                }
                printWriter.close();
                writer.close();
               fos.close();


        } catch (IOException e) {
            Log.d(e.getMessage(),"IO exeption dans ScoreWriter");

        }
    }
    /*

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


        return pathName;
    }
    */

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
