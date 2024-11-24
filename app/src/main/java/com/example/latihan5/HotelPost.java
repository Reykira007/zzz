package com.example.latihan5;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class HotelPost {

    @SerializedName("id_hotel")
    @Expose
    private String idHotel;
    @SerializedName("nama_hotel")
    @Expose
    private String namaHotel;
    @SerializedName("range_harga")
    @Expose
    private String rangeHarga;
    @SerializedName("lokasi")
    @Expose
    private String lokasi;
    @SerializedName("deskripsi")
    @Expose
    private String deskripsi;
    @SerializedName("bintang")
    @Expose
    private String bintang;
    @SerializedName("foto_hotel")
    @Expose
    private String fotoHotel;

    public String getIdHotel() {
        return idHotel;
    }

    public void setIdHotel(String idHotel) {
        this.idHotel = idHotel;
    }

    public String getNamaHotel() {
        return namaHotel;
    }

    public void setNamaHotel(String namaHotel) {
        this.namaHotel = namaHotel;
    }

    public String getRangeHarga() {
        return rangeHarga;
    }

    public void setRangeHarga(String rangeHarga) {
        this.rangeHarga = rangeHarga;
    }

    public String getLokasi() {
        return lokasi;
    }

    public void setLokasi(String lokasi) {
        this.lokasi = lokasi;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }

    public String getBintang() {
        return bintang;
    }

    public void setBintang(String bintang) {
        this.bintang = bintang;
    }

    public String getFotoHotel() {
        return fotoHotel;
    }

    public void setFotoHotel(String fotoHotel) {
        this.fotoHotel = fotoHotel;
    }

}