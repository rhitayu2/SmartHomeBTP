package com.example.smarthomefinal.admin.sufferer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.smarthomefinal.R;
import com.example.smarthomefinal.admin.AsyncMain;

public class deleteSufferer extends AppCompatActivity {
    EditText suffererName, suffererAddress;
    Button save;
    String suffName, suffAdd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_sufferer);
        suffererName = (EditText)findViewById(R.id.SuffererNameEditText);
        suffererAddress = (EditText)findViewById(R.id.SuffererAddressEditText);
        save = (Button)findViewById(R.id.deleteSufferer);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                suffName = suffererName.getText().toString();
                suffAdd = suffererAddress.getText().toString();
                new AsyncMain(getApplicationContext()).execute(getString(R.string.server_ip), "deleteSufferer", suffName, suffAdd);
            }
        });
    }
}