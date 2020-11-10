package formPackage;

import javax.swing.*;

public class Managerform extends JFrame{
    private javax.swing.JPanel JPanel;
    public Managerform() {
        setTitle("관리자 모드");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(JPanel);
        setSize(300,200);
        setResizable(false);
        setVisible(true);
    }
}
