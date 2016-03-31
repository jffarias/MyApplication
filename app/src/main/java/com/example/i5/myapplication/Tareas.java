package com.example.i5.myapplication;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class Tareas extends AppCompatActivity {
    ListView lvTareas;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tareas);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                //Intent intent = new Intent(Tareas.this, Agregar_Tarea.class);
                //startActivity(intent);
                startActivity(new Intent(Tareas.this, Agregar_Tarea.class));
            }
        });
        CargarDatos();
        //CargarDatos();
    }

    public void CargarDatos(){
        //cargamos datos en la db
        DBhelper dbhelper = new DBhelper(this, "DBAPP", null, 1);
        SQLiteDatabase db = dbhelper.getReadableDatabase();
        if(db != null){
            Cursor c = db.rawQuery("SELECT * FROM Tareas", null);
            int Cantidad = c.getCount();
            int i=0;
            String[] Arreglo = new String[Cantidad];
            if(c.moveToFirst()){
                do{
                    String Linea = c.getInt(0) + " " + c.getString(1);
                    Arreglo[i] = Linea;
                    i++;
                    //prueba
                }while(c.moveToNext());
            }

            ArrayAdapter<String>Adapter = new ArrayAdapter<String>(this,
                    android.R.layout.simple_list_item_1, Arreglo);
            ListView lvTareas = (ListView) findViewById(R.id.listaTareas);
            lvTareas.setAdapter(Adapter);

            lvTareas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Toast.makeText(getApplicationContext(), String.valueOf(position),
                            Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}
