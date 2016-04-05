package com.example.i5.myapplication;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class Tareas extends AppCompatActivity {
    String[] Arreglo;
    ListView lvTareas;
    protected Object noActionMode;
    public int selectedItem=-1;
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
        lvTareas = (ListView) findViewById(R.id.listaTareas);
        lvTareas.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        lvTareas.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener(){
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id){
                if(noActionMode != null){
                    return false;
                }
                selectedItem = position;
                //menuTarea
                noActionMode = Tareas.this.startActionMode(menuTarea);
                view.setSelected(true);
                return true;
            }
        });
    }

    public void CargarDatos(){
        //cargamos datos en la db
        DBhelper dbhelper = new DBhelper(this, "DBAPP", null, 1);
        SQLiteDatabase db = dbhelper.getReadableDatabase();
        if(db != null){
            Cursor c = db.rawQuery("SELECT * FROM Tareas", null);
            int Cantidad = c.getCount();
            int i=0;
            Arreglo = new String[Cantidad];
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
        }
    }

    private ActionMode.Callback menuTarea = new ActionMode.Callback(){

        @Override
        public boolean onCreateActionMode(ActionMode mode, Menu menu) {
            MenuInflater inflater = mode.getMenuInflater();
            inflater.inflate(R.menu.menu_tareas, menu);
            return true;
        }

        @Override
        public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
            return false;
        }

        @Override
        public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
            switch(item.getItemId()){
                case R.id.menu_borrar:
                    Borrar();
                    mode.finish();
                    return true;
                default:
                    return false;
            }
        }

        @Override
        public void onDestroyActionMode(ActionMode mode) {
            noActionMode = null;
            selectedItem = -1;
        }
    };

    private void Borrar(){
        DBhelper dbhelper = new DBhelper(this, "DBAPP", null, 1);
        SQLiteDatabase db = dbhelper.getReadableDatabase();
        int id= Integer.parseInt(Arreglo[selectedItem].split(" ")[0]);
        if(db != null){
            long res = db.delete("Tareas", "Id="+id, null);
            if(res > 0){
                Toast.makeText(getApplicationContext(), "Tarea Eliminada "+id, Toast.LENGTH_SHORT).show();
                CargarDatos();
            }
        }
    }
}
