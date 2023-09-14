package com.example.first;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    public static final String TAG = "myLogs";
    private String lastMassege = "First_Message_2";
    private EditText editText;
    private TextView textview;
    private Button button1;
    private Button button2;
    private Button button3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //
        initializeComponent();
        initializeLiseners();
        //
        Log.d(TAG, "MainActivity onCreate() done");
    }

    private void initializeComponent() {
        editText = (EditText) findViewById(R.id.editText1);
        textview = (TextView) findViewById(R.id.textView1);
        button1 = (Button) findViewById(R.id.button1);
        button2 = (Button) findViewById(R.id.button2);
        button3 = (Button) findViewById(R.id.button3);
    }

    private void initializeLiseners() {
        button1.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String temp = editText.getText().toString();
                        editText.setText("Hello " + temp);
                        textview.setText("Hello " + temp);
                        lastMassege = "Hello " + temp;
                        AndroidApplication.get().setData("Hello " + temp);
                        //AndroidApplication.get().setTextEdit1(editText);
                        Toast.makeText(MainActivity.this, "Button Save done", Toast.LENGTH_LONG).show();
                        Log.d(TAG, "MainActivity onClick() SaveButton done");
                    }
                }
        );
        button2.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        //editText.setText(lastMassege);
                        //textview.setText(lastMassege);
                        //String text = "";
                        String text = AndroidApplication.get().getData();
                        //String text = "";
                        if ((text == null) || (text.isEmpty()) || text.equalsIgnoreCase("EMPTY")) {
                            text = lastMassege;
                        }
                        editText.setText(text);
                        //editText = AndroidApplication.get().getTextEdit1();
                        textview.setText(text);
                        Toast.makeText(MainActivity.this, "Button Read done", Toast.LENGTH_LONG).show();
                        Log.d(TAG, "MainActivity onClick() ReadButton done");
                    }
                }
        );
        button3.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(MainActivity.this, TwoActivity.class);
                        intent.putExtra("key", lastMassege);
                        startActivity(intent);
                        Toast.makeText(MainActivity.this, "Button TwoButton done", Toast.LENGTH_LONG).show();
                        Log.d(TAG,"MainActivity onClick() Start Second Activity done");
                    }
                }
        );
    }

    /*
    public void myClickHandler(View view) {
        String temp;
        EditText text = (EditText) findViewById(R.id.editText1);
        TextView textview = (TextView) findViewById(R.id.textView1);
        switch (view.getId()) {
            case R.id.button1:
                temp = text.getText().toString();
                text.setText("Hello " + temp);
                textview.setText("Hello " + temp);
                //lastMassege = "Hello " + temp;
                //AndroidApplication.get().setData(lastMassege);
                Log.d(TAG, "MainActivity onClick() Button done");
                break;
        }
    }
    */

    @Override
    public void onStart() {
        super.onStart();
        Log.d(TAG, "MainActivity onStart() done");
    }

    @Override
    public void onRestart() {
        super.onRestart();
        Log.d(TAG, "MainActivity onRestart() done");
    }

    @Override
    public void onResume() {
        super.onResume();
        //restoreComponent();
        //
        String text = AndroidApplication.get().getData();
        editText.setText(text);
        textview.setText(text);
        //
        Log.d(TAG, "MainActivity onResume() done");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "MainActivity onPause() done");
        //
        // saveComponent();
        // AndroidApplication
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d(TAG, "MainActivity onStop() done");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "MainActivity onDestroy() done");
    }
}