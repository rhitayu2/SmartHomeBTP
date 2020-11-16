package com.example.smarthomefinal.admin.familiar_sufferer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.smarthomefinal.R;
import com.example.smarthomefinal.admin.AsyncMain;

public class deleteFS extends AppCompatActivity {
    EditText familiarName, suffererName;
    Button delete;
    String famName, suffName, sound;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_f_s);
        familiarName = (EditText)findViewById(R.id.FSFamiliarNameDeleteText);
        suffererName = (EditText)findViewById(R.id.FSSuffererNameDeleteText);
        delete = (Button)findViewById(R.id.deleteFSButton);
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                famName = familiarName.getText().toString();
                suffName = suffererName.getText().toString();

                new AsyncMain(getApplicationContext()).execute(getString(R.string.server_ip), "deleteFS", famName, suffName);
            }
        });
    }
}