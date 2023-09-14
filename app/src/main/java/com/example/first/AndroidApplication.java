package com.example.first;

import android.app.Application;
import android.content.res.Configuration;
import android.util.Log;
import android.widget.EditText;

public class AndroidApplication extends Application {
    private static AndroidApplication instance = null;
    private String data = "EMPTY";
    private EditText textEdit1;  // Do not save UI Elements, it is Invalid Solution

    public static AndroidApplication get() {
        Log.d(MainActivity.TAG, "\tAndroidApplication get()");
        return instance;
    }

    @Override
    public final void onCreate() {
        super.onCreate();
        instance = this;
        Log.d(MainActivity.TAG, "\tAndroidApplication onCreate()");
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        Log.d(MainActivity.TAG, "\tAndroidApplication onConfigurationChanged()");
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        Log.d(MainActivity.TAG, "\tAndroidApplication onLowMemory()");
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        Log.d(MainActivity.TAG, "\tAndroidApplication onTerminate()");
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public EditText getTextEdit1() {
        return textEdit1;
    }

    public void setTextEdit1(EditText textEdit1) {
        this.textEdit1 = textEdit1;
    }

}
