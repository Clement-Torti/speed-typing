package com.example.speed_typing.model.Util;

import android.content.Context;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;

public class ScoreReader {/*
    private static ArrayList<ScoreLigne> scoreLignes = new ArrayList<ScoreLigne>();

    public static ArrayList<ScoreLigne> read(String fileName, Context context){
        String ligne[];

        int score;
        int nbCharactere;
        String nom;
        Date date;
        String pathPhoto;

        File fichier= new File(fileName);

        DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.MEDIUM);

        try {
            BufferedReader br = new BufferedReader(new FileReader(fichier));
            ligne = br.readLine().split("-");
            while (ligne[0] != " ") {
                score = Integer.parseInt(ligne[0]);
                nbCharactere = Integer.parseInt(ligne[1]);
                nom = ligne[2];
                date = dateFormat.parse(ligne[3]);
                pathPhoto = ligne[4];

                scoreLignes.add(new ScoreLigne(score, nbCharactere, nom, date, pathPhoto));
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
*/}
