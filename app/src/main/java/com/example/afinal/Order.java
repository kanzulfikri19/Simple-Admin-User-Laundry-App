package com.example.afinal;

import android.widget.EditText;
import java.io.Serializable;

import com.google.firebase.database.Exclude;
import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class Order implements Serializable{
    // getter method for our id
    public String getId() {
        return id;
    }

    // setter method for our id
    public void setId(String id) {
        this.id = id;
    }

    // we are using exclude because
    // we are not saving our id
    @Exclude
    private String id;
    public String nama, alamat, harga, paket, status, berat;
    public Order(){

    }


    public Order(String nama, String alamat, String harga, String paket, String status, String berat){
        this.nama = nama;
        this.alamat = alamat;
        this.harga = harga;
        this.paket = paket;
        this.status = status;
        this.berat = berat;


    }
    public String getNama() {
        return nama;
    }
    public String getAlamat() {
        return alamat;
    }
    public String getHarga() {
        return harga;
    }
    public String getPaket() {
        return paket;
    }
    public String getStatus() {
        return status;
    }
    public String getBerat() {
        return berat;
    }

}