package view;

import javax.swing.*;
import java.awt.*;

public class Login extends JFrame //giriş arayüzü sınıfı
{
    public Login(String title) throws HeadlessException {
        super(title);
    }

    public static void main(String[] a){
        JTabbedPane tabbedPane = new JTabbedPane();
        LoginJpanel loginJpanel =new LoginJpanel("hasta");
        LoginJpanel loginJpanel2 =new LoginJpanel("doktor");
        LoginJpanel loginJpanel3 =new LoginJpanel("personel");
        tabbedPane.addTab("Hasta",loginJpanel.getLoginJpanell());//hasta,doktor,personel giriş ekranını addTab ile oluşturduk
        tabbedPane.addTab("Doktor",loginJpanel2.getLoginJpanell());
        tabbedPane.addTab("Personel",loginJpanel3.getLoginJpanell());
        Login frame=new Login("Giriş Yap");
        frame.add(tabbedPane);
        frame.setVisible(true);
        frame.setBounds(10,10,600,600); //pencere boyutu
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false); //pencere boyutunun değişmesini engeller

    }
}
