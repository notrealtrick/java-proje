package view;

import com.mysql.cj.util.StringUtils;
import daoModel.insan;
import db.connect;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.Objects;

public class LoginJpanel {


    private JTextField TC_textField;
    private JPasswordField passwordField;
    private JButton GirisYapButton;
    private JPanel LoginJpanell;
    private String tipi;
    connect conn=new connect();
    public LoginJpanel(String tip) {
       this.tipi=tip;
        GirisYapButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(TC_textField.getText().isEmpty()){
                    JOptionPane.showMessageDialog(null, "TC alanı boş bırakılamaz!!", "Sonuç" , JOptionPane.ERROR_MESSAGE);
                }else if(passwordField.getPassword().length==0){
                    JOptionPane.showMessageDialog(null, "Şifre alanı boş bırakılamaz!!", "Sonuç" , JOptionPane.ERROR_MESSAGE);
                }else{
                    try {
                       insan insan= conn.loginControl(TC_textField.getText(),String.valueOf(passwordField.getPassword()),tipi);
                        if(Objects.nonNull(insan)){
                           if(insan.getTipi().equals("personel")){
                            PersonelGUI personelGUI=new PersonelGUI();
                            personelGUI.setVisible(true);
                           }else if(insan.getTipi().equals("doktor")){
                               DoktorGui doktorGui=new DoktorGui(insan.getId());
                               doktorGui.setVisible(true);

                           }else if(insan.getTipi().equals("hasta")){
                               HastaGUI hastaGUI=new HastaGUI(insan.getId());
                               hastaGUI.setVisible(true);

                           }
                        }
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                }
                System.out.println();
            }
        });
    }

    public JPanel getLoginJpanell() {
        return LoginJpanell;
    }
}
