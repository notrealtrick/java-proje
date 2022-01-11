package daoModel;

import db.connect;

public class randevu {
    int id;
    int doktor_id;
    int hasta_id;
    String tarih;
    connect conn=new connect();

    public randevu(int doktor_id, int hasta_id, String tarih) {
        this.doktor_id = doktor_id;
        this.hasta_id = hasta_id;
        this.tarih = tarih;
    }

    public randevu(int id, int doktor_id, int hasta_id, String tarih) {
        this.id=id;
        this.doktor_id = doktor_id;
        this.hasta_id = hasta_id;
        this.tarih = tarih;
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
    public boolean delete(){
        return conn.RandevuSil(this.id);
    }
}
