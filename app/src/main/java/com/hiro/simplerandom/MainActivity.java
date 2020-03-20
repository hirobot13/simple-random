package com.hiro.simplerandom;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    TextView txvResult;
    EditText etxMin, etxMax ;
    Button btnGenerate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txvResult = (TextView) findViewById(R.id.txvValue);
        etxMin = (EditText) findViewById(R.id.etxMin);
        etxMax = (EditText) findViewById(R.id.etxMax);
        btnGenerate = (Button) findViewById(R.id.btnGenerate);

        btnGenerate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Random random = new Random();
                int min = Integer.parseInt(etxMin.getText().toString());
                int max = Integer.parseInt(etxMax.getText().toString());
                int result = random.nextInt(max - min + 1) + min;
                txvResult.setText(Integer.toString(result));
            }
        });
    }
}
