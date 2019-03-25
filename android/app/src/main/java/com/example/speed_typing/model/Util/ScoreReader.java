package com.example.speed_typing.model.Util;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.Image;
import android.util.Log;

import com.example.speed_typing.model.Scores;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class ScoreReader {
    private static final int IMAGE_WIDTH = 200;
    private static final int IMAGE_HEIGHT = 300;
    private static ArrayList<Scores> scoreLignes = new ArrayList<Scores>();

    public static ArrayList<Scores> read(Context context){
        String ligne;
        String elements[];

        int nbWordWrite;
        int nbCharactere;
        String name;
        String photoPath;
        int time;
        int nbWordWrittenCaracteres;

        // Vider la liste des scores
        scoreLignes.clear();

        try {
            //creation buffer
            String fileName = "Scores.txt";

            FileInputStream ips = context.openFileInput(fileName);
            InputStreamReader ipsr = new InputStreamReader(ips);
            BufferedReader br = new BufferedReader(ipsr);


            // name + "_" + time + "_" + nbWordWrite + "_" + nbWordWrittenCaracteres + "_" + nbCharacterePerSec + "_" +photoPath
            while((ligne = br.readLine()) != null) {
                elements = ligne.split("_");
                name = elements[0];

                time = Integer.parseInt(elements[1]);

                nbWordWrite = Integer.parseInt(elements[2]);

                nbWordWrittenCaracteres = Integer.parseInt(elements[3]);

                nbCharactere = Integer.parseInt(elements[4]);

                photoPath = elements[5];

                scoreLignes.add(new Scores(name, time, nbWordWrite, nbWordWrittenCaracteres, nbCharactere, photoPath));
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
            File file = new File(context.getFilesDir() + File.separator + filePath);
            InputStream in = new BufferedInputStream(new FileInputStream(file));
            byte[] buf = new byte[(int) file.length()];
            int numRead = in.read(buf);

            // On convertie ces bytes en Bitmap
            img = BitmapFactory.decodeByteArray(buf, 0, numRead);

            // Redimensionne l'image pour ne pas prendre trop de place en mémoire
            img = Bitmap.createScaledBitmap(img, IMAGE_HEIGHT, IMAGE_WIDTH, false);

            // Rotation pour être en mode paysage
            img = rotateImage(img, 90);

            //System.out.println("appelé après la conversion : " + img);

        } catch(FileNotFoundException e) {
            Log.d("readImage", "fichier " + filePath + " introuvable");
        } catch(IOException e) {
            Log.d("readImage", "read buffer fonctionne pas");
        }


        return img;

    }

    public static Bitmap rotateImage(Bitmap src, float degree)
    {
        // create new matrix
        Matrix matrix = new Matrix();
        // setup rotation degree
        matrix.postRotate(degree);
        Bitmap bmp = Bitmap.createBitmap(src, 0, 0, src.getWidth(), src.getHeight(), matrix, true);
        return bmp;
    }
}
