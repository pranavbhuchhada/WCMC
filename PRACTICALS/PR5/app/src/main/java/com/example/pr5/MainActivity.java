package com.example.pr5;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private EditText username;
    private EditText password;
    private Button login;
    private Button cancel;
    private TextView status;
    private byte count;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        login = findViewById(R.id.login);
        cancel = findViewById(R.id.cancel);
        status = findViewById(R.id.status);

        count = 0;

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                count++;
                String user = String.valueOf(username.getText());
                String pass = String.valueOf(password.getText());
                if (user.equals("admin") && pass.equals("admin")){
                    count=0;
                    status.setText("Success!!");
                    status.setBackgroundColor(Color.GREEN);
                }
                else{
                    status.setText("Failed!!");
                    status.setBackgroundColor(Color.RED);
                }
                if (count>2){
                    Toast.makeText(getApplicationContext(),"Too Many Attempts",Toast.LENGTH_LONG).show();
                    login.setVisibility(View.INVISIBLE);
                }
                username.setText("");
                password.setText("");
            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.exit(0);
            }
        });

    }
}
