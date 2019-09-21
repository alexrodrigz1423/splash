package com.example.jaroga.appempresa;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.net.URLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    Button btnLogin, btnRegister,Ubicar;
    TextView lblUser,lblPassword;
    EditText txtUser, txtPassword;

    String user,pass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Ubicar=findViewById(R.id.botonUbicar);
        btnLogin=findViewById(R.id.botonLogin);
        btnRegister=findViewById(R.id.botonRegistrar);
        txtPassword=findViewById(R.id.TxtPassword);
        txtUser=findViewById(R.id.TxtEmail);


        Ubicar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MapsActivity.class);
                startActivity(intent);
            }
        });


        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),ActivityRegistrar.class);
                startActivity(intent);
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),MenuActivity.class);
                startActivity(intent);
            }
        });



    }


    public void Login(View view ){
        user= txtUser.getText().toString().trim();
        pass=txtPassword.getText().toString();
        //validar datos
        if(TextUtils.isEmpty(user)){
            txtUser.setError("Usuario requerido");
            txtUser.setFocusable(true);
            return;
        }


        if(TextUtils.isEmpty(pass)){
            txtPassword.setError("Contrasena requerida");
            txtPassword.setFocusable(true);
            return;
        }
        new LoginRest().execute(user,pass);
    }








    //clase AsyncTask
    class LoginRest extends AsyncTask<String, Integer, String>{

        //variable de peticion de conexion
        URLConnection connection=null;
        //variable para el resultado
        String result="0";



        @Override
        protected String doInBackground(String... strings) {
            try {
                connection=new URL("http://172.18.26.67/cursoAndroid/vista/Usuario/iniciarSesion.php?usuario="+strings[0]+"&password="+strings[1]).openConnection();

                InputStream inputStream= (InputStream) connection.getContent();
                byte[] buffer=new byte[10000];

                int size=inputStream.read(buffer);

                result= new String(buffer,0,size);
                Log.i("result",result);





            } catch (IOException e) {
                e.printStackTrace();
            }


            return result;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            if(s.equals("1")){
                Intent intent = new Intent(MainActivity.this,MenuActivity.class);
                startActivity(intent);
            }else{
                Toast.makeText(MainActivity.this, "Usuario o contrasena incorrectos", Toast.LENGTH_SHORT).show();
            }
        }
    }








}
