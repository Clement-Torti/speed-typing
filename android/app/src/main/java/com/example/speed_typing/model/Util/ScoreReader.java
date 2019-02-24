package com.example.speed_typing.model.Util;

import android.content.Context;

import com.example.speed_typing.model.Scores;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
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

        File fichier= new File(fileName);

        DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.MEDIUM);

        try {
            BufferedReader br = new BufferedReader(new FileReader(fichier));
            ligne = br.readLine().split("-");
            while (ligne[0] != " ") {
                name = ligne[0];
                time = Integer.parseInt(ligne[1]);
                nbWordWrite = Integer.parseInt(ligne[2]);
                nbWordFailed= Integer.parseInt(ligne[3]);
                nbCharactere = Integer.parseInt(ligne[4]);
                date = dateFormat.parse(ligne[5]);
                if (ligne.length == 6){
                    photoPath = ligne[6];
                    scoreLignes.add(new Scores(name, time, nbWordWrite, nbWordFailed, nbCharactere,date, photoPath));
                }
                else
                    scoreLignes.add(new Scores(name, time, nbWordWrite, nbWordFailed, nbCharactere,date));
                ligne = br.readLine().split("-");
            }

            br.close();
        } catch (FileNotFoundException e) {
            //FileNotFoundException
            e.printStackTrace();
        } catch (IOException e) {
            //IOException
            e.printStackTrace();
        } catch (ParseException e) {
            //ParseException
            e.printStackTrace();
        }

        return scoreLignes;

    }
}
