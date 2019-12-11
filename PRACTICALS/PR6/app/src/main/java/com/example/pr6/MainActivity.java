package com.example.pr6;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.hardware.camera2.CameraManager;
import android.os.Build;
import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.Toast;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity {
    private LinearLayout page;
    private ToggleButton button;
    private CameraManager cameraManager;
    private String cameraID;
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

        button.setChecked(true);

        button.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                try {
                    cameraManager.setTorchMode(cameraID,!b);
                    if (!b){
                        page.setBackgroundColor(Color.WHITE);
                        button.setBackgroundColor(Color.BLACK);
                        button.setTextColor(Color.WHITE);
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