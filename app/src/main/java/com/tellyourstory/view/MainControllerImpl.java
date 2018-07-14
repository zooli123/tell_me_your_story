package com.tellyourstory.view;

import android.content.Context;
import android.media.MediaPlayer;
import android.os.Handler;
import android.support.annotation.NonNull;

import com.tellyourstory.R;
import com.tellyourstory.data.ImageChangeScheduler;
import com.tellyourstory.data.ImageProvider;

import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.UiThread;

import lombok.val;

import static org.androidannotations.annotations.UiThread.Propagation.REUSE;

@EBean
class MainControllerImpl implements MainContract.Controller {
    private static final int DELAY_MILLIS = 3000;

    @Bean
    ImageChangeScheduler imageChangeScheduler;

    @Bean
    ImageProvider imageProvider;

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

    @Override
    public void startChangingPictures() {
        setCryButtonImageWithDelay();
    }

    @Override
    public void stopChangingPictures() {
        imageChangeScheduler.cancelSchedule();
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
        val handler = new Handler();
        handler.postDelayed(() -> updateBottomText(bottomText), DELAY_MILLIS);
    }

    @Background
    void setCryButtonImageWithDelay() {
        imageChangeScheduler.schedule(__ -> updateCryButton());
    }

    @UiThread(propagation = REUSE)
    void updateBottomText(final String text) {
        view.updateBottomText(text);
    }

    @UiThread(propagation = REUSE)
    void updateCryButton() {
        view.updateCryButton(imageProvider.provideImageResId());
    }
}
