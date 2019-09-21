package com.example.jaroga.appempresa;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.Buffer;

public class Activity_from_store extends AppCompatActivity implements View.OnClickListener {

    EditText txtId, txtNombre, txtDescripcion, txtDireccion, txtLatitud, txtLongitud;

    Button btnSave, btnCancelar;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_from_store);

        txtId =findViewById(R.id.editTextId);
        txtNombre=findViewById(R.id.editTextNombre);
        txtDescripcion=findViewById(R.id.editTextDescripcion);
        txtDireccion=findViewById(R.id.editTextDireccion);
        txtLatitud=findViewById(R.id.editTextLatitud);
        txtLongitud=findViewById(R.id.editTextLongitud);

        btnSave=findViewById(R.id.btnNumberDecimal);
        btnCancelar=findViewById(R.id.btnCancelarFrom);

        btnSave.setOnClickListener(this);
        btnCancelar.setOnClickListener(this);


    }


    @Override
    public void onClick(View view) {


        switch (view.getId()){
            case R.id.btnNumberDecimal:
                break;
            case R.id.btnCancelarFrom:
                break;
        }

    }

    class UpdateCategory extends AsyncTask<Category,Integer, Void>{

        @Override
        protected Void doInBackground(Category... categories) {

            String params ="nombre="+ categories[0].getTitle()+"&"+
                    "Latitud="+ categories[0].getLatidtud()+"&"+
                    "longitud="+ categories[0].getLongitud()+"&"+
                    "direccion="+ categories[0].getDireccion()+"&"+
                    "descripcion="+ categories[0].getDescripcion();

            try {
                URL url = new URL("http://1172.18.26.67/cursoAndroid/vista/Tienda/modificarTienda.php");

                HttpURLConnection connection = (HttpURLConnection) url.openConnection();

                connection.setRequestMethod("POST");
                connection.setDoOutput(true);
                connection.setDoInput(true);

                OutputStream outputStream = connection.getOutputStream();
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));

                writer.write(params);
                writer.flush();
                writer.close();


                connection.connect();



            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }


            return null;
        }
    }
}
