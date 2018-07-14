package com.tellyourstory.data;

import com.tellyourstory.R;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.EBean;

import java.util.List;
import java.util.Random;

import lombok.val;

import static java.util.Arrays.asList;

@EBean
public class ImageProvider {
    private List<Integer> IMAGE_COLLECTION;

    @AfterInject
    void init() {
        IMAGE_COLLECTION = buildImageCollection();
    }

    public Integer provideImageResId() {
        return selectRandomResId(IMAGE_COLLECTION);
    }

    private List<Integer> buildImageCollection() {
        return asList(
                R.drawable.cry,
                R.drawable.dawson_crying_mini,
                R.drawable.sad_frog_mini,
                R.drawable.cry_lake
        );
    }

    private Integer selectRandomResId(final List<Integer> imageCollection) {
        val random = new Random();
        return imageCollection.get(random.nextInt((imageCollection.size())));
    }

}
