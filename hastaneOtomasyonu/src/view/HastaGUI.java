package view;

import daoModel.randevu;
import db.connect;
import dtoModel.doktor;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class HastaGUI extends JFrame {
    private JTabbedPane tabbedPane1;
    private JPanel randevuAl;
    private JPanel randevularim;
    private JFormattedTextField formattedTextField;
    private JButton randevuOluşturButton;
    private JList list;
    private JButton button_viewDate;
    private JTextField textField_date;
    private JTable table1;
    private JButton button_RandevuSil;
    private JFrame jFrame;
    connect con=new connect();
    DefaultTableModel tableModel;
    int hasta_id;
    ArrayList<randevu> randevuList;
    public HastaGUI(int hasta_id) throws HeadlessException {
        this.hasta_id=hasta_id;
        this.jFrame=this;
        this.add(tabbedPane1);
        this.setBounds(0,0,1200,900);
        this.setTitle("Hasta");
        tableModel= (DefaultTableModel) table1.getModel();
        dtoModel.randevu randevular=new dtoModel.randevu();
        dtoModel.doktor doktorlar=new dtoModel.doktor();
        doktorlar.convert();
        String[] listData=new String[doktor.doktorlar.size()];
        int j=0;
        for(dtoModel.doktor i: doktor.doktorlar){
            listData[j]=i.getTc()+" - "+i.getIsimSoyisim()+" - "+i.getPoliniklik();
            j++;
        }
        list.setListData(listData);
        randevuListUpdate();
        button_viewDate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textField_date.setText(new DatePicker(jFrame).setPickedDate());
            }
        });
        randevuOluşturButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String value = (String) list.getSelectedValue();
                String[] split =value.split(" -");
                int doktorTC=Integer.valueOf(split[0]);
                String date=textField_date.getText();
                if(con.RandevuEkle(new randevu(doktorTC,hasta_id,date))){
                    JOptionPane.showConfirmDialog(null,"Randevu Oluşturuldu.","Randevu Bilgisi", JOptionPane.INFORMATION_MESSAGE);
                }else{
                    JOptionPane.showConfirmDialog(null,"Randevu Oluşturulamadı!","Randevu Bilgisi", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });
        button_RandevuSil.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                randevuSil();
            }
        });
        tabbedPane1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                randevuListUpdate();
            }
        });
    }
    public void randevuListUpdate(){
        tableModel.setRowCount(0);
        tableModel.setColumnCount(0);
        tableModel.addColumn("Doktor İsim-Soyisim");
        tableModel.addColumn("Poliklinik");
        tableModel.addColumn("Tarih");
        randevuList=con.hastaRandevuListele(hasta_id);
        for(int i=0;i<randevuList.size();i++){
                dtoModel.randevu yeni=new dtoModel.randevu(randevuList.get(i));
                Object[] row={yeni.getDoktor().getIsimSoyisim(),yeni.getDoktor().getPoliniklik(),yeni.getTarih()};
                tableModel.addRow(row);
        }
    }
    public void randevuSil(){
        if(randevuList.size()>0){
            int selected=table1.getSelectedRow();
            if(randevuList.get(selected).delete()){
                JOptionPane.showConfirmDialog(null,"Randevu Silindi.","Randevu", JOptionPane.INFORMATION_MESSAGE);
                randevuListUpdate();
            }else{
                JOptionPane.showConfirmDialog(null,"Randevu Silindi.","Randevu", JOptionPane.INFORMATION_MESSAGE);

            }
        }
    }
    class DatePicker {
        int month = java.util.Calendar.getInstance().get(java.util.Calendar.MONTH);
        int year = java.util.Calendar.getInstance().get(java.util.Calendar.YEAR);;
        JLabel l = new JLabel("", JLabel.CENTER);
        String day = "";
        JDialog d;
        JButton[] button = new JButton[49];

        public DatePicker(JFrame parent) {
            d = new JDialog();
            d.setModal(true);
            String[] header = { "Pazar", "Pazartesi", "Salı", "Çarşamba", "Perşembe", "Cuma", "Cumartesi" };
            JPanel p1 = new JPanel(new GridLayout(7, 7));
            p1.setPreferredSize(new Dimension(430, 120));

            for (int x = 0; x < button.length; x++) {
                final int selection = x;
                button[x] = new JButton();
                button[x].setFocusPainted(false);
                button[x].setBackground(Color.white);
                if (x > 6)
                    button[x].addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent ae) {
                            day = button[selection].getActionCommand();
                            d.dispose();
                        }
                    });
                if (x < 7) {
                    button[x].setText(header[x]);
                    button[x].setForeground(Color.red);
                }
                p1.add(button[x]);
            }
            JPanel p2 = new JPanel(new GridLayout(1, 3));
            JButton previous = new JButton("<< Geri");
            previous.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent ae) {
                    month--;
                    displayDate();
                }
            });
            p2.add(previous);
            p2.add(l);
            JButton next = new JButton("İleri >>");
            next.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent ae) {
                    month++;
                    displayDate();
                }
            });
            p2.add(next);
            d.add(p1, BorderLayout.CENTER);
            d.add(p2, BorderLayout.SOUTH);
            d.pack();
            d.setLocationRelativeTo(parent);
            displayDate();
            d.setVisible(true);
        }

        public void displayDate() {
            for (int x = 7; x < button.length; x++)
                button[x].setText("");
            java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat(
                    "MMMM yyyy");
            java.util.Calendar cal = java.util.Calendar.getInstance();
            cal.set(year, month, 1);
            int dayOfWeek = cal.get(java.util.Calendar.DAY_OF_WEEK);
            int daysInMonth = cal.getActualMaximum(java.util.Calendar.DAY_OF_MONTH);
            for (int x = 6 + dayOfWeek, day = 1; day <= daysInMonth; x++, day++)
                button[x].setText("" + day);
            l.setText(sdf.format(cal.getTime()));
            d.setTitle("Takvim");
        }

        public String setPickedDate() {
            if (day.equals(""))
                return day;
            java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat(
                    "dd/MM/yyyy");
            java.util.Calendar cal = java.util.Calendar.getInstance();
            cal.set(year, month, Integer.parseInt(day));
            return sdf.format(cal.getTime());
        }
    }
}

