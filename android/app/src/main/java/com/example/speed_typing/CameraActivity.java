package com.example.speed_typing;

import android.Manifest;
import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.hardware.camera2.*;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.example.speed_typing.model.Util.ScoreWriter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import static android.provider.MediaStore.Files.FileColumns.MEDIA_TYPE_IMAGE;

public class CameraActivity extends BaseActivity {

    private Camera camera;
    private String photoPath;
    // Image prise par l'utilisateur

    private Camera.PictureCallback picture = new Camera.PictureCallback() {

        /*
         * Appelé lorsqu'une image est enregistrée par l'utilisateur
         */
        @Override
        public void onPictureTaken(byte[] data, Camera camera) {
            // On génère le fichier de l'image dans un chemin
            photoPath = ScoreWriter.generateRandomPath();
            File pictureFile = new File(getFilesDir() + File.separator + photoPath);

            if (pictureFile == null){
                Log.d("onPictureTaken", "Problème de création d'un fichier dans onPictureTaken");
                return;
            }

            try {
                // On écrit les données de l'image dans le nouveau fichier
                FileOutputStream fos = new FileOutputStream(pictureFile);
                fos.write(data);
                fos.close();

                // Une fois l'image enregistrée, on retourne dans la endGameActivity
                Map<String, Serializable> photoParams = new HashMap<>();
                photoParams.put("photoPath", photoPath);
                changeActivity(EndGameActivity.class, photoParams);


            } catch (FileNotFoundException e) {
                Log.d("onPictureTaken", "File not found: " + e.getMessage());
            } catch (IOException e) {
                Log.d("onPictureTaken", "Error accessing file: " + e.getMessage());
            }

        }



    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);

        // Demander les permissions d'acces à la camera
        if (ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CAMERA)
                == PackageManager.PERMISSION_DENIED) {
            ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.CAMERA}, 1);
        } else {
            configureCameraView();
        }
    }

    /*
    * Méthode qui nous renvoit la réponse de l'utilisateur suite à la demande d'acces à la camera
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            configureCameraView();
        } else {
            Toast.makeText(this, R.string.cameraAccess, Toast.LENGTH_LONG).show();
            ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.CAMERA}, 1);
        }

    }

    /*
    * Accède à une instance de la camera
     */
    private Camera getCameraInstance() {
        Camera c = null;

        try {
            c = Camera.open();
        } catch (Exception e) {
            Toast.makeText(this, R.string.cameraUnavailable, Toast.LENGTH_SHORT).show();
        }

        return c;
    }


    /*
    * Met en place l'utilisation de la camera
     */
    private void configureCameraView() {
        camera = getCameraInstance();

        if(camera != null) {
            // Ajout de la cameraView dans notre vue
            FrameLayout preview = (FrameLayout) findViewById(R.id.cameraPreview);
            CameraView cameraView = new CameraView(this, camera);

            preview.addView(cameraView);


        }
    }

    /*
    * Quand l'utilisateur prend une photo
     */
    public void takeSnapshot(View view) {
        // Appelle le pictureCallback pour prévenir que l'utilisateur à cliquer sur le bouton photo
        camera.takePicture(null, null, picture);

    }



}
