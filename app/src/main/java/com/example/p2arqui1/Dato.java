package com.example.p2arqui1;

import com.google.gson.annotations.SerializedName;

public class Dato {

    @SerializedName("Dato")
    String dato;

    @SerializedName("Correlativo")
    int correlativo;

    @SerializedName("Leido")
    int leido;

    public Dato(String d, int c,int l){
        dato=d;
        correlativo=c;
        leido=l;
    }
}
