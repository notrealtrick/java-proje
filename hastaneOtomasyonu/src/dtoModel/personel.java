package dtoModel;

import daoModel.doktor;

import java.util.ArrayList;

public class personel extends insan {
    public static ArrayList<personel> personeller =new ArrayList<>();
    public personel() {
    }

    public personel(int tc, String isimSoyisim, String tipi) {
        super(tc, isimSoyisim, tipi);
    }

    @Override
    public void convert() {
        personeller.clear();
        ArrayList<daoModel.personel> personelDao = new ArrayList<>();
        personelDao=con.personel_liste();
        for(daoModel.personel i:personelDao){
            personel yeni=new personel();
            yeni.setIsimSoyisim(i.getIsim()+" "+i.getSoyisim());
            yeni.setTc(i.getTc());

            personeller.add(yeni);
        }

    }
}
