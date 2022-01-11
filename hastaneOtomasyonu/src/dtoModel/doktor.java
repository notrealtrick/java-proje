package dtoModel;

import java.util.ArrayList;

public class doktor extends insan {

    public static ArrayList<doktor> doktorlar=new ArrayList<>();
    private String poliniklik;
    public doktor() {
    }

    public doktor(int tc, String isimSoyisim) {
        super(tc, isimSoyisim,"doktor");
    }

    public String getPoliniklik() {
        return poliniklik;
    }

    public void setPoliniklik(String poliniklik) {
        this.poliniklik = poliniklik;
    }

    @Override
    public void convert() {
        doktorlar.clear();
        ArrayList<daoModel.doktor> doktorDao = new ArrayList<>();
        doktorDao=con.doktor_liste();
        for(daoModel.doktor i:doktorDao){
            doktor yeniDoktor=new doktor();
            yeniDoktor.setIsimSoyisim(i.getIsim()+" "+i.getSoyisim());
            yeniDoktor.setTc(i.getTc());
            yeniDoktor.setPoliniklik(con.PoliklinkiAdi(i.getId()));
            doktorlar.add(yeniDoktor);
        }
    }
}
