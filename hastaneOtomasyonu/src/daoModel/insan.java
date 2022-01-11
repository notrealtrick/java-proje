package daoModel;

public class insan {
    private int id;
    private int tc;
    private String şifre;
    private String isim;
    private String soyisim;
    private String tipi;

    public insan() {
    }

    public insan(int id, int tc, String şifre, String isim, String soyisim, String tipi) {
        this.id = id;
        this.tc = tc;
        this.şifre = şifre;
        this.isim = isim;
        this.soyisim = soyisim;
        this.tipi = tipi;
    }

    public insan(int tc, String şifre, String isim, String soyisim) {
        this.tc = tc;
        this.şifre = şifre;
        this.isim = isim;
        this.soyisim = soyisim;
    }

    public int getTc() {
        return tc;
    }

    public void setTc(int tc) {
        this.tc = tc;
    }

    public String getŞifre() {
        return şifre;
    }

    public void setŞifre(String şifre) {
        this.şifre = şifre;
    }

    public String getIsim() {
        return isim;
    }

    public void setIsim(String isim) {
        this.isim = isim;
    }

    public String getSoyisim() {
        return soyisim;
    }

    public void setSoyisim(String soyisim) {
        this.soyisim = soyisim;
    }

    public String getTipi() {
        return tipi;
    }

    public void setTipi(String tipi) {
        this.tipi = tipi;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
