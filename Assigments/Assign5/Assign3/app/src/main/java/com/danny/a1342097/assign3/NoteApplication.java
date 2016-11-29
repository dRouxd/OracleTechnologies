package com.danny.a1342097.assign3;

import android.app.Application;

/**
 * Created by 1342097 on 2016-11-25.
 */
public class NoteApplication extends Application {

    public static String HOST = "192.168.1.240";
    public static String PORT = "9999";
    public static String PREFIX = "http://" + HOST + ":" + PORT;

    public static String CONNECTED_USER = "http://localhost:9999/user/61";

    @Override
    public void onCreate() {
        super.onCreate();
    }

}
