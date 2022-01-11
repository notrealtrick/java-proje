package dtoModel;

import java.util.ArrayList;

public class hasta extends insan {
    public hasta(int tc, String isimSoyisim) {
        super(tc, isimSoyisim, "hasta");
    }
    public static ArrayList<hasta> hastalar =new ArrayList<>();

    public hasta() {

    }

    @Override
    public void convert() {
        hastalar.clear();
        ArrayList<daoModel.hasta> hastaDao = new ArrayList<>();
        hastaDao=con.hasta_liste();
        for(daoModel.hasta i:hastaDao){
            hasta yeni=new hasta();
            yeni.setIsimSoyisim(i.getIsim()+" "+i.getSoyisim());
            yeni.setTc(i.getTc());

            hastalar.add(yeni);
        }

    }
}
