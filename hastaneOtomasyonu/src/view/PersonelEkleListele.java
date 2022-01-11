package view;

import daoModel.insan;
import db.connect;
import dtoModel.doktor;
import dtoModel.hasta;
import dtoModel.personel;
//import sun.tools.jps.Jps;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Objects;

public class PersonelEkleListele  {
    private JTabbedPane tabbedPane_listele;
    private JPanel ekle;
    private JPanel listele;
    private JTextField textField_tc;
    private JTextField textField_sifre;
    private JTextField textField_isim;
    private JTextField textField_soyisim;
    private JButton ekleButton;
    private JTable table;
    private JPanel panel;
    private JButton button_Sil;
    DefaultTableModel tableModel ;
    String tipi;
    connect conn =new connect();
    dtoModel.doktor doktor=new doktor();
    dtoModel.personel personel=new personel();
    dtoModel.hasta hasta=new hasta();
    public PersonelEkleListele(String tipi) {

        this.tipi=tipi;
        this.tableModel = (DefaultTableModel) table.getModel();
        InsanListele();
        ekleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                insan yeni=new insan(Integer.valueOf(textField_tc.getText()),textField_sifre.getText(),textField_isim.getText(),textField_soyisim.getText());
                yeni.setTipi(tipi);
                if(tipi.equals("doktorlar")){
                    String poliklinikAdi = JOptionPane.showInputDialog("Lütfen poliklinik adını giriniz!");
                    conn.insan_ekle(yeni,poliklinikAdi);
                    InsanListele();
                    return;
                }
                conn.insan_ekle(yeni);
                InsanListele();
            }
        });
        button_Sil.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow=table.getSelectedRow();
                conn.insan_sil(selectedRow,tipi);
                InsanListele();
            }
        });
    }
    public void InsanListele(){
        tableModel.setColumnCount(0);
        tableModel.setRowCount(0);
        if(tipi.equals("doktorlar")){
            doktor.convert();

            tableModel.addColumn("TC");
            tableModel.addColumn("İsim Soyisim");
            tableModel.addColumn("Poliklinik");
            for(dtoModel.doktor i:dtoModel.doktor.doktorlar){

                Object[] yeni={i.getTc(),i.getIsimSoyisim(),i.getPoliniklik()};
                tableModel.addRow(yeni);
            }
        }else if(tipi.equals("hastalar")){
            hasta.convert();
            tableModel.addColumn("TC");
            tableModel.addColumn("İsim Soyisim");
            for(dtoModel.hasta i:dtoModel.hasta.hastalar) {

                Object[] yeni={i.getTc(),i.getIsimSoyisim()};
                tableModel.addRow(yeni);
            }

        }else if(tipi.equals("personel")){

            personel.convert();
            tableModel.addColumn("TC");
            tableModel.addColumn("İsim Soyisim");
            for(dtoModel.personel i:dtoModel.personel.personeller) {

                Object[] yeni={i.getTc(),i.getIsimSoyisim()};
                tableModel.addRow(yeni);
            }
        }
    }
    public JPanel getPanel() {
        return panel;
    }


}
