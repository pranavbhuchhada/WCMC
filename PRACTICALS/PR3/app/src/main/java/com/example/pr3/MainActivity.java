package com.example.pr3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Chronometer;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Chronometer chronometer;
    private TextView textView;
    private int time;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        chronometer = (Chronometer)findViewById(R.id.chronometer);
        textView = (TextView)findViewById(R.id.mytext);
        chronometer.start();
        time = 0;
        chronometer.setOnChronometerTickListener(new Chronometer.OnChronometerTickListener() {
            @Override
            public void onChronometerTick(Chronometer mychronometer) {
                time++;
                if (time%11==0){
                    Toast.makeText(getApplicationContext(),"10 Second Happened",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
