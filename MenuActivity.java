package com.example.jaroga.appempresa;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

public class MenuActivity extends AppCompatActivity {

    ListView lst;
    AdapterStore adapterStore;
    ArrayList<Category>arrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);


        lst= findViewById(R.id.lstTiendas);
        //arrayList=new ArrayList<Category>();
        // arrayList.add(new tienda("tiendaUno", "descripcion1") );
        // arrayList.add(new tienda("tiendaDos", "descripcion2"));

        // adapterStore= new AdapterStore(this, arrayList);
        //lst.setAdapter(adapterStore);

        registerForContextMenu(lst);

        new ConsultarTienda().execute();


    }


    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_contex, menu);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {

        AdapterView.AdapterContextMenuInfo info= (AdapterView.AdapterContextMenuInfo)
                item.getMenuInfo();


        switch (item.getItemId()) {


            case R.id.itemContextUpdate:
               Toast.makeText(getApplicationContext(),"Actualizar"+ arrayList.get(info.position).getId(),
                       Toast.LENGTH_SHORT).show();

                Intent intent= new Intent(getApplicationContext(),Activity_from_store.class);
                intent.putExtra("idUpdate",
                        arrayList.get(info.position).getId());



                return true;
            case R.id.itemContextDelete:
                Toast.makeText(getApplicationContext(),"Eliminar!", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }

    class ConsultarTienda extends AsyncTask<Void, Integer, JSONArray> {//integer es el progreso de la actividad en segundo plano

        @Override
        protected JSONArray doInBackground(Void... voids) {

            URLConnection conection= null;
            JSONArray jsonArray =null;
            try {
                conection= new URL("http://172.18.26.67/cursoAndroid/vista/Tienda/obtenerTiendas.php").openConnection();

                InputStream inputStream=(InputStream)conection.getContent();
                //numero de cabidades que tiene
                byte[]buffer= new byte[1000];
                int size = inputStream.read(buffer);//buffer que lee la cantidad de caracteres que contiene
                jsonArray= new JSONArray(new String(buffer,0,size));



            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }


            return jsonArray;
        }

        @Override
        protected void onPostExecute(JSONArray jsonArray) {
            super.onPostExecute(jsonArray);
            Category mytienda=null;

            arrayList= new ArrayList<Category>();

            for(int i=0;i<jsonArray.length();i++){

                try {
                    JSONObject jsonObject= jsonArray.getJSONObject(i);
                    mytienda= new Category(jsonObject.getInt("idtienda"),
                            jsonObject.getString("nombre"),
                            jsonObject.getString("direccion"),
                            jsonObject.getDouble("latitud"),
                            jsonObject.getDouble("longitud"),
                            jsonObject.getString("descripcion"));

                    arrayList.add(mytienda);
                }catch (JSONException e){
                    e.printStackTrace();

                }



            }
            adapterStore= new AdapterStore (MenuActivity.this, arrayList);
            lst.setAdapter(adapterStore);

        }
    }

}