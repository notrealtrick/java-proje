package db;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;

import daoModel.*;

import javax.swing.*;

public class connect {
    private Connection con = null;
    private Statement statement = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;
    public connect() {
        String user= "root";
        String pass= "admin";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/hastane";
            String kullaniciad = "kullanıcı_adınız";
            String sifre = "şifreniz";

            con = DriverManager.getConnection(url, user, pass);
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
            System.out.println("Sürücü proje eklenmemiş [Connector eklenmemiş]");
        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("Bağlanılamadı");
        }
    }
    public insan loginControl(String tc, String sifre, String tipi) throws SQLException {
        String tablo="";
        if(tipi.equals("personel")){
            tablo="personel";
        }else if(tipi.equals("doktor")){
            tablo="doktorlar";

        }else if(tipi.equals("hasta")){
            tablo="hastalar";
        }
        String sorgu="SELECT * FROM "+tablo+" where tc=? and sifre=?";
        preparedStatement =con.prepareStatement(sorgu);
        preparedStatement.setString(1, tc);
        preparedStatement.setString(2, sifre);
        resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            insan insan = null;
            if(tipi.equals("personel")){
                insan=new personel();
                tablo="personel";
            }else if(tipi.equals("doktor")){
                insan=new doktor();
                tablo="doktorlar";

            }else if(tipi.equals("hasta")){
                insan =new hasta();
                tablo="hastalar";
            }
            insan.setId(resultSet.getInt("id"));
            insan.setTc(resultSet.getInt("tc"));
            insan.setŞifre(resultSet.getString("sifre"));
            insan.setIsim(resultSet.getString("isim"));
            insan.setSoyisim(resultSet.getString("soyisim"));
            return insan;
        } else {
            return null;
        }

    }
    public boolean insan_ekle(insan Insan) {
        String sorgu = "Insert Into "+Insan.getTipi() +" (tc,sifre,isim,soyisim) VALUES(?,?,?,?)";


        try {
            preparedStatement = (PreparedStatement) con.prepareStatement(sorgu);
            preparedStatement.setInt(1, Insan.getTc());
            preparedStatement.setString(2, Insan.getŞifre());
            preparedStatement.setString(3,Insan.getIsim());
            preparedStatement.setString(4,Insan.getSoyisim());
            int x=preparedStatement.executeUpdate();
            preparedStatement.getGeneratedKeys();
            return true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Kullanıcı Kayıtlı!", "Sonuç" , JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
        return false;
    }
    public boolean insan_ekle(insan Insan,String poliklinik) {
        String sorgu = "Insert Into "+Insan.getTipi() +" (tc,sifre,isim,soyisim) VALUES(?,?,?,?)";
        PreparedStatement preparedStatement2;

        try {
            preparedStatement2 = (PreparedStatement) con.prepareStatement(sorgu);
            preparedStatement2.setInt(1, Insan.getTc());
            preparedStatement2.setString(2, Insan.getŞifre());
            preparedStatement2.setString(3,Insan.getIsim());
            preparedStatement2.setString(4,Insan.getSoyisim());
            preparedStatement2.executeUpdate();
            insan yeni =loginControl(String.valueOf(Insan.getTc()),Insan.getŞifre(),"doktor");
            poliklinikEkle(poliklinik,yeni.getId());
            return true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Kullanıcı Kayıtlı!", "Sonuç" , JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
        return false;
    }
    public boolean poliklinikEkle(String poliklinikAdi,int doktor_id){
        String sorgu = "Insert Into poliklinikler (adi,doktor_id) VALUES(?,?)";


        try {
            preparedStatement = (PreparedStatement) con.prepareStatement(sorgu);
            preparedStatement.setString(1, poliklinikAdi);
            preparedStatement.setInt(2, doktor_id);

            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    public boolean insan_sil(int selectedRow,String tipi) {
        int id=0;
        if(tipi.equals("doktor")){
            ArrayList<doktor> doktors =doktor_liste();
            id=doktors.get(selectedRow).getId();
            tipi="doktorlar";
        }else if(tipi.equals("hasta")){
            tipi="hastalar";
            ArrayList<hasta> hastas =hasta_liste();
            id=hastas.get(selectedRow).getId();
        }else if(tipi.equals("personel")){
            ArrayList<personel> personels=personel_liste();
            id=personels.get(selectedRow).getId();
        }


        String sorgu = "DELETE FROM "+tipi +" WHERE id=?";


        try {
            preparedStatement = (PreparedStatement) con.prepareStatement(sorgu);
            preparedStatement.setInt(1, id);

            preparedStatement.executeUpdate();

            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    public ArrayList<personel> personel_liste() {
        ArrayList<personel> cikti = new ArrayList<>();
        try {
            statement = (Statement) con.createStatement();
            String sorgu = "Select * From personel";
            resultSet = statement.executeQuery(sorgu);

            while (resultSet.next()) {
                int id=resultSet.getInt("id");
                int tc = resultSet.getInt("tc");
                String isim = resultSet.getString("isim");
                String soyisim = resultSet.getString("soyisim");
                String sifre = resultSet.getString("sifre");
                cikti.add(new personel(id,tc, sifre, isim, soyisim));
            }


            return cikti;
        } catch (SQLException e) {
            e.printStackTrace();

        }
        return null;

    }
    public ArrayList<doktor> doktor_liste() {
        ArrayList<doktor> cikti = new ArrayList<>();
        try {
            statement = (Statement) con.createStatement();
            String sorgu = "Select * From doktorlar";
            resultSet = statement.executeQuery(sorgu);

            while (resultSet.next()) {
                int id=resultSet.getInt("id");
                int tc = resultSet.getInt("tc");
                String isim = resultSet.getString("isim");
                String soyisim = resultSet.getString("soyisim");
                String sifre = resultSet.getString("sifre");
                cikti.add(new doktor(id,tc, sifre, isim, soyisim));
            }


            return cikti;
        } catch (SQLException e) {
            e.printStackTrace();

        }
        return null;

    }
    public ArrayList<hasta> hasta_liste() {
        ArrayList<hasta> cikti = new ArrayList<>();
        try {
            statement = (Statement) con.createStatement();
            String sorgu = "Select * From hastalar";
            resultSet = statement.executeQuery(sorgu);

            while (resultSet.next()) {
                int id=resultSet.getInt("id");
                int tc = resultSet.getInt("tc");
                String isim = resultSet.getString("isim");
                String soyisim = resultSet.getString("soyisim");
                String sifre = resultSet.getString("sifre");
                cikti.add(new hasta(id,tc, sifre, isim, soyisim));
            }


            return cikti;
        } catch (SQLException e) {
            e.printStackTrace();

        }
        return null;

    }
    public String PoliklinkiAdi(int id){
        String sorgu="SELECT * FROM poliklinikler where doktor_id =?";
        try {
            preparedStatement =con.prepareStatement(sorgu);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {

                return resultSet.getString("adi");
            } else {
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public boolean RandevuEkle(randevu randevu){
        for(doktor i:doktor_liste()){
            if(randevu.getDoktor_id()==i.getTc()){
                randevu.setDoktor_id(i.getId());
                break;
            }
        }


        String sorgu = "Insert Into randevular (doktor_id,hasta_id,tarih) VALUES(?,?,?)";


        try {
            preparedStatement = (PreparedStatement) con.prepareStatement(sorgu);
            preparedStatement.setInt(1, randevu.getDoktor_id());
            preparedStatement.setInt(2, randevu.getHasta_id());
            preparedStatement.setString(3,randevu.getTarih());
            preparedStatement.executeUpdate();

            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    public ArrayList<randevu> hastaRandevuListele(int hasta_id){
        ArrayList<randevu> cikti = new ArrayList<>();
        try {
            String sorgu = "Select * From randevular where hasta_id=?";

            preparedStatement =  con.prepareStatement(sorgu);
            preparedStatement.setInt(1,hasta_id);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                cikti.add(new randevu(resultSet.getInt("id"),resultSet.getInt("doktor_id"),resultSet.getInt("hasta_id"),resultSet.getString("tarih")));
            }


            return cikti;
        } catch (SQLException e) {
            e.printStackTrace();

        }
        return null;
    }
    public boolean RandevuSil(int randevu_id){
        String sorgu="DELETE FROM randevular  where id="+randevu_id;
        try {
            statement =con.createStatement();
            statement.execute(sorgu);

            return true;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    public ArrayList<daoModel.randevu> RandevularForDoktorId(int doktor_id){
        ArrayList<randevu> cikti = new ArrayList<>();
        try {
            String sorgu = "Select * From randevular where doktor_id=?";

            preparedStatement =  con.prepareStatement(sorgu);
            preparedStatement.setInt(1,doktor_id);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                cikti.add(new randevu(resultSet.getInt("id"),resultSet.getInt("doktor_id"),resultSet.getInt("hasta_id"),resultSet.getString("tarih")));
            }


            return cikti;
        } catch (SQLException e) {
            e.printStackTrace();

        }
        return null;
    }
    public dtoModel.doktor getDoktorForId(int doktor_id){

        String sorgu="SELECT * FROM  doktorlar where id=? ";
        try {
            preparedStatement =con.prepareStatement(sorgu);
            preparedStatement.setInt(1, doktor_id);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                dtoModel.doktor R_doktor=new dtoModel.doktor(resultSet.getInt("tc"),resultSet.getString("isim")+" "+resultSet.getString("soyisim"));
                return R_doktor;
            } else {
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
    public daoModel.doktor getDoktorForId_dao(int doktor_id){

        String sorgu="SELECT * FROM  doktorlar where id=? ";
        try {
            preparedStatement =con.prepareStatement(sorgu);
            preparedStatement.setInt(1, doktor_id);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                daoModel.doktor R_doktor=new daoModel.doktor(resultSet.getInt("id"),resultSet.getInt("tc"),resultSet.getString("sifre"),resultSet.getString("isim"),resultSet.getString("soyisim"));
                return R_doktor;
            } else {
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
    public dtoModel.hasta getHastaForId(int hasta_id){
        String sorgu="SELECT * FROM  hastalar where id=? ";
        try {
            preparedStatement =con.prepareStatement(sorgu);
            preparedStatement.setInt(1, hasta_id);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                dtoModel.hasta R_hasta=new dtoModel.hasta(resultSet.getInt("tc"),resultSet.getString("isim")+" "+resultSet.getString("soyisim"));
                return R_hasta;
            } else {
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return  null;
    }

}
