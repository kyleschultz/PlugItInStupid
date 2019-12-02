package com.example.myapplication;

import android.app.Application;
import android.content.Context;

public class App extends Application {

    private static Context context;
    private static int ids = 0;

    public static Context getContext() {
        return context;
    }

    public static void setContext(Context c) {
        context = c;
    }

    public static int getIds() { return ids; }

    public static void setIds(int id) {
        ids = id;
    }
}
