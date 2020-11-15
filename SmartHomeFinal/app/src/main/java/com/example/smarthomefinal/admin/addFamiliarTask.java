package com.example.smarthomefinal.admin;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.smarthomefinal.R;
import com.example.smarthomefinal.admin.addFamiliarAsync;

public class addFamiliarTask extends AppCompatActivity {
    EditText familiarUsername, familiarPassword, familiarAdminFlag;
    Button save, back;
    String username, password, flag;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_familiar_task);
        familiarUsername = (EditText)findViewById(R.id.FamiliarNameEditText);
        familiarPassword = (EditText)findViewById(R.id.FamiliarPasswordEditText);
        familiarAdminFlag = (EditText)findViewById(R.id.FamiliarAdminFlag);
        save = (Button)findViewById(R.id.Save);
        back = (Button)findViewById(R.id.Back);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                username = familiarUsername.getText().toString();
                password = familiarPassword.getText().toString();
                flag = familiarAdminFlag.getText().toString();
                new addFamiliarAsync(getApplicationContext()).execute(username, password, flag);
            }
        });

    }
}