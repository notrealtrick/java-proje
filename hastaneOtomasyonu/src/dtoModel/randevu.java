package dtoModel;

import db.connect;

import java.util.ArrayList;

public class randevu {
    int id;
    int doktor_id;
    int hasta_id;
    String tarih;
    doktor doktor;
    hasta hasta;
    connect conn=new connect();
    public static ArrayList<randevu> randevular=new ArrayList<>();
    public randevu() {
    }

    public randevu(daoModel.randevu daorandevu) {
        this.id=daorandevu.getId();
        this.doktor_id = daorandevu.getDoktor_id();
        this.hasta_id = daorandevu.getHasta_id();
        this.tarih = daorandevu.getTarih();
        doktor=conn.getDoktorForId(doktor_id);
        doktor.setPoliniklik(conn.PoliklinkiAdi(doktor_id));
        hasta=conn.getHastaForId(hasta_id);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDoktor_id() {
        return doktor_id;
    }

    public void setDoktor_id(int doktor_id) {
        this.doktor_id = doktor_id;
    }

    public int getHasta_id() {
        return hasta_id;
    }

    public void setHasta_id(int hasta_id) {
        this.hasta_id = hasta_id;
    }

    public String getTarih() {
        return tarih;
    }

    public void setTarih(String tarih) {
        this.tarih = tarih;
    }

    public dtoModel.doktor getDoktor() {
        return doktor;
    }

    public void setDoktor(dtoModel.doktor doktor) {
        this.doktor = doktor;
    }

    public dtoModel.hasta getHasta() {
        return hasta;
    }

    public void setHasta(dtoModel.hasta hasta) {
        this.hasta = hasta;
    }

}
