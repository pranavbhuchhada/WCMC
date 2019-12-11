package com.example.pr2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    protected EditText editText1;
    protected EditText editText2;
    protected Button button1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText1 = (EditText) findViewById(R.id.editText1);
        editText2 = (EditText) findViewById(R.id.editText2);
        button1 = (Button) findViewById(R.id.button);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                float num1 = Float.parseFloat(String.valueOf(editText1.getText())) ;
                float num2 = Float.parseFloat(String.valueOf(editText2.getText())) ;
                Double sum = null;
                try {
                    sum = (double)(num1 + num2);
                }
                catch (ArithmeticException eo){
                    sum = (double)0;
                }
                Context context = getApplicationContext();
                CharSequence text = String.valueOf(sum);
                Toast toast = Toast.makeText(context, text, Toast.LENGTH_LONG);
                toast.show();
            }
        });
    }
}
