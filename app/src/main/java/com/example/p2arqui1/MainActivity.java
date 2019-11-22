package com.example.p2arqui1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.Retrofit.Builder;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    int correlativo=0;
    EditText txtentrada;
    boolean estado=false;
    Button btnEnviar;
    Button btnCeder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtentrada=(EditText)findViewById(R.id.txtTexto);

        btnEnviar = findViewById(R.id.btnEnviar);
        btnEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                VerEstado();
                if(estado) {
                    String entrada = txtentrada.getText().toString();
                    correlativo++;
                    Envio(entrada);
                }else{
                    Toast.makeText(getApplicationContext(), "No Tiene Permisos en Este momento", Toast.LENGTH_SHORT).show();

                }
            }
        });

        btnCeder = findViewById(R.id.btnCeder);
        btnCeder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CambioEstado();
                if(estado){
                    estado=false;
                }else{
                    Toast.makeText(getApplicationContext(), "No Tiene Permisos en Este momento", Toast.LENGTH_SHORT).show();

                }

            }
        });


        VerEstado();
        Maximo();


    }


    private void VerEstado(){
        Retrofit retrofit = new Builder()
                .baseUrl(Servicio.ruta_api)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Servicio postApi= retrofit.create(Servicio.class);
        Call<String> call = postApi.Verestado();

        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                String prueba=response.body();

                Log.d("good", "goog");
                Log.d("goog",prueba);

                if(prueba.equals("true")){
                    estado=true;

                }else{
                    estado=false;

                }


            }
            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Log.d("fail", "fail");
                Log.d("fail",t.getMessage());
            }
        });

    }

    private void Envio(String cadena){
        Retrofit retrofit = new Builder()
                .baseUrl(Servicio.ruta_api)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Dato dato=new Dato(cadena,correlativo,0);

        Servicio postApi= retrofit.create(Servicio.class);
        Call<Dato> call = postApi.uploadFile(dato);


        call.enqueue(new Callback<Dato>() {
            @Override
            public void onResponse(Call<Dato> call, Response<Dato> response) {
                response.body();
                Log.d("good", "good");
                Log.d("goog",response.raw().toString());

            }
            @Override
            public void onFailure(Call<Dato> call, Throwable t) {
                Log.d("fail", "fail");
                Log.d("fail",t.getMessage());
            }
        });

    }

    private void CambioEstado(){
        Retrofit retrofit = new Builder()
                .baseUrl(Servicio.ruta_api)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Servicio postApi= retrofit.create(Servicio.class);
        Call<String> call = postApi.Setestado();

        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                String prueba=response.body();

                Log.d("good", "goog");
                Log.d("goog","muestreo");

            }
            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Log.d("fail", "fail");
                Log.d("fail",t.getMessage());
            }
        });
    }

    private void Maximo(){
        Retrofit retrofit = new Builder()
                .baseUrl(Servicio.ruta_api)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Servicio postApi= retrofit.create(Servicio.class);
        Call<String> call = postApi.maximo();

        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if(response!=null){
                    correlativo=Integer.parseInt(response.body());
                }else{
                    correlativo=0;
                }


                Log.d("good", "goog");
                Log.d("goog","muestreo");

            }
            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Log.d("fail", "fail");
                Log.d("fail",t.getMessage());
            }
        });
    }

}


