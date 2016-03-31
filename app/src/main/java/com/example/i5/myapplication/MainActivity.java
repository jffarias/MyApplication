package com.example.i5.myapplication;

import android.content.Intent;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity  implements View.OnClickListener {
//implements View.OnClickListener
    EditText etUsuario;
    EditText etContrasena;
    Button btnAceptar;
    TextView tvAviso;

    //sensor
    LinearLayout ln;
    SensorManager sm;
    Sensor sensor;
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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

        //sensor
        /*ln = (LinearLayout) findViewById(R.id.Linear);
        sm = (SensorManager)getSystemService(SENSOR_SERVICE);
        sensor = sm.getDefaultSensor(Sensor.TYPE_PROXIMITY);*/
        //misElementos
        etUsuario = (EditText) findViewById(R.id.etUsuario);
        etContrasena = (EditText) findViewById(R.id.etContrasena);
        btnAceptar = (Button) findViewById(R.id.btnAceptar);
        tvAviso = (TextView) findViewById(R.id.tvAviso);

        btnAceptar.setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.btnAceptar:
                //Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                //startActivity(intent);
                String dUsuario = etUsuario.getText().toString();
                String dContrasena = etContrasena.getText().toString();
                if(dUsuario.equals("admin") && dContrasena.equals("admin")){
                    //tvAviso.setText("Usuario"+dUsuario+"Contraseña"+ dContrasena);
                    Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                    intent.putExtra("USUARIO", dUsuario);
                    startActivity(intent);
                }

                break;
        }
    }

   /* @Override
    protected void onResume() {
        super.onResume();
        sm.registerListener(this, sensor, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
        super.onPause();
        sm.unregisterListener(this);
    }
    @Override
    public void onSensorChanged(SensorEvent event) {
        String texto = String.valueOf(event.values[0]);
        tvAviso.setText(texto);
        float valor = Float.parseFloat(texto);
        if(valor==0){
            ln.setBackgroundColor(Color.BLUE);
        }else{
            ln.setBackgroundColor(Color.TRANSPARENT);
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }*/
}
