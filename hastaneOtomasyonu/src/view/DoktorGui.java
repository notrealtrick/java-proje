package view;

import com.mysql.cj.util.StringUtils;
import daoModel.doktor;
import daoModel.randevu;
import db.connect;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class DoktorGui extends JFrame {
    private JTabbedPane tabbedPane1;
    private JPanel randevularTab;
    private JTable table_randevular;
    private JPanel jpanel;
    private JTextArea textArea1;
    private JButton button_randevuSil;
    private JPanel bilgilerTab;
    private JTextField textField_isim;
    private JTextField textField_soyisim;
    private JTextField textField_tc;
    private JTextField textField_poliklinik;
    private JTextField textField_sifre;
    private JButton değişikleriKaydetButton;
    private DefaultTableModel tableModel=new DefaultTableModel();
    private int doktor_id;
    ArrayList<randevu> randevuList;
    connect conn=new connect();
    doktor Doktor=new doktor();
    public DoktorGui(int doktor_id) throws HeadlessException {
        this.doktor_id=doktor_id;
        this.Doktor=conn.getDoktorForId_dao(doktor_id);
        this.tableModel= (DefaultTableModel) table_randevular.getModel();
        updateRandevular();
        BilgilerimDoldur();
        button_randevuSil.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow=table_randevular.getSelectedRow();
                int randevu_id=randevuList.get(selectedRow).getId();
                conn.RandevuSil(randevu_id);
                updateRandevular();
            }
        });
        değişikleriKaydetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }
    public void BilgilerimDoldur(){
        textField_isim.setText(Doktor.getIsim());
        textField_soyisim.setText(Doktor.getSoyisim());
        textField_sifre.setText(Doktor.getŞifre());
        textField_tc.setText(String.valueOf(Doktor.getTc()));
        textField_poliklinik.setText(conn.PoliklinkiAdi(Doktor.getId()));
    }
    public void updateRandevular(){
        this.add(jpanel);
        this.setBounds(0,0,1200,900);
        this.setTitle("Doktor");
        tableModel.setRowCount(0);
        tableModel.setColumnCount(0);
        tableModel.addColumn("Hasta Ad-Soyad");
        tableModel.addColumn("Randevu Tarihi");
        tableModel.addColumn("Reçete Yaz");
        randevuList=conn.RandevularForDoktorId(doktor_id);
        if(randevuList.size()==0){
            JOptionPane.showConfirmDialog(null,"Randevu yok","Randevu Bilgisi", JOptionPane.INFORMATION_MESSAGE);

        }else{
            for(int i=0;i<randevuList.size();i++){
                dtoModel.randevu yeni=new dtoModel.randevu(randevuList.get(i));
                Object[] row={yeni.getHasta().getIsimSoyisim(),yeni.getTarih()};
                tableModel.addRow(row);
            }
        }
    }

    private void createUIComponents() {
    }
}
