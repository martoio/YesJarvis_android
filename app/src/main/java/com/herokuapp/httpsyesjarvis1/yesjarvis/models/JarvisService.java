package com.herokuapp.httpsyesjarvis1.yesjarvis.models;

/**
 * Created by Martin on 2017-01-01 for YesJarvis.
 */

//TODO: add static final vars to represent the different services
public class JarvisService {
    String mName;
    int mPhoto;

    public JarvisService(String name, int photo){
        mName = name;
        mPhoto = photo;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public int getPhoto() {
        return mPhoto;
    }

    public void setPhoto(int photo) {
        mPhoto = photo;
    }
}
