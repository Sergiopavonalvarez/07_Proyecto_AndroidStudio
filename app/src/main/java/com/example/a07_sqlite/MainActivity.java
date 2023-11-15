package com.example.a07_sqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    AdminSQlite a;
    EditText edad;
    EditText nombre;
    EditText estudios;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        a = new AdminSQlite(this, "Politicos", null, 1);
        nombre = findViewById(R.id.nombre);
        edad = findViewById(R.id.escaños);
        estudios = findViewById(R.id.presidente);
    }


    public void agregar(View vista) {
        SQLiteDatabase db = a.getWritableDatabase();
        ContentValues registro = new ContentValues();
        registro.put("NOMBRE", nombre.getText().toString());
        registro.put("EDAD", edad.getText().toString());
        registro.put("ESTUDIOS", estudios.getText().toString());
        db.insert("Politicos", null, registro);
        //db.close();
    }

    public void borrar(View vista) {
        EditText nombre = findViewById(R.id.nombre);

        SQLiteDatabase db = a.getWritableDatabase();
        int filasborradas = db.delete("POLITICOS", "NOMBRE = '" + nombre.getText().toString() + "'", null);
        if (filasborradas == 1) {
            Toast.makeText(this, "se ha borrado lider politico" + nombre.getText().toString(), Toast.LENGTH_LONG);

        } else {
            Toast.makeText(this, "no se ha borrado lider politico" + nombre.getText().toString(), Toast.LENGTH_LONG);

        }
        //db.close();


    }

    public void consultar(View vista) {


        SQLiteDatabase bd = a.getWritableDatabase();
        String[] aux = {nombre.getText().toString()};
        Cursor fila = bd.query("Politicos", null, "NOMBRE = ?", aux, null, null, null);
        if (fila.moveToFirst()) {
            nombre.setText(fila.getString(0));
            edad.setText(fila.getString(1));
            estudios.setText(fila.getString(2));
        } else
            Toast.makeText(this, "no se ha encontrado el lider politico " + nombre.getText().toString(), Toast.LENGTH_SHORT);
        //bd.close();
    }

    public void modificar(View vista) {
        SQLiteDatabase bd = a.getWritableDatabase();
        ContentValues s = new ContentValues();
        s.put("edad", edad.getText().toString());
        s.put("estudios", estudios.getText().toString());
        int filasupdateadas = bd.update("politicos", s, "nombre='" + nombre.getText().toString() + "'", null);
        if (filasupdateadas == 1) {
            Toast.makeText(this, "se ha modificado lider politico" + nombre.getText().toString(), Toast.LENGTH_LONG);

        } else {
            Toast.makeText(this, "no se ha modificado lider politico" + nombre.getText().toString(), Toast.LENGTH_LONG);

        }
        //bd.close();

    }


   /* public void cambiar(View vista){
        Button cambiar=findViewById(R.id.Cambiar);
        Intent intent=new Intent(this, MainActivity2.class);
        startActivity(intent);
    }*/
}

