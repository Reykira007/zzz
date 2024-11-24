package com.example.latihan5;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class InformationPost {

    @SerializedName("id_information")
    @Expose
    private String idInformation;
    @SerializedName("tgl_information")
    @Expose
    private String tglInformation;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("komentar")
    @Expose
    private String komentar;
    @SerializedName("tanggapan")
    @Expose
    private String tanggapan;

    public String getIdInformation() {
        return idInformation;
    }

    public void setIdInformation(String idInformation) {
        this.idInformation = idInformation;
    }

    public String getTglInformation() {
        return tglInformation;
    }

    public void setTglInformation(String tglInformation) {
        this.tglInformation = tglInformation;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getKomentar() {
        return komentar;
    }

    public void setKomentar(String komentar) {
        this.komentar = komentar;
    }

    public String getTanggapan() {
        return tanggapan;
    }

    public void setTanggapan(String tanggapan) {
        this.tanggapan = tanggapan;
    }

}