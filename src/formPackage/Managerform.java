package formPackage;

import Module.FOPmodule;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.PrintWriter;
import java.io.IOException;
import java.util.*;
public class Managerform extends JFrame{
    private javax.swing.JPanel JPanel;
    private JList manager_list;
    private JButton bt_add;
    private JButton bt_dele;
    private List<List> pri_data;
    private List<List> env_data;
    public Managerform(List pri_data,List env_data) {
        this.pri_data = pri_data;
        this.env_data = env_data;
    }
    public Managerform() {
        Vector data_vec = new Vector();
        setTitle("관리자 모드");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(JPanel);
        setSize(800,600);
        setResizable(false);
        setVisible(true);
        Mainform mf = new Mainform();
        mf.dispose();
        pri_data = mf.return_pri(pri_data);
        env_data = mf.return_env(env_data);
        for(int i=0;i<pri_data.size();i++) {
            data_vec.addElement(pri_data.get(i).get(0));
        }
        for(int i=0;i<env_data.size();i++) {
            data_vec.addElement(env_data.get(i).get(0));
        }
        manager_list.setListData(data_vec);
        bt_add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FOPmodule fop = new FOPmodule();
                String input = JOptionPane.showInputDialog(null,"충전소 관리 기관을 입력해 주세요.\n " +
                        "[환경부,민간기업] 중 하나를 선택해 주세요.\n [예시: 환경부 -> 환경부 , 민간기업 -> 민간] "
                        , "전기자동차 충전소 관리자모드");
                String addr_name = JOptionPane.showInputDialog(null,"충전소 이름을 입력해 주세요.\n" +
                        "[예시: 이마트 원주점, 평창군청]","전기자동차 충전소 관리자 모드");
                String addr = JOptionPane.showInputDialog(null,"충전소 주소를 입력해 주세요.\n" +
                        "도로명 주소로 기입해 주시길 바랍니다.\n" +
                        "[예시: 강원도 원주시 북원로 1928 본관 2층 F구역]","전기자동차 충전소 관리자모드");
                String time = JOptionPane.showInputDialog(null,"운영 시간을 입력해 주세요.\n" +
                        "[예시: 10:30 ~ 20:30 , 24시간이면 24시간 으로 표기]","전기자동차 충전소 관리자모드");
                String type = JOptionPane.showInputDialog(null,"충전기 타입을 입력해 주세요.\n" +
                        "[예시: 완속의 경우 -> 완속 입력, 여러개의 타입일 경우 -> DC차데모+AC3상+DC콤보]","전기자동차 충전소 관리자모드");
                if(input.equals("환경부")) {
                    fop.File_add(input,"./src/Env_charge.txt",addr_name,addr,time,type);
                } else if(input.equals("민간")) {
                    fop.File_add(input,"./src/pri_charge.txt",addr_name,addr,time,type);
                }
            }
        });
        bt_dele.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FOPmodule fop = new FOPmodule();
                String input_check = JOptionPane.showInputDialog(null,"충전소 관리 기관을 입력해 주세요.\n " +
                                "[환경부,민간기업] 중 하나를 선택해 주세요.\n [예시: 환경부 -> 환경부 , 민간기업 -> 민간] ");
                String input_addr_name = JOptionPane.showInputDialog(null,"충전소 이름을 정확하게 입력해 주세요\n" +
                        "[예시: 강릉모터스는 강릉모터스1,강릉모터스2 가 존재합니다. 삭제하고자 하는 충전소 이름을 정확하게 입력해주세요]");
                if(input_check.equals("환경부")) {
                    fop.File_remove("./src/Env_charge.txt",input_addr_name);
                } else if(input_check.equals("민간")) {
                    fop.File_remove("./src/pri_charge.txt",input_addr_name);
                }
            }
        });
    }
}
