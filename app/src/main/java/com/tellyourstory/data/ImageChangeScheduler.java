package com.tellyourstory.data;

import com.annimon.stream.function.Consumer;

import org.androidannotations.annotations.EBean;

import java.util.Timer;
import java.util.TimerTask;

@EBean
public class ImageChangeScheduler {
    private static final int PICTURE_DELAY_MILLIS = 2000;
    private Timer timer;

    public void schedule(final Consumer consumer) {
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                consumer.accept(this);
            }
        }, 0, PICTURE_DELAY_MILLIS);
    }

    public void cancelSchedule() {
        timer.cancel();
    }
}
