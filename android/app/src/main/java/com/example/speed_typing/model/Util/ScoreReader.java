package com.example.speed_typing.model.Util;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.util.Log;

import com.example.speed_typing.model.Scores;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;

public class ScoreReader {
    private static ArrayList<Scores> scoreLignes = new ArrayList<Scores>();

    public static ArrayList<Scores> read(Context context){
        String ligne;
        String elements[];

        int nbWordWrite;
        int nbWordFailed;
        float nbCharactere;
        String name;
        String photoPath;
        int time;


        try {
            //creation buffer
            String fileName = "Scores.txt";

            FileInputStream ips = context.openFileInput(fileName);
            InputStreamReader ipsr = new InputStreamReader(ips);
            BufferedReader br = new BufferedReader(ipsr);


            while((ligne = br.readLine()) != null) {
                elements = ligne.split("_");

                name = elements[0];

                time = Integer.parseInt(elements[1]);

                nbWordWrite = Integer.parseInt(elements[2]);

                nbWordFailed= Integer.parseInt(elements[3]);

                nbCharactere = Float.parseFloat(elements[4]);

                photoPath = elements[5];

                scoreLignes.add(new Scores(name, time, nbWordWrite, nbWordFailed, nbCharactere, photoPath));
            }

            ips.close();
            ipsr.close();
            br.close();

        } catch (FileNotFoundException e) {
            //FileNotFoundException
            e.printStackTrace();
            System.out.println("Fichier non trouvé");
            // return new ArrayList<Scores>
        } catch (IOException e) {
            //IOException
            e.printStackTrace();
            System.out.println("Il y a eu une IOException");
        }

        return scoreLignes;

    }


    public static Bitmap readImage(String filePath, Context context) {
        Bitmap img = null;

        try {
            // On lit tous les bytes du fichier
            File file = new File(context.getFilesDir() + "/" + filePath);
            InputStream in = new BufferedInputStream(new FileInputStream(file));
            byte[] buf = new byte[(int) file.length()];
            int numRead = in.read(buf);

            // On convertie ces bytes en Bitmap
            img = BitmapFactory.decodeByteArray(buf, 0, numRead);

        } catch(FileNotFoundException e) {
            Log.d("readImage", "fichier " + filePath + " introuvable");
        } catch(IOException e) {
            Log.d("readImage", "read buffer fonctionne pas");
        }


        return img;

    }
}
