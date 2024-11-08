package com.example.latihan5;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class FoodPost {

    @SerializedName("id_makanan")
    @Expose
    private String idMakanan;
    @SerializedName("nama_makanan")
    @Expose
    private String namaMakanan;
    @SerializedName("harga_makanan")
    @Expose
    private String hargaMakanan;
    @SerializedName("deskripsi_makanan")
    @Expose
    private String deskripsiMakanan;
    @SerializedName("lokasi_toko")
    @Expose
    private String lokasiToko;
    @SerializedName("foto_makanan")
    @Expose
    private String fotoMakanan;

    public String getIdMakanan() {
        return idMakanan;
    }

    public void setIdMakanan(String idMakanan) {
        this.idMakanan = idMakanan;
    }

    public String getNamaMakanan() {
        return namaMakanan;
    }

    public void setNamaMakanan(String namaMakanan) {
        this.namaMakanan = namaMakanan;
    }

    public String getHargaMakanan() {
        return hargaMakanan;
    }

    public void setHargaMakanan(String hargaMakanan) {
        this.hargaMakanan = hargaMakanan;
    }

    public String getDeskripsiMakanan() {
        return deskripsiMakanan;
    }

    public void setDeskripsiMakanan(String deskripsiMakanan) {
        this.deskripsiMakanan = deskripsiMakanan;
    }

    public String getLokasiToko() {
        return lokasiToko;
    }

    public void setLokasiToko(String lokasiToko) {
        this.lokasiToko = lokasiToko;
    }

    public String getFotoMakanan() {
        return fotoMakanan;
    }

    public void setFotoMakanan(String fotoMakanan) {
        this.fotoMakanan = fotoMakanan;
    }

}