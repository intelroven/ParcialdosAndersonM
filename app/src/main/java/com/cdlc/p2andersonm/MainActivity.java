package com.cdlc.p2andersonm;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    EditText cod, nom, salario;
    Spinner snivelescolar,  sestrato;
    Button guardar, listado;
    Persona persona;
    PersonaController c;
    ListView listadoL;
    Listar l;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listadoL = findViewById(R.id.listado);

        cod = findViewById(R.id.txtcedula);
        nom = findViewById(R.id.txtnombre);
        salario = findViewById(R.id.txtsalario);

        snivelescolar = findViewById(R.id.Spn_nivelE);
        snivelescolar.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                Object item = parent.getItemAtPosition(pos);
            }
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        sestrato = findViewById(R.id.Spn_estrato);
        sestrato.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                Object item = parent.getItemAtPosition(pos);
            }
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        guardar = findViewById(R.id.Btnregistrar);
        guardar.setOnClickListener(this);

        listado = findViewById(R.id.btnListar);
        listado.setOnClickListener(this);


        c = new PersonaController(getApplicationContext());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){

            case R.id.Btnregistrar:
                if(nom.getText().toString().equals("")|| cod.getText().toString().equals("")|| salario.getText().toString().equals("")){
                    Toast.makeText(this,"Llene los campos indicados",Toast.LENGTH_LONG).show();
                }else{

                    int vestrato = Integer.parseInt(sestrato.getSelectedItem().toString()) ;
                    String vnivelEdu = snivelescolar.getSelectedItem().toString();


                    persona = new Persona(nom.getText().toString(),vnivelEdu,cod.getText().toString(),vestrato,Double.parseDouble(salario.getText().toString()));
                    Toast.makeText(getApplicationContext(), persona.cedula, Toast.LENGTH_SHORT).show();

                    if(!c.buscarPersona(persona.cedula)) {
                        Log.d("Buscar", "No encontrado");
                        long id = c.AgregarPersona(persona);
                        if(id!=0){
                            Toast.makeText(getApplicationContext(), "Registro Exitoso", Toast.LENGTH_SHORT).show();
                        }

                    }
                    else{
                        Log.d("Buscar", "encontrado");
                        Toast.makeText(getApplicationContext(),"Esta persona ya ha sido registrada",Toast.LENGTH_SHORT).show();
                    }
                }
                break;

            case R.id.btnListar:
                Toast.makeText(getApplicationContext(),"Lista de Personas",Toast.LENGTH_SHORT).show();
                Intent i = new Intent(getApplicationContext(),Listar.class);
                startActivity(i);
                break;
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_principal, menu);
        return true;
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.mn_actualizar:
                AlertDialog.Builder dialog3 = new AlertDialog.Builder(this);
                dialog3.setTitle("Ingrese el codigo del paciente");
                //dialog3.setMessage("No se encontro elemento a borrar");
                dialog3.setCancelable(true);

                final EditText input2 = new EditText(this);
                input2.setHeight(130);
                input2.setWidth(340);
                input2.setGravity(Gravity.LEFT);
                input2.setImeOptions(EditorInfo.IME_ACTION_DONE);


                dialog3.setPositiveButton("Confirmar", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialogo1, int id) {
                        String cod=  input2.getText().toString();
                        if(c.buscarPersona(cod)){
                            Toast.makeText(MainActivity.this,"Actualizar datos",Toast.LENGTH_LONG).show();
                            Intent i = new Intent(getApplicationContext(),Update.class);
                            i.putExtra("codigo",cod);
                            startActivity(i);
                        }else{
                            AlertDialog.Builder dialog4 = new AlertDialog.Builder(MainActivity.this);
                            dialog4.setTitle("No existe el codigo del paciente");
                            dialog4.setMessage("No se encontro elemento a actualizar");
                            dialog4.setCancelable(true);
                            dialog4.show();

                        }

                    }
                });
                dialog3.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogo1, int id) {
                        Intent i = new Intent(getApplicationContext(),Listar.class);
                        startActivity(i);
                    }
                });

                dialog3.setView(input2);
                dialog3.show();
                break;

            case R.id.mn_eliminar:
                AlertDialog.Builder dialog2 = new AlertDialog.Builder(this);
                dialog2.setTitle("Ingrese el codigo del paciente");
                //dialog3.setMessage("No se encontro elemento a borrar");
                dialog2.setCancelable(true);

                final EditText input = new EditText(this);
                input.setHeight(130);
                input.setWidth(340);
                input.setGravity(Gravity.LEFT);
                input.setImeOptions(EditorInfo.IME_ACTION_DONE);


                dialog2.setPositiveButton("Confirmar", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialogo1, int id) {
                        String cod=  input.getText().toString();
                        c.Eliminar(cod);
                    }
                });
                dialog2.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogo1, int id) {
                        Intent i = new Intent(getApplicationContext(),Listar.class);
                        startActivity(i);
                    }
                });

                dialog2.setView(input);
                dialog2.show();

                break;

            case R.id.mn_buscar:
                AlertDialog.Builder dialogbuscar = new AlertDialog.Builder(this);
                dialogbuscar.setTitle("Ingrese la cedula de la persona");
                //dialog3.setMessage("No se encontro elemento a borrar");
                dialogbuscar.setCancelable(true);

                final EditText inputBuscar = new EditText(this);
                inputBuscar.setHeight(130);
                inputBuscar.setWidth(340);
                inputBuscar.setGravity(Gravity.LEFT);
                inputBuscar.setImeOptions(EditorInfo.IME_ACTION_DONE);


                dialogbuscar.setPositiveButton("Confirmar", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialogo1, int id) {
                        String cod=  inputBuscar.getText().toString();
                        Intent i = new Intent(getApplicationContext(),Listar.class);
                        i.putExtra("codigo",cod);
                        i.putExtra("listar",1);
                        startActivity(i);

                    }
                });
                dialogbuscar.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogo1, int id) {
                        Intent i = new Intent(getApplicationContext(), Listar.class);
                        startActivity(i);
                    }
                });

                dialogbuscar.setView(inputBuscar);
                dialogbuscar.show();

                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
