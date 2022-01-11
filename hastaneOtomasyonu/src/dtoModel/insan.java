package dtoModel;

import db.connect;

public abstract class insan {
    private int tc;
    private String isimSoyisim;
    private String tipi;
    public final connect con=new connect();
    public insan() {
    }

    public insan(int tc, String isimSoyisim, String tipi) {
        this.tc = tc;
        this.isimSoyisim = isimSoyisim;
        this.tipi = tipi;
    }

    public String getIsimSoyisim() {
        return isimSoyisim;
    }

    public void setIsimSoyisim(String isimSoyisim) {
        this.isimSoyisim = isimSoyisim;
    }

    public int getTc() {
        return tc;
    }

    public void setTc(int tc) {
        this.tc = tc;
    }


    public String getTipi() {
        return tipi;
    }

    public void setTipi(String tipi) {
        this.tipi = tipi;
    }

    public connect getCon() {
        return con;
    }



    public  abstract void convert();
}
