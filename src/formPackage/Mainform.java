package formPackage;

import javax.swing.*;
import Module.*;

import java.util.*;

public class Mainform extends JFrame{
    private javax.swing.JLabel JLabel;
    private javax.swing.JPanel JPanel;
    private JList arealist;

    public Mainform() {
        Vector vec = new Vector();
        ChargeInfo ci = new ChargeInfo();
        List <List> pri_data = new ArrayList();
        setTitle("전기 자동차 사용자용 프로그램");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(JPanel);
        setSize(500,400);
        pri_data = ci.pri_charge();
        for(int i=0;i<pri_data.size();i++) {
            vec.addElement(pri_data.get(i).get(0));
        }
        arealist.setListData(vec);
        setResizable(false);
        setVisible(true);
    }
    public void addlist() {
        Vector vec;
        List <List> pri_data = new ArrayList();
        ChargeInfo ci = new ChargeInfo();

    }
}
