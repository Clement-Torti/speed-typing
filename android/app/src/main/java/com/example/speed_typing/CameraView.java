package com.example.speed_typing;

import android.content.Context;
import android.hardware.Camera;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.io.IOException;

public class CameraView extends SurfaceView implements SurfaceHolder.Callback {
    private SurfaceHolder mHolder;
    private Camera mCamera;

    public CameraView(Context context, Camera camera) {
        super(context);
        mCamera = camera;
        // Pour être en mode portrait
        mCamera.setDisplayOrientation(90);

        // Le SurfaceHolder.Callback nous permet de savoir quand le layout qui affiche la camera est créer puis détruit
        mHolder = getHolder();
        mHolder.addCallback(this);

    }

    public void surfaceCreated(SurfaceHolder holder) {
        // Une fois que la surface d'affichage est créer, on associe à la caméra sa zone d'affichage
        try {
            mCamera.setPreviewDisplay(holder);
            mCamera.startPreview();
        } catch (IOException e) {

        }
    }

    public void surfaceDestroyed(SurfaceHolder holder) {
        // supprimer la cameraPreview à la fin de l'activité
    }

    /*
    * Appeler si la zone d'affichage change de taille ou tourne
     */

    public void surfaceChanged(SurfaceHolder holder, int format, int w, int h) {

        if (mHolder.getSurface() == null){
            // preview surface does not exist
            return;
        }

        // Arreter la preview avant d'appliquer les changements
        try {
            mCamera.stopPreview();
        } catch (Exception e){
            // ignore: tried to stop a non-existent preview
        }

        // Les changements doivent etre effectué ici
        // En fonction de l'orientation de la camera, on la met en mode portrait ou paysage

        if(w > h) {
            mCamera.setDisplayOrientation(0);
        } else {
            mCamera.setDisplayOrientation(270);
        }

        // start preview with new settings
        try {
            mCamera.setPreviewDisplay(mHolder);
            mCamera.startPreview();

        } catch (Exception e){
            Log.d("surfaceChanged", "Error starting camera preview: " + e.getMessage());
        }
    }



}
