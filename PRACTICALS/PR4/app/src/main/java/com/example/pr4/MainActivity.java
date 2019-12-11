package com.example.pr4;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    EditText kelvin;
    EditText fahrenheit;
    EditText celsius;
    TextWatcher celsiusWatcher;
    TextWatcher kelvinWatcher;
    TextWatcher fahrenheitWatcher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        kelvin = findViewById(R.id.kelvin);
        fahrenheit = findViewById(R.id.fahrenheit);
        celsius = findViewById(R.id.celsius);
        kelvin.setText(String.valueOf(0));
        fahrenheit.setText(String.valueOf(-459.69));
        celsius.setText(String.valueOf(-273.15));

        kelvinWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                fahrenheit.removeTextChangedListener(fahrenheitWatcher);
                celsius.removeTextChangedListener(celsiusWatcher);
                try {
                    double k = Double.valueOf(String.valueOf(kelvin.getText()));
                    double c = k - 273.15;
                    double f = c*(double)9/(double)(5) + (double)32;

                    fahrenheit.setText(String.valueOf(Math.round(f*10)/10.0));
                    celsius.setText(String.valueOf(Math.round(c*10)/10.0));
                }
                catch (Exception eo){}
                fahrenheit.addTextChangedListener(fahrenheitWatcher);
                celsius.addTextChangedListener(celsiusWatcher);
            }
            @Override
            public void afterTextChanged(Editable editable) {


            }
        };
        fahrenheitWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                kelvin.removeTextChangedListener(kelvinWatcher);
                celsius.removeTextChangedListener(celsiusWatcher);
                try {
                    double f = Double.valueOf(String.valueOf(fahrenheit.getText()));
                    double c = (f - 32)*(double) 5/(double) 9;
                    double k = c + 273.15;

                    kelvin.setText(String.valueOf(Math.round(k*10)/10.0));
                    celsius.setText(String.valueOf(Math.round(c*10)/10.0));
                }
                catch (Exception eo){}
                kelvin.addTextChangedListener(kelvinWatcher);
                celsius.addTextChangedListener(celsiusWatcher);

            }

            @Override
            public void afterTextChanged(Editable editable) {


            }
        };
        celsiusWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                kelvin.removeTextChangedListener(kelvinWatcher);
                fahrenheit.removeTextChangedListener(fahrenheitWatcher);
                try {
                    double c = Double.valueOf(String.valueOf(celsius.getText()));
                    double k = c + 273.15;
                    double f = c*(double)9/(double)(5) + (double)32;

                    kelvin.setText(String.valueOf(Math.round(k*10)/10.0));
                    fahrenheit.setText(String.valueOf(Math.round(f*10)/10.0));
                }
                catch (Exception eo){}
                kelvin.addTextChangedListener(kelvinWatcher);
                fahrenheit.addTextChangedListener(fahrenheitWatcher);
            }
        };
        kelvin.addTextChangedListener(kelvinWatcher);
        fahrenheit.addTextChangedListener(fahrenheitWatcher);
        celsius.addTextChangedListener(celsiusWatcher);

    }
}
