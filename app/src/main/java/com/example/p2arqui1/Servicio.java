package com.example.p2arqui1;


import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface Servicio {

    String ruta_api="http://192.168.0.18:3000/datos/";


    @POST(ruta_api)
    //@FormUrlEncoded
    Call<Dato> uploadFile(
            @Body Dato dato
    );

    @GET(ruta_api+"min/")
    Call<String>  minimo(

    );

    @GET(ruta_api+"max/")
    Call<String>  maximo(

    );

    @POST(ruta_api+"estado/")
    Call<String>  Setestado(

    );

    @GET(ruta_api+"estado/")
    Call<String>  Verestado(

    );



}

