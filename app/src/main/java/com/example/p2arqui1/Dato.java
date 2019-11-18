package com.example.p2arqui1;

import com.google.gson.annotations.SerializedName;

public class Dato {

    @SerializedName("Dato")
    String dato;

    @SerializedName("Correlativo")
    int correlativo;

    public Dato(String d, int c){
        dato=d;
        correlativo=c;
    }
}
