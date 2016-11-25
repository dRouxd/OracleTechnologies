package com.danny.a1342097.assign3;

import android.app.Application;

/**
 * Created by 1342097 on 2016-11-25.
 */
public class NoteApplication extends Application {

    public static String HOST = "10.32.2.24";
    public static String PORT = "9999";
    public static String PREFIX = "http://" + HOST + ":" + PORT;
    @Override
    public void onCreate() {
        super.onCreate();
    }

}
