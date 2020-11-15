package com.example.serverappnode;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.IOException;

import fi.iki.elonen.NanoHTTPD;

public class MainActivity extends AppCompatActivity {
    Button useless;
    private serverReceive server;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Welcome to Node App");

        useless = (Button)findViewById(R.id.useless_button);
        useless.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Told Ya!", Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        try {
            server = new serverReceive();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

