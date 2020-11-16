package com.example.smarthomefinal.admin.speakers;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.smarthomefinal.R;
import com.example.smarthomefinal.admin.AsyncMain;

public class addSpeaker extends AppCompatActivity {
    EditText speakerNameET, speakerAddET;
    String name, ip;
    Button save;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_speaker);
        speakerNameET = (EditText)findViewById(R.id.SpeakerNameEditText);
        speakerAddET = (EditText)findViewById(R.id.SpeakerAddressEditText);
        save = (Button)findViewById(R.id.saveAddSpeaker);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name = speakerNameET.getText().toString();
                ip = speakerAddET.getText().toString();
                new AsyncMain(getApplicationContext()).execute(getString(R.string.server_ip),"addSpeaker",ip);
            }
        });
    }
}