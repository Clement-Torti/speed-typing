package com.example.speed_typing.model.Util;

import android.content.Context;
import android.media.MediaPlayer;

import com.example.speed_typing.R;

public class SoundBox {
    private static MediaPlayer soundPlayer;
    private static MediaPlayer musicPlayer;
    private static int length = 0;

    public static void playBackgroundSound(Context context) {
        playMusic(R.raw.bg_music, context);
    }

    public static void playSuccessSound(Context context) {
        playSound(R.raw.sucess, context);
    }

    public static void playFailSound(Context context) {
        playSound(R.raw.fail, context);
    }

    private static void playSound(int id, Context context) {
        soundPlayer = MediaPlayer.create(context, id);
        soundPlayer.setVolume((float)0.5, (float)0.5);
        soundPlayer.start();
    }

    private static void playMusic(int id, Context context) {
        if(musicPlayer == null){
            musicPlayer = MediaPlayer.create(context, id);
            musicPlayer.setLooping(true);
            musicPlayer.start();
        }
        else{
            musicPlayer.seekTo(length);
            musicPlayer.start();
        }
    }

    public static void stopMusic() {
        if(musicPlayer != null) {
            musicPlayer.stop();
            musicPlayer = null;
            length = 0;
        }
    }

    public static void pauseMusic(){
        musicPlayer.pause();
        length = musicPlayer.getCurrentPosition();
    }
}
