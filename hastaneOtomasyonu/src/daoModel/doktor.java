package daoModel;

public class doktor extends insan {
    public doktor() {
        setTipi("doktor");
    }

    public doktor(int id, int tc, String şifre, String isim, String soyisim) {
        super(id, tc, şifre, isim, soyisim, "doktor");
    }

    public doktor(int tc, String şifre, String isim, String soyisim) {
        super(tc, şifre, isim, soyisim);
        setTipi("doktor");
    }
}
