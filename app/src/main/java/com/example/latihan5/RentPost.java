package com.example.latihan5;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RentPost {

    @SerializedName("plat_nomor")
    @Expose
    private String platNomor;
    @SerializedName("nama_kendaraan")
    @Expose
    private String namaKendaraan;
    @SerializedName("jenis_kendaraan")
    @Expose
    private String jenisKendaraan;
    @SerializedName("harga_kendaraan")
    @Expose
    private String hargaKendaraan;
    @SerializedName("deskripsi_kendaraan")
    @Expose
    private String deskripsiKendaraan;
    @SerializedName("tahun")
    @Expose
    private String tahun;
    @SerializedName("foto_kendaraan")
    @Expose
    private String fotoKendaraan;

    public String getPlatNomor() {
        return platNomor;
    }

    public void setPlatNomor(String platNomor) {
        this.platNomor = platNomor;
    }

    public String getNamaKendaraan() {
        return namaKendaraan;
    }

    public void setNamaKendaraan(String namaKendaraan) {
        this.namaKendaraan = namaKendaraan;
    }

    public String getJenisKendaraan() {
        return jenisKendaraan;
    }

    public void setJenisKendaraan(String jenisKendaraan) {
        this.jenisKendaraan = jenisKendaraan;
    }

    public String getHargaKendaraan() {
        return hargaKendaraan;
    }

    public void setHargaKendaraan(String hargaKendaraan) {
        this.hargaKendaraan = hargaKendaraan;
    }

    public String getDeskripsiKendaraan() {
        return deskripsiKendaraan;
    }

    public void setDeskripsiKendaraan(String deskripsiKendaraan) {
        this.deskripsiKendaraan = deskripsiKendaraan;
    }

    public String getTahun() {
        return tahun;
    }

    public void setTahun(String tahun) {
        this.tahun = tahun;
    }

    public String getFotoKendaraan() {
        return fotoKendaraan;
    }

    public void setFotoKendaraan(String fotoKendaraan) {
        this.fotoKendaraan = fotoKendaraan;
    }

}