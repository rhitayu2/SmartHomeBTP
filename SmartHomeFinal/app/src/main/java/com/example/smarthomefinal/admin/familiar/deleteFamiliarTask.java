package com.example.smarthomefinal.admin.familiar;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.smarthomefinal.R;
import com.example.smarthomefinal.admin.AsyncMain;

public class deleteFamiliarTask extends AppCompatActivity {
    EditText sadpass, saduser;
    Button delete;
    String user, pass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_familiar_task);
        sadpass = (EditText)findViewById(R.id.sadpass);
        saduser = (EditText)findViewById(R.id.saduser);
        delete = (Button)findViewById(R.id.Delete);
        user = saduser.getText().toString();
        pass = sadpass.getText().toString();
        Log.e("DELETE_FAM user:", user);
        Log.e("DELETE_FAM pass:", pass);
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AsyncMain(getApplicationContext()).execute(getString(R.string.server_ip),"deleteFamiliar", user, pass);
            }
        });
    }
}