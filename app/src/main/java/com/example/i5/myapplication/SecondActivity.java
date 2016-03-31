package com.example.i5.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnTareas;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        TextView argTexto;
        argTexto = (TextView) findViewById(R.id.titleSecond);

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();

        if(extras != null){
            String argTitle = extras.getString("USUARIO");
            argTexto.setText(argTitle);
        }
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        btnTareas = (Button)findViewById(R.id.btnTareas);
        btnTareas.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnTareas:
                Intent intentTareas = new Intent(SecondActivity.this, Tareas.class);
                startActivity(intentTareas);
        }
    }
}
