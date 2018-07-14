package com.tellyourstory.data;

import android.content.Context;
import android.media.MediaPlayer;

import com.tellyourstory.R;

import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;

@EBean
public class MusicPlayer {
    @RootContext
    Context context;

    private MediaPlayer mediaPlayer;

    public void initMediaPlayer() {
        mediaPlayer = MediaPlayer.create(context, R.raw.sad_violin);
    }

    public void startMusic() {
        mediaPlayer.start();
    }

    public void stopMusic() {
        mediaPlayer.stop();
        initMediaPlayer();
    }

    public boolean isPlaying() {
        return mediaPlayer.isPlaying();
    }
}
