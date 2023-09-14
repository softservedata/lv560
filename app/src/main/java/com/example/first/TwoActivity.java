package com.example.first;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

public class TwoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_two);
        //
        Bundle passedData = getIntent().getExtras();
        String myParameter = passedData.getString("key", "default");
        EditText editText = (EditText) findViewById(R.id.editText1);
        editText.setText(myParameter);
        Toast.makeText(TwoActivity.this, "get myParameter = " + myParameter, Toast.LENGTH_LONG);
        Log.d(MainActivity.TAG, "TwoActivity onCreate() done");
    }


    @Override
    public void onStart() {
        super.onStart();
        Log.d(MainActivity.TAG, "TwoActivity onStart() done");
    }

    @Override
    public void onRestart() {
        super.onRestart();
        Log.d(MainActivity.TAG, "TwoActivity onRestart() done");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d(MainActivity.TAG, "TwoActivity onResume() done");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(MainActivity.TAG, "TwoActivity onPause() done");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d(MainActivity.TAG, "TwoActivity onStop() done");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(MainActivity.TAG, "TwoActivity onDestroy() done");
    }

}