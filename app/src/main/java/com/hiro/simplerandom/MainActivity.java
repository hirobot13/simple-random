package com.hiro.simplerandom;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    TextView txvResult;
    EditText etxMin, etxMax ;
    Button btnGenerate;

    ArrayList<Integer> randomlist;
    boolean isExists;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txvResult = (TextView) findViewById(R.id.txvValue);
        etxMin = (EditText) findViewById(R.id.etxMin);
        etxMax = (EditText) findViewById(R.id.etxMax);
        btnGenerate = (Button) findViewById(R.id.btnGenerate);
        randomlist = new ArrayList<>();

        btnGenerate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Random random = new Random();
                try {
                    int min = Integer.parseInt(etxMin.getText().toString());
                    int max = Integer.parseInt(etxMax.getText().toString());
                    int result = random.nextInt(max - min + 1) + min;
                    if(randomlist.size()>(max - min)){
                        Toast.makeText(MainActivity.this, "Đã hết số để random, ứng dụng sẽ random lại từ đầu!", Toast.LENGTH_LONG).show();
                        randomlist.clear();
                    }

                    if (randomlist.size() == 0 || randomlist == null) {
                        randomlist.add(result);
                    } else {
                        while (IsExist(result, randomlist)){
                            result = random.nextInt(max - min + 1) + min;
                        }
                        randomlist.add(result);
                    }
                    txvResult.setText(Integer.toString(result));

                } catch (Exception e){
                    Toast.makeText(MainActivity.this, "Lỗi, xin nhập số đầy đủ và chính xác! " +
                            "Nhập số bắt đầu vào min và số cuối của dãy cần random vào max", Toast.LENGTH_LONG).show();
                }

            }
        });
    }

    public boolean IsExist(int num, ArrayList<Integer> randomlist){
        boolean result = false;
        for (int i : randomlist) {
            if (num == i){
                result = true;
                break;
            }
        }
        return result;
    }
}
