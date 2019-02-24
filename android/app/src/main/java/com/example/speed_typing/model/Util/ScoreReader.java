package com.example.speed_typing.model.Util;

import android.content.Context;
import android.content.res.AssetManager;
import android.util.Log;

import com.example.speed_typing.model.Scores;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;

public class ScoreReader {
    private static ArrayList<Scores> scoreLignes = new ArrayList<Scores>();

    public static ArrayList<Scores> read(String fileName, Context context){
        String ligne[];

        int nbWordWrite;
        int nbWordFailed;
        int nbCharactere;
        String name;
        Date date;
        String photoPath;
        int time;

        int nbscores;
        //recuperation de l'asset manager permettant de retrouver les fichiers
        AssetManager assetManager = context.getAssets();

        DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.MEDIUM);

        try {

            //creation buffer
            InputStream ips = assetManager.open(fileName);
            InputStreamReader ipsr = new InputStreamReader(ips);
            BufferedReader br = new BufferedReader(ipsr);

            /*File fichier = context.getFileStreamPath(fileName);
            FileReader fr = new FileReader(fichier);
            BufferedReader br = new BufferedReader(fr);;*/

            nbscores = Integer.parseInt(br.readLine());
            Log.d("jonathan","scoreReader");

            for (int i=0; i < (int)nbscores;i++)  {
                ligne = br.readLine().split("_");

                name = ligne[0];
                Log.d("jonathan","      nom "+name);

                time = Integer.parseInt(ligne[1]);

                nbWordWrite = Integer.parseInt(ligne[2]);

                nbWordFailed= Integer.parseInt(ligne[3]);

                nbCharactere = Integer.parseInt(ligne[4]);

                /*if (ligne.length == 5){
                    Log.d("jonathan","ici");
                    photoPath = ligne[5];
                    Log.d("jonathan",photoPath);
                    scoreLignes.add(new Scores(name, time, nbWordWrite, nbWordFailed, nbCharactere, photoPath));

                }
                else*/
                scoreLignes.add(new Scores(name, time, nbWordWrite, nbWordFailed, nbCharactere));
            }

            br.close();
        } catch (FileNotFoundException e) {
            //FileNotFoundException
            e.printStackTrace();
            Log.d("jo","file not found");
        } catch (IOException e) {
            //IOException
            e.printStackTrace();
            Log.d("jo","IO exeption");
        }

        return scoreLignes;

    }
}
