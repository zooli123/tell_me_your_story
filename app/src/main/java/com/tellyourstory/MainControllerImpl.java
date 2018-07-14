package com.tellyourstory;

import android.content.Context;
import android.media.MediaPlayer;
import android.os.Handler;
import android.support.annotation.NonNull;

import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.UiThread;

import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import static java.util.Arrays.asList;
import static org.androidannotations.annotations.UiThread.Propagation.REUSE;

@EBean
class MainControllerImpl implements MainContract.Controller {
    private static final int DELAY_MILLIS = 3000;
    private static final int PICTURE_DELAY_MILLIS = 2000;
    private MediaPlayer mPlayer;
    private MainContract.Activity view;
    private String bottomText;
    private Timer timer;
    private List<Integer> IMAGE_COLLECTION;

    @Override
    public void initController(final MainContract.Activity view) {
        this.view = view;
        IMAGE_COLLECTION = buildImageCollection();
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
        timer.cancel();
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
        handler.postDelayed(() -> updateBottomText(bottomText), DELAY_MILLIS);
    }

    @Background
    void setCryButtonImageWithDelay() {
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                updateCryButton();
            }
        }, 0, PICTURE_DELAY_MILLIS);
    }

    @UiThread(propagation = REUSE)
    void updateCryButton() {
        view.updateCryButton(selectRandom(IMAGE_COLLECTION));
    }

    @UiThread(propagation = REUSE)
    void updateBottomText(final String text) {
        view.updateBottomText(text);
    }

    private Integer selectRandom(final List<Integer> imageCollection) {
        final Random random = new Random();
        return imageCollection.get(random.nextInt((imageCollection.size())));
    }

    private List<Integer> buildImageCollection() {
        return asList(
                R.drawable.cry,
                R.drawable.dawson_crying_mini,
                R.drawable.sad_frog_mini,
                R.drawable.cry_lake
        );
    }
}
