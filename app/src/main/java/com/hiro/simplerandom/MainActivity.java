package com.hiro.simplerandom;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Process;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    TextView txvValue;
    EditText etxMin, etxMax ;
    Button btnGenerate;

    ArrayList<Integer> randomlist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ShowLoginDialog();
    }

    private void Main(){
        setContentView(R.layout.activity_main);

        txvValue = (TextView) findViewById(R.id.txvValue);
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
                    if (randomlist.size() > (max - min)) {
                        Toast.makeText(MainActivity.this, "Đã hết số để random, ứng dụng sẽ random lại từ đầu!", Toast.LENGTH_LONG).show();
                        randomlist.clear();
                    }

                    if (randomlist.size() == 0 || randomlist == null) {
                        randomlist.add(result);
                    } else {
                        while (IsExist(result, randomlist))  {
                            result = random.nextInt(max - min + 1) + min;
                        }
                        randomlist.add(result);
                    }
                    txvValue.setText(Integer.toString(result));

                } catch (Exception e) {
                    Toast.makeText(MainActivity.this, "Lỗi, xin nhập số đầy đủ và chính xác! " +
                            "Nhập số bắt đầu vào min và số cuối của dãy cần random vào max", Toast.LENGTH_LONG).show();
                }

            }
        });
    }

    private void ShowLoginDialog(){
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCanceledOnTouchOutside(false);
        dialog.setContentView(R.layout.login);
        dialog.show();

        final EditText etxUserName = dialog.findViewById(R.id.etxUserName);
        final EditText etxPassword = dialog.findViewById(R.id.etxPassword);
        Button btnLogin = dialog.findViewById(R.id.btnLogin);
        Button btnExit = dialog.findViewById(R.id.btnExit);

        btnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this);
                alert.setTitle(R.string.alertTitle);
                alert.setMessage(R.string.exitAlert);
                alert.setIcon(R.mipmap.ic_launcher);

                alert.setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        ExitApp();
                    }
                });

                alert.setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

                alert.show();
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = etxUserName.getText().toString();
                String password = etxPassword.getText().toString();
                if (username.equals("admin") && password.equals("1234")){
                    Main();
                    dialog.dismiss();
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.exitMenu:
                ExitApp();
                break;
            case R.id.infoMenu:
                Toast.makeText(this, R.string.infoApp, Toast.LENGTH_SHORT).show();
                break;
        }
        return super.onOptionsItemSelected(item);
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

    private void ExitApp(){
        moveTaskToBack(true);
        Process.killProcess(Process.myPid());
        System.exit(1);
    }
}
