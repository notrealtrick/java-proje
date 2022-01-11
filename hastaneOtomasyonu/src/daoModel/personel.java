package daoModel;

public class personel extends insan {
    public personel() {
        setTipi("personel");
    }

    public personel(int id, int tc, String şifre, String isim, String soyisim) {
        super(id, tc, şifre, isim, soyisim, "personel");
    }

    public personel(int tc, String şifre, String isim, String soyisim) {
        super(tc, şifre, isim, soyisim);
        setTipi("personel");
    }
}
