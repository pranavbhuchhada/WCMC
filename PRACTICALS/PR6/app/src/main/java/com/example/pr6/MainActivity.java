package com.example.pr6;

import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraManager;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.InputType;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;
import android.widget.ToggleButton;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private LinearLayout page;
    private ToggleButton button;
    private CameraManager cameraManager;
    private String cameraID;
    private EditText timer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (!getApplicationContext().getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA_FLASH)){
            Toast.makeText(getApplicationContext(),"No Flash Available!!",Toast.LENGTH_LONG).show();
            System.exit(0);
        }

        cameraManager = (CameraManager)getSystemService(Context.CAMERA_SERVICE);
        try {
            cameraID = cameraManager.getCameraIdList()[0];
        }catch (Exception eo){}

        page = findViewById(R.id.page);
        button = findViewById(R.id.button);
        timer = findViewById(R.id.timer);
        button.setChecked(true);
        timer.setInputType(InputType.TYPE_NUMBER_FLAG_SIGNED);
        button.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                try {
                    Integer time = Integer.valueOf(String.valueOf(timer.getText()))*1000;
                    if (time<1000) throw new Exception();


                    CountDownTimer countDownTimer=new CountDownTimer(time*10,time){
                        boolean temp = false;
                        @Override
                        public void onTick(long millisUntilFinished) {
                            try {
                                cameraManager.setTorchMode(cameraID,temp = !temp);
                            } catch (CameraAccessException e) {}
                        }

                        @Override
                        public void onFinish() {
                        }

                    }.start();
                    if (!b){
                        try {

                        }
                        catch (Exception eo){
                            cameraManager.setTorchMode(cameraID,!b);
                            page.setBackgroundColor(Color.WHITE);
                            button.setBackgroundColor(Color.BLACK);
                            button.setTextColor(Color.WHITE);
                        }
                    }
                    else {
                        page.setBackgroundColor(Color.BLACK);
                        button.setBackgroundColor(Color.WHITE);
                        button.setTextColor(Color.BLACK);
                    }
                }catch (Exception eo){}
            }
        });

    }
}