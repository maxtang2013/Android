package com.example.maxtang.audioexample;

import android.content.Context;
import android.media.MediaPlayer;

/**
 * Created by maxtang on 16/4/15.
 */
public class AudioPlayer {
    MediaPlayer mPlayer;

    private static AudioPlayer sInstance = null;
    public static AudioPlayer getInstance() {
        if (sInstance == null) {
            sInstance = new AudioPlayer();
        }
        return sInstance;
    }

    private AudioPlayer() {
    }

    public  void stop() {
        if (mPlayer != null) {
            mPlayer.stop();
            mPlayer.release();
            mPlayer = null;
        }
    }

    public void play(Context c) {
        stop();

        mPlayer = MediaPlayer.create(c, R.raw.mad_world);
        mPlayer.start();
    }
}
