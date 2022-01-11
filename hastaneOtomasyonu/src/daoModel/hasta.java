package daoModel;

public class hasta extends insan {
    public hasta() {
        setTipi("hasta");
    }

    public hasta(int tc, String şifre, String isim, String soyisim) {
        super(tc, şifre, isim, soyisim);
        setTipi("hasta");
    }

    public hasta(int id, int tc, String şifre, String isim, String soyisim) {
        super(id, tc, şifre, isim, soyisim,"hasta");
    }
}
