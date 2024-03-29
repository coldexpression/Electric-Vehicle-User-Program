package formPackage;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Graphics;
import java.awt.Image;
public class SelectView extends JFrame{
    private javax.swing.JPanel JPanel;
    private JButton UserButton;
    private JButton ManagerButton;

    public SelectView() {
        setTitle("모드선택");
        setContentPane(JPanel);
        ManagerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new PWform();
                dispose();
            }
        });
        setSize(400,600);
        setResizable(false);
        setVisible(true);
        UserButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Mainform();
                dispose();
            }
        });
    }

    public static void main(String[] args) {
        new SelectView();
    }
}
