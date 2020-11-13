package com.example.smarthomefinal;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity{
    Button login;
    EditText username, password;
    ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Welcome User");

        login = (Button) findViewById(R.id.button);
        username = (EditText) findViewById(R.id.usernameTextBox);
        password = (EditText)findViewById(R.id.passwordTextBox);
        progressBar = (ProgressBar)findViewById(R.id.progressBar);
        progressBar.setVisibility(View.GONE);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    String usernameEditText = username.getText().toString();
                    String passwordEditText = password.getText().toString();
                    String postLoginUrl = "http://192.168.29.148:4444/login";

                    new SendDetails(getApplicationContext()).execute(postLoginUrl, usernameEditText, passwordEditText);

            }
        });
    }

}

