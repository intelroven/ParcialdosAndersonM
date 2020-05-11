package com.cdlc.p2andersonm;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

public class PersonaCursorAdapter extends CursorAdapter {


    public PersonaCursorAdapter(Context context, Cursor c, int flags) {
        super(context, c, flags);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.datopersona   , parent, false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView nombres = (TextView) view.findViewById(R.id.txtNombreS);
        TextView id = (TextView) view.findViewById(R.id.txtId);
        TextView testrato = (TextView) view.findViewById(R.id.txtEstrato);
        TextView tsalario = (TextView) view.findViewById(R.id.txtSalario);
        TextView tnivelE = (TextView) view.findViewById(R.id.txtNivelE);

        // Extract properties from cursor
        String nombre = cursor.getString(cursor.getColumnIndexOrThrow("_id"));
        String cod=    cursor.getString(cursor.getColumnIndexOrThrow("codigo"));
        String estrato=  cursor.getString(cursor.getColumnIndexOrThrow(DefDB.col_estrato));
        String salario= cursor.getString(cursor.getColumnIndexOrThrow(DefDB.col_salario)) ;
        String nivelE = cursor.getString(cursor.getColumnIndexOrThrow(DefDB.col_educacion));

        // Populate fields with extracted properties
        nombres.setText(nombre);
        id.setText(cod);
        testrato.setText(estrato);
        tsalario.setText(salario);
        tnivelE.setText(nivelE);
    }
}
