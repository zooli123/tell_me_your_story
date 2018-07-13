package com.tellyourstory;

import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

@EActivity(R.layout.activity_main)
public class MainActivity extends AppCompatActivity implements MainContract.Activity {

    @Bean(MainControllerImpl.class)
    MainContract.Controller controller;

    @ViewById(R.id.title_textview)
    TextView title;

    @ViewById(R.id.bottom_textview)
    TextView bottomText;

    @AfterViews
    public void init() {
        controller.initController(this);
        updateTitle(getString(R.string.wait));
    }

    @Override
    public void onPause() {
        super.onPause();
        controller.stopMusic();
    }

    @Click(R.id.cry_button)
    void startToCry() {
        controller.handleMusic();
    }

    @Override
    public void updateTitle(final String text) {
        title.setText(text);
    }

    @Override
    public void updateBottomText(final String text) {
        bottomText.setText(text);
    }
}
