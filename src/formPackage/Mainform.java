package formPackage;

import javax.swing.*;
import Module.*;

import java.awt.*;
import java.util.*;
import java.util.List;

public class Mainform extends JFrame{
    private javax.swing.JPanel JPanel;
    private JList arealist;
    private JScrollPane sp;
    private JCheckBox seoul;
    private JCheckBox kyunggi;
    private JCheckBox junrado;
    private JCheckBox chungcheongdo;
    private JLabel lb1;

    public void Buttonsize() {
        seoul.setBounds(200,200,200,200);
        kyunggi.setBounds(200,210,200,200);
        junrado.setBounds(200,220,200,200);
        chungcheongdo.setBounds(200,230,200,200);
        System.out.println("호출");
    }

    public Mainform() {
        Vector charge_vec = new Vector();
        ChargeInfo ci = new ChargeInfo();
        List <List> pri_data = new ArrayList();
        List <List> env_data = new ArrayList();
        setTitle("전기 자동차 사용자용 프로그램");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        sp.setViewportView(arealist);
        setContentPane(JPanel);
        setSize(1300,600);
        Buttonsize();
        pri_data = ci.pri_charge();
        env_data = ci.env_charge();
        for(int i=0;i<pri_data.size();i++) {
            charge_vec.addElement(pri_data.get(i).get(0));
        }
        for(int i=0;i<env_data.size();i++) {
            charge_vec.addElement(env_data.get(i).get(0));
        }
        arealist.setListData(charge_vec);
        setResizable(false);
        setVisible(true);
    }
}
