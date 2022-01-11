package view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import daoModel.personel;
import db.connect;
public class PersonelGUI extends JFrame {
    private JTabbedPane tabbedPane1;
    private JPanel DoktorTab;
    private JTabbedPane tabbedPane2;
    private JTextField tcKimlikNoTextField;
    private JTextField isimTextField;
    private JTextField soyisimTextField;
    private JTextField şifreTextField;
    private JButton ekleButton_doktor;
    private JTable table_personel;
    PersonelEkleListele hastaEkleListele=new PersonelEkleListele("hastalar");
    PersonelEkleListele doktorlEkleListele=new PersonelEkleListele("doktorlar");
    PersonelEkleListele personelEkleListele=new PersonelEkleListele("personel");


    public PersonelGUI() {
        tabbedPane1.addTab("Hasta",hastaEkleListele.getPanel());
        tabbedPane1.addTab("Doktor",doktorlEkleListele.getPanel());
        tabbedPane1.addTab("Personel",personelEkleListele.getPanel());
        this.add(tabbedPane1);
        this.setBounds(0,0,1200,900);
    }

}
