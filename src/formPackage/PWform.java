package formPackage;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PWform extends JFrame{
    private javax.swing.JPanel JPanel;
    private JPasswordField password;
    private JButton checkbutton;
    public PWform() {
        setTitle("CheckPassword");
        setSize(300,100);
        setContentPane(JPanel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);
        checkbutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String pass = "1234";
                String input = new String(password.getPassword());
                if(pass.equals(input))
                {
                    JOptionPane.showMessageDialog(null,"로그인에 성공하였습니다.");
                    new Managerform();
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(null,"비밀번호가 틀렸습니다.");
                }
            }
        });
    }
}
