package com.example.smarthomefinal;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class deleteFamiliarTask extends AppCompatActivity {
    EditText familiarDeleteUsername, familiarDeletePassword;
    Button delete;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_familiar_task);
        familiarDeletePassword = (EditText)findViewById(R.id.FamiliarDeletePassword);
        familiarDeleteUsername = (EditText)findViewById(R.id.FamiliarDeleteUsername);
        delete = (Button)findViewById(R.id.Delete);

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = familiarDeleteUsername.getText().toString();
                new deleteFamiliarAsync(getApplicationContext()).execute(username);
            }
        });
    }
}