package com.example.i5.myapplication;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Agregar_Tarea extends AppCompatActivity implements View.OnClickListener {
    EditText et_tarea;
    Button btnAgregar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar__tarea);
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

        et_tarea = (EditText) findViewById(R.id.et_tarea);
        btnAgregar = (Button) findViewById(R.id.btnAgregar);
        btnAgregar.setOnClickListener(this);
    }

    public void GuardarDatos(){
        String NombreTarea = et_tarea.getText().toString();
        DBhelper dbhelper = new DBhelper(this, "DBAPP", null, 1);
        SQLiteDatabase db = dbhelper.getWritableDatabase();
        if(db != null){
            ContentValues NuevoRegistro = new ContentValues();
            NuevoRegistro.put("Nombre", NombreTarea);
            long i = db.insert("Tareas", null, NuevoRegistro);
            if(i>0){
                Toast.makeText(this, "Registro Insertado", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnAgregar:
                GuardarDatos();
                break;
        }
    }
}
