package com.example.p2arqui1;


import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface Servicio {

    String ruta_api="http://192.168.0.15:5555/prueba/";


    @POST(ruta_api)
    @FormUrlEncoded
    Call<RequestBody> uploadFile(
            @Field("Dato") String Dato,
            @Field("Correlativo") int Correlativo
    );

}
}
