package com.example.hoango7604.se114_bt6_4;

import android.Manifest;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.Scanner;

public class MainActivity extends AppCompatActivity {

    Button btnRead;
    Button btnWrite;
    EditText edtRead;

    String sdcard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
        handleClick();
    }

    private void init() {
        btnRead = findViewById(R.id.btnRead);
        btnWrite = findViewById(R.id.btnWrite);
        edtRead = findViewById(R.id.edtRead);
        sdcard = Environment.getExternalStorageDirectory().getAbsolutePath() + "/myfile.txt";
    }

    private void handleClick() {
        btnRead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                readFile();
            }
        });

        btnWrite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                writeFile();
            }
        });
    }

    private void readFile(){
        try {
            String data = "";
            Scanner scanner = new Scanner(new File(sdcard));
            while (scanner.hasNext()){
                data += scanner.nextLine() + "\n";
            }
            scanner.close();
            edtRead.setText(data);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void writeFile() {
        try {
            OutputStreamWriter writer = new OutputStreamWriter(new FileOutputStream(sdcard), "UTF-16LE");
            writer.write(edtRead.getText().toString());
            writer.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
