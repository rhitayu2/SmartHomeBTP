package com.example.smarthomefinal;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class familiarActivity extends AppCompatActivity {
    String user_name;
    ListView simpleList;
    String patientList[] = {"patient1", "patient2", "patient3"};
    AlertDialog.Builder builder;
    Context c = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_familiar);
        Intent intent = getIntent();
        user_name = intent.getStringExtra("user_name");
        getSupportActionBar().setTitle("Familiar : " + user_name);
        simpleList = (ListView)findViewById(R.id.simpleListView);
//        getPatientList(user_name);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, R.layout.activity_listview, R.id.textView, patientList);
        simpleList.setAdapter(arrayAdapter);

        simpleList.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    final int position, long id) {
                // TODO Auto-generated method stub
//                Toast.makeText(familiarActivity.this, patientList[position], Toast.LENGTH_SHORT).show();
                AlertDialog.Builder builder = new AlertDialog.Builder(familiarActivity.this);
                builder.setTitle("Play Sound?");
                builder.setMessage("Would you like to play sound for "+patientList[position]);
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        new playSound(getApplicationContext()).execute("http://192.168.29.240:4444/fetchPatientBT",patientList[position]);
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        });
        };
    }