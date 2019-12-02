package com.example.myapplication;

import android.app.Application;
import android.content.Context;

import java.util.ArrayList;

public class App extends Application {

    private static Context context;
    private static int ids = 0;
    private static ArrayList<String> views = new ArrayList<String>();

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

    public static ArrayList<String> getViews() {
        return views;
    }

    public static void addToArrayList(String view){
        views.add(view);
    }
}
