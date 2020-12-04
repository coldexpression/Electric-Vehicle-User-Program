package formPackage;

import Module.FOPmodule;
import Module.ChargeInfo;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
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
    private JButton bt_mainform;
    private JComboBox cb_state;
    private List<List> pri_data;
    private List<List> env_data;
    Vector data_vec = new Vector();
    boolean check = false;
    boolean remove_check=false;
    public Managerform(List pri_data,List env_data) {
        this.pri_data = pri_data;
        this.env_data = env_data;
    }
    public Vector return_env_for_manager () {
        return data_vec;
    }
    public List<List> return_pri() {
        return this.pri_data;
    }
    public List<List> return_env() {
        return this.env_data;
    }
    public boolean return_manager_edit_check () {
        return check;
    }
    public Managerform(int a) {

    }
    public Managerform() {
        File history = new File("./src/check.txt");
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
        cb_state.addItem("사용가능");
        cb_state.addItem("점검중");
        cb_state.addItem("사용중");
        addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {

            }

            @Override
            public void windowClosing(WindowEvent e) {
                String CallManagerHistory = String.valueOf(check);
                FileWriter writer = null;
                try {
                    writer = new FileWriter(history,false);
                        writer.write(CallManagerHistory);
                        writer.flush();
                } catch(IOException ie) {
                    ie.printStackTrace();
                }
            }

            @Override
            public void windowClosed(WindowEvent e) {
                String CallManagerHistory = String.valueOf(check);
                FileWriter writer = null;
                try {
                    writer = new FileWriter(history,false);
                    writer.write(CallManagerHistory);
                    writer.flush();
                } catch(IOException ie) {
                    ie.printStackTrace();
                }
            }

            @Override
            public void windowIconified(WindowEvent e) {

            }

            @Override
            public void windowDeiconified(WindowEvent e) {

            }

            @Override
            public void windowActivated(WindowEvent e) {

            }

            @Override
            public void windowDeactivated(WindowEvent e) {

            }
        });
        bt_add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                File state = new File("./src/state.txt");
                FOPmodule fop = new FOPmodule();
                List tmp = new ArrayList();
                double radomValue = Math.random();
                int random_num = (int)(radomValue*50) + 30;
                boolean add_env_pri_check = false;
                String input = JOptionPane.showInputDialog(null,"충전소 관리 기관을 입력해 주세요.\n " +
                        "[환경부,민간기업] 중 하나를 선택해 주세요.\n [예시: 환경부 -> 환경부 , 민간기업 -> 민간] "
                        , "전기자동차 충전소 관리자모드");
                String addr_name = JOptionPane.showInputDialog(null,"충전소 이름을 입력해 주세요.\n" +
                        "[예시: 이마트 원주점, 평창군청]","전기자동차 충전소 관리자 모드");
                String addr = JOptionPane.showInputDialog(null,"충전소 주소를 입력해 주세요.\n" +
                        "도로명 주소로 기입해 주시길 바랍니다.\n" +
                        "[예시: 강원도 원주시 북원로 1928 본관 2층 F구역]","전기자동차 충전소 관리자모드");
                String time = JOptionPane.showInputDialog(null,"운영 시간을 입력해 주세요.\n" +
                        "[예시: 10:30~20:30 , 24시간이면 24시간 으로 표기]","전기자동차 충전소 관리자모드");
                String type = JOptionPane.showInputDialog(null,"충전기 타입을 입력해 주세요.\n" +
                        "[예시: 완속의 경우 -> 완속 입력, 여러개의 타입일 경우 -> DC차데모+AC3상+DC콤보]","전기자동차 충전소 관리자모드");
                if(input.equals("환경부")) {
                    fop.File_add(input,"./src/Env_charge.txt",addr_name,addr,time,type);
                    check = true;
                } else if(input.equals("민간")) {
                    fop.File_add(input,"./src/pri_charge.txt",addr_name,addr,time,type);
                    check = true;
                } else {
                    JOptionPane.showMessageDialog(null,"잘못된 값을 입력했습니다.","WrongData",JOptionPane.ERROR_MESSAGE);
                }
                add_env_pri_check = fop.return_add_env_pri_check();
                tmp.add(addr_name+" "+addr+" ");
                tmp.add(type);
                tmp.add(time);
                tmp.add("사용가능");
                if(type.equals("완속")) {
                    tmp.add(7);
                } else {
                    tmp.add(random_num);
                }
                if(add_env_pri_check) {
                    env_data.add(tmp);
                } else {
                    pri_data.add(tmp);
                }
                fop.File_state_chagne(state,pri_data,env_data);
            }
        });
        bt_dele.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FOPmodule fop = new FOPmodule();
                File state = new File("./src/state.txt");
                boolean remove_env_pri_check = false;
                int delete_index=0;
                String input_check = JOptionPane.showInputDialog(null,"충전소 관리 기관을 입력해 주세요.\n " +
                                "[환경부,민간기업] 중 하나를 선택해 주세요.\n [예시: 환경부 -> 환경부 , 민간기업 -> 민간] ");
                String input_addr_name = JOptionPane.showInputDialog(null,"충전소 이름을 정확하게 입력해 주세요\n" +
                        "[예시: 강릉모터스는 강릉모터스1,강릉모터스2 가 존재합니다. 삭제하고자 하는 충전소 이름을 정확하게 입력해주세요]");
                if(input_check.equals("환경부")) {
                    fop.File_remove("./src/Env_charge.txt",input_addr_name,pri_data.size(), env_data.size());
                    //env_pri_check = fop.return_remove_env_pri_check();
                    try {
                        fop.eraseLast("./src/Env_charge.txt");
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }
                    check = true;
                    remove_check = true;
                } else if(input_check.equals("민간")) {
                    fop.File_remove("./src/pri_charge.txt",input_addr_name,pri_data.size(), env_data.size());
                    //env_pri_check = fop.return_remove_env_pri_check();
                    try {
                        fop.eraseLast("./src/pri_charge.txt");
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }
                    check = true;
                    remove_check = true;
                } else {
                    JOptionPane.showMessageDialog(null,"잘못된 값을 입력했습니다.","WrongData",JOptionPane.ERROR_MESSAGE);
                }
                delete_index = fop.return_deleteline();
                remove_env_pri_check = fop.return_remove_env_pri_check();
                if(remove_env_pri_check) {
                   // System.out.println("삭제될 >> "+env_data.get(delete_index));
                    env_data.remove(delete_index);
                    //fop.File_state_chagne(state,pri_data,env_data);
                } else {
                    pri_data.remove(delete_index);
                    //fop.File_state_chagne(state,pri_data,env_data);
                }
                fop.File_state_chagne(state,pri_data,env_data);
            }
        });
        bt_mainform.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Mainform();
                dispose();
            }
        });
        cb_state.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FOPmodule fop = new FOPmodule();
                File state = new File("./src/state.txt");
                String cb_type = cb_state.getSelectedItem().toString();
                String select_addr = manager_list.getSelectedValue().toString();
                String agency_index = ChargeInfo.find_charge_index(pri_data,env_data,select_addr);
                String[] split_agency_index = agency_index.split(" ");
                if(split_agency_index[0].equals("env")) {
                    check = true;
                    env_data.get(Integer.parseInt(split_agency_index[1])).set(3,cb_type);
                  //  System.out.println(env_data.get(Integer.parseInt(split_agency_index[1])));
                    JOptionPane.showMessageDialog(null,"변경완료");
                    cb_state.setSelectedIndex(0);
                } else if(split_agency_index[0].equals("pri")) {
                    check = true;
                    pri_data.get(Integer.parseInt(split_agency_index[1])).set(3,cb_type);
                  //  System.out.println(pri_data.get(Integer.parseInt(split_agency_index[1])));
                    JOptionPane.showMessageDialog(null,"변경완료");
                    cb_state.setSelectedIndex(0);
                }
                fop.File_state_chagne(state,pri_data,env_data);
            }
        });
    }
}
