package com.cdlc.p2andersonm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.ListView;

public class Listar extends AppCompatActivity {
    PersonaController c;
    ListView listado;

    public Listar() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar);

        listado = findViewById(R.id.listado);
        c = new PersonaController(getApplicationContext());

        Cursor cur = c.allPersonas();

        PersonaCursorAdapter eca = new PersonaCursorAdapter(this,cur,0);
        listado.setAdapter(eca);
        eca.notifyDataSetChanged();

        Intent intent = getIntent();
        int intExtra= intent.getIntExtra("listar",0);
        if(intExtra==1){
            ListarUnico();
        }

    }

    public void ListarUnico(){
        Intent intent = getIntent();
        String codigoExtra= intent.getStringExtra("codigo");
        Cursor cur= c.buscarPersonaSelect(codigoExtra);
        PersonaCursorAdapter eca = new PersonaCursorAdapter(this,cur,0);
        listado.setAdapter(eca);
        eca.notifyDataSetChanged();
    }
}
