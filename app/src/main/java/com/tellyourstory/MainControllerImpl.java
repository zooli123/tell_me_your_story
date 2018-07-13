package com.tellyourstory;

import android.content.Context;
import android.media.MediaPlayer;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.UiThread;

import org.androidannotations.annotations.EBean;

@EBean
class MainControllerImpl implements MainContract.Controller {
    private static final int DELAY_MILLIS = 3000;
    private MediaPlayer mPlayer;
    private MainContract.Activity view;
    private String bottomText;

    @Override
    public void initController(final MainContract.Activity view) {
        this.view = view;
        initMediaPlayer();
    }

    @Override
    public void handleMusic() {
        if (mPlayer.isPlaying()) {
            stopMusic();
        } else {
            startMusic();
        }
    }

    @Override
    public void stopMusic() {
        mPlayer.stop();
        bottomText = "";
        view.updateBottomText(bottomText);
        initMediaPlayer();
        view.updateTitle(getStringResource(R.string.wait));
    }

    private void startMusic() {
        mPlayer.start();
        view.updateTitle(getStringResource(R.string.tell_your_story));
        bottomText = getStringResource(R.string.dont_hold_back);
        setBottomTextWithDelay();
    }

    @NonNull
    private String getStringResource(final int resId) {
        return ((Context) view).getResources().getString(resId);
    }

    private void initMediaPlayer() {
        mPlayer = MediaPlayer.create((Context) view, R.raw.sad_violin);
    }

    private void setBottomTextWithDelay() {
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                updateBottomText(bottomText);
            }
        }, DELAY_MILLIS);
    }

    @UiThread
    private void updateBottomText(final String text) {
        view.updateBottomText(text);
    }
}
