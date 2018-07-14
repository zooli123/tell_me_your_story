package com.tellyourstory.view;

import android.content.Context;
import android.os.Handler;
import android.support.annotation.NonNull;

import com.tellyourstory.R;
import com.tellyourstory.data.ImageChangeScheduler;
import com.tellyourstory.data.ImageProvider;
import com.tellyourstory.data.MusicPlayer;

import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.UiThread;

import static org.androidannotations.annotations.UiThread.Propagation.REUSE;

@EBean
class MainControllerImpl implements MainContract.Controller {
    private static final int DELAY_MILLIS = 3000;

    @Bean
    ImageChangeScheduler imageChangeScheduler;

    @Bean
    ImageProvider imageProvider;

    @Bean
    MusicPlayer musicPlayer;

    private MainContract.Activity view;
    private String bottomText;

    @Override
    public void initController(final MainContract.Activity view) {
        this.view = view;
        musicPlayer.initMediaPlayer();
    }

    @Override
    public void startChangingPictures() {
        setCryButtonImageWithDelay();
    }

    @Override
    public void stopChangingPictures() {
        imageChangeScheduler.cancelSchedule();
    }

    @Override
    public void handleMusicAndView() {
        if (musicPlayer.isPlaying()) {
            handleStop();
        } else {
            handleStart();
        }
    }

    @Override
    public void handleStop() {
        musicPlayer.stopMusic();
        updateViewsWhenMusicStopped();
    }

    private void handleStart() {
        musicPlayer.startMusic();
        updateViewsWhenMusicIsPlaying();
    }

    private void updateViewsWhenMusicStopped() {
        bottomText = "";
        view.updateBottomText(bottomText);
        view.updateTitle(getStringResource(R.string.wait));
    }

    private void updateViewsWhenMusicIsPlaying() {
        bottomText = getStringResource(R.string.dont_hold_back);
        setBottomTextWithDelay();
        view.updateTitle(getStringResource(R.string.tell_your_story));
    }

    @NonNull
    private String getStringResource(final int resId) {
        return ((Context) view).getResources().getString(resId);
    }

    private void setBottomTextWithDelay() {
        new Handler().postDelayed(() -> updateBottomText(bottomText), DELAY_MILLIS);
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
