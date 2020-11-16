package com.example.smarthomefinal.admin.familiar_sufferer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.smarthomefinal.R;
import com.example.smarthomefinal.admin.AsyncMain;

public class addFS extends AppCompatActivity {
    EditText familiarName, suffererName, keySound;
    Button save;
    String famName, suffName, sound;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_f_s);
        familiarName = (EditText)findViewById(R.id.FSFamiliarNameEditText);
        suffererName = (EditText)findViewById(R.id.FSSuffererNameEditText);
        keySound = (EditText)findViewById(R.id.FSKeySoundAssociated);
        save = (Button)findViewById(R.id.saveAddFS);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                famName = familiarName.getText().toString();
                suffName = suffererName.getText().toString();
                sound = keySound.getText().toString();
                new AsyncMain(getApplicationContext()).execute(getString(R.string.server_ip), "addFS", famName, suffName, sound);
            }
        });
    }
}