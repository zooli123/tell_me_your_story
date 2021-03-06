package com.tellyourstory.view;

import android.support.v7.app.AppCompatActivity;
import android.widget.ImageButton;
import android.widget.TextView;

import com.tellyourstory.R;

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

    @ViewById(R.id.cry_button)
    ImageButton cryBtn;

    @AfterViews
    public void init() {
        controller.initController(this);
        updateTitle(getString(R.string.wait));
    }

    @Override
    public void onPause() {
        super.onPause();
        controller.handleStop();
        controller.stopChangingPictures();
    }

    @Override
    public void onResume() {
        super.onResume();
        controller.startChangingPictures();
    }

    @Click(R.id.cry_button)
    void startToCry() {
        controller.handleMusicAndView();
    }

    @Override
    public void updateTitle(final String text) {
        title.setText(text);
    }

    @Override
    public void updateBottomText(final String text) {
        bottomText.setText(text);
    }

    @Override
    public void updateCryButton(final int resId) {
        cryBtn.setImageResource(resId);
    }

}
