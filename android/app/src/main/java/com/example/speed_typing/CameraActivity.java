package com.example.speed_typing;

import android.Manifest;
import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import static android.provider.MediaStore.Files.FileColumns.MEDIA_TYPE_IMAGE;

public class CameraActivity extends AppCompatActivity {

    private Camera camera;
    // Image prise par l'utilisateur

    private Camera.PictureCallback picture = new Camera.PictureCallback() {

        /*
         * Appelé lorsqu'une image est enregistrée par l'utilisateur
         */
        /*
        @Override
        public void onPictureTaken(byte[] data, Camera camera) {
            File pictureFile = getOutputMediaFile(MEDIA_TYPE_IMAGE);
            if (pictureFile == null){
                Log.d(TAG, "Error creating media file, check storage permissions");
                return;
            }

            try {
                FileOutputStream fos = new FileOutputStream(pictureFile);
                fos.write(data);
                fos.close();
            } catch (FileNotFoundException e) {
                Log.d(TAG, "File not found: " + e.getMessage());
            } catch (IOException e) {
                Log.d(TAG, "Error accessing file: " + e.getMessage());
            }

        }
        */
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
        camera.takePicture(null, null, picture);

    }


}
