package formPackage;

import javax.swing.*;

public class Mainform extends JFrame{
    private javax.swing.JLabel JLabel;
    private javax.swing.JPanel JPanel;
    private JList arealist;

    public Mainform() {
        setTitle("전기 자동차 사용자용 프로그램");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(JPanel);
        setSize(500,400);
        setResizable(false);
        setVisible(true);
    }
}
