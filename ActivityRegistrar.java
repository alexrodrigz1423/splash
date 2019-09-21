package com.example.jaroga.appempresa;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import modelos.Usuario;

public class ActivityRegistrar extends AppCompatActivity {
    EditText txtUsuario,txtDireccion,txtContrasena,txtCorreo,txtNombre;
    Button btnAdd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar);

        txtUsuario = findViewById(R.id.txtUsuario);
        txtContrasena=findViewById(R.id.txtPassword);
        txtDireccion=findViewById(R.id.txtDireccion);
        txtCorreo=findViewById(R.id.txtCorreo);
        txtNombre=findViewById(R.id.txtNombre);


        btnAdd=findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //mandar llamar a la validacion de datos
                if(validarDatos(txtUsuario.getText().toString().trim(),
                        txtNombre.getText().toString().trim(),
                        txtCorreo.getText().toString().trim(),
                        txtDireccion.getText().toString().trim(),
                        txtContrasena.getText().toString()))
                {
                    //crear el objeto de tipo Usuario

                    Usuario usuario = new Usuario();
                    usuario.setNickname(txtUsuario.getText().toString().trim());
                    usuario.setNickname(txtNombre.getText().toString().trim());
                    usuario.setNickname(txtCorreo.getText().toString().trim());
                    usuario.setNickname(txtDireccion.getText().toString().trim());
                    usuario.setNickname(txtContrasena.getText().toString().trim());

                    //crear pla instancia para subClase asyncTask para realizar la coneccion

                    new AddUser().execute(usuario);
                }





                }



        });
    }

    public boolean validarDatos(String nickname, String direccion, String nombre, String correo, String password) {


        //validar que los datos no esten vacios

        if (nickname.isEmpty()) {

            txtUsuario.setError("campo vacio");
            txtUsuario.setFocusable(true);
            return false;
        }
        if (nombre.isEmpty()) {

            txtNombre.setError("campo vacio");
            txtNombre.setFocusable(true);
            return false;
        }
        if (direccion.isEmpty()) {

            txtDireccion.setError("campo vacio");
            txtDireccion.setFocusable(true);
            return false;
        }
        if (correo.isEmpty()) {

            txtCorreo.setError("campo vacio");
            txtCorreo.setFocusable(true);
            return false;
        }
        if (password.isEmpty()) {

            txtContrasena.setError("campo vacio");
            txtContrasena.setFocusable(true);
            return false;
        }

        return true;
    }
   // permite generar una tarea aque corra en un ilo diferente al principal
    class AddUser extends AsyncTask<Usuario,Integer,Boolean> {

       @Override
       protected Boolean doInBackground(Usuario... usuarios) {

           //preparar los datos de insersion
           String params = "";
           params ="user="+ usuarios[0].getNickname()+"&"+
                   "nombre="+ usuarios[0].getNombre()+"&"+
                   "correo="+ usuarios[0].getCorreo()+"&"+
                   "direccion="+ usuarios[0].getDireccion()+"&"+
                   "password="+ usuarios[0].getPassword();

           //declarar la conexion
           try {
               URL url= new URL("http://172.18.26.67/cursoAndroid/vista/Usuario/crearUsuario.php");
               HttpURLConnection connection= (HttpURLConnection) url.openConnection();

               connection.setRequestMethod("POST");
               //indicar a la url que este preparada para recibir un dato de entrada y uno de salida
               connection.setDoInput(true);
               connection.setDoOutput(true);

               OutputStream outputStream= connection.getOutputStream();
               BufferedWriter writer= new BufferedWriter
                       (new OutputStreamWriter(outputStream,"UTF-8"));

               writer.write(params);
               writer.flush();
               writer.close();
               outputStream.close();
               //hace la coneccion
               connection.connect();

               int responseCode = connection.getResponseCode();
               if (responseCode== HttpURLConnection.HTTP_OK){

                   Log.i("AddUser","usuario agregado con exito");
                   return true;


               }else {

                   return false;
               }



           } catch (MalformedURLException e) {
               e.printStackTrace();
           } catch (IOException e) {
               e.printStackTrace();
           }


           return false;
       }

       @Override
       protected void onPostExecute(Boolean aBoolean) {
           super.onPostExecute(aBoolean);
           if(aBoolean){

               Toast.makeText(ActivityRegistrar. this,
                       "usuario agregado con exito",
                       Toast.LENGTH_SHORT).show();
               finish();
           }else{
               Toast.makeText(ActivityRegistrar.this,
                       "usuario no agregado con exito, intente de nuevo",
                       Toast.LENGTH_SHORT).show();
           }
       }
   }



}
