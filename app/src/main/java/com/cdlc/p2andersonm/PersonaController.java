package com.cdlc.p2andersonm;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class PersonaController extends AppCompatActivity {

    private BaseDatos db;

    public PersonaController(Context context) {
        this.db = new BaseDatos(context);
    }

    public long AgregarPersona(Persona e){
        try{

            SQLiteDatabase database = db.getWritableDatabase();
            ContentValues valores = new ContentValues();

            valores.put(DefDB.col_codigo,e.cedula);
            valores.put(DefDB.col_nombre,e.nombre);
            valores.put(DefDB.col_salario,e.salario);
            valores.put(DefDB.col_estrato,e.estrato);
            valores.put(DefDB.col_educacion, e.educacion);
            long id = database.insert(DefDB.tabla_persona,null,valores);
            return id;
        }
        catch (Exception ex){
            System.out.println("Error al insertar");
            return 0;
        }
    }


    public boolean buscarPersona(String cod){
        String[] args = new String[] {cod};
        SQLiteDatabase database = db.getReadableDatabase();
        Cursor c = database.query(DefDB.tabla_persona, null, "codigo=?", args, null, null, null);
        if (c.getCount()>0) {
            database.close();
            return true;
        }
        else{
            database.close();
            return false;}
    }

    public Cursor buscarPersonaSelect(String cod){
        try {
            String[] args = new String[] {cod};
            SQLiteDatabase database = db.getReadableDatabase();
           // Cursor c = database.query(DefDB.tabla_persona, null, "codigo=?", args, null, null, null);
                   Cursor c=   database.rawQuery("select nombre as _id, codigo,estrato, salario, nivelEducativo from persona where codigo=?",args);
            return c;
        }catch (Exception ex) {
            System.out.println("Error al consultar");
            return null;
        }

    }


    public long actualizarPersona(Persona e, String codAnt){

        try{
            SQLiteDatabase database = db.getWritableDatabase();
            ContentValues valores = new ContentValues();
            String[] args = new String[] {codAnt};
            valores.put(DefDB.col_codigo,codAnt);
            valores.put(DefDB.col_nombre,e.nombre);
            valores.put(DefDB.col_estrato,e.estrato);
            valores.put(DefDB.col_salario,e.salario);
            valores.put(DefDB.col_educacion, e.educacion);
            long id = database.update(DefDB.tabla_persona, valores,"codigo=?",args);
            database.close();
            return id;
        }
        catch (Exception ex){
            System.out.println("Error al actualizar");
            return 0;
        }

    }
    public Cursor allPersonas() {
        try {
            SQLiteDatabase database = db.getWritableDatabase();
            Cursor cur = database.rawQuery("select nombre as _id , codigo, salario, estrato, nivelEducativo from persona", null);
            return cur;
        } catch (Exception ex) {
            System.out.println("Error al consultar");
            return null;
        }
    }

    public long Eliminar(String cod){

        try {
            if(buscarPersona(cod)){
                String[] args = new String[] {cod};
                SQLiteDatabase database = db.getWritableDatabase();
                long id = database.delete(DefDB.tabla_persona,"codigo=?",args);
                database.close();
                return id;

            }else{
                AlertDialog.Builder dialog = new AlertDialog.Builder(this);
                dialog.setTitle("Importante");
                dialog.setMessage("No se encontro elemento a borrar");
                return 0;
            }

        }catch (Exception ex){
            return 0;
        }

    }

    public void EliminarTodo(){
        try {

            SQLiteDatabase database = db.getWritableDatabase();
            database.delete(DefDB.tabla_persona,null,null);

        }catch (Exception e){

        }
    }


}
