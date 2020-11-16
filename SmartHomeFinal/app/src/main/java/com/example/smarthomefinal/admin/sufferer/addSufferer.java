package com.example.smarthomefinal.admin.sufferer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.smarthomefinal.R;
import com.example.smarthomefinal.admin.AsyncMain;

public class addSufferer extends AppCompatActivity {
    EditText suffererName, suffererAddress, suffererSound;
    Button save;
    String suffName, suffAdd, suffSound;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_sufferrer);
        suffererName = (EditText)findViewById(R.id.SuffererNameEditText);
        suffererAddress = (EditText)findViewById(R.id.SuffererAddressEditText);
        suffererSound = (EditText)findViewById(R.id.SuffererSoundTag);
        save = (Button)findViewById(R.id.saveAddSpeaker);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                suffName = suffererName.getText().toString();
                suffAdd = suffererAddress.getText().toString();
                suffSound = suffererSound.getText().toString();
                new AsyncMain(getApplicationContext()).execute(getString(R.string.server_ip), "addSufferer", suffName, suffAdd, suffSound);
            }
        });
    }
}