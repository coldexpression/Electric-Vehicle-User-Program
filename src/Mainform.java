import javax.swing.*;

public class Mainform {
    private javax.swing.JLabel JLabel;
    private javax.swing.JPanel JPanel;
    public static void main(String args[]) {
        JFrame frame = new JFrame("Mainform");
        frame.setContentPane(new Mainform().JPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
