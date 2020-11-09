import javax.swing.*;

public class SelectView {
    private javax.swing.JPanel JPanel;
    private JButton UserButton;
    private JButton ManagerButton;
    public static void main(String args[]) {
        JFrame frame = new JFrame("SelectView");
        frame.setSize(300,200);
        frame.setContentPane(new SelectView().JPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
