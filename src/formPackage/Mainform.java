package formPackage;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import Module.*;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import java.util.List;
import javax.swing.Timer;

public class Mainform extends JFrame{
    private Timer timer;
    private javax.swing.JPanel JPanel;
    private JList arealist;
    private JScrollPane sp;
    private JCheckBox seoul;
    private JCheckBox kyunggi;
    private JCheckBox junrado;
    private JCheckBox chungcheongdo;
    private JLabel lb_locate;
    private JCheckBox gyeongsangdo;
    private JCheckBox jejudo;
    private JLabel lb_battery;
    private JLabel lb_time;
    private JLabel lb_use;
    private JButton bt_start;
    private JLabel lb_size;
    private JProgressBar loading_bar;
    private JLabel lb_endtime;
    private JLabel lb_uselocate;
    private JLabel lb_usetype;
    private JButton bt_end;
    private JLabel lb_explain;
    private JCheckBox Gwangwondo;
    boolean re_empty_check = true;
    int thread_check=0;
    int start_seoul_size=0;
    int end_seoul_size=0;
    int start_kyunggi_size=0;
    int end_kyunggi_size=0;
    int start_junrado_size=0;
    int end_junrado_size=0;
    int start_chungcheongdo_size=0;
    int end_chungcheongdo_size=0;
    int start_gyeongsangdo_size=0;
    int end_gyeongsangdo_size=0;
    int start_jejudo_size=0;
    int end_jejudo_size=0;
    int start_gwangwondo_size=0;
    int end_gwangwondo_size=0;
    int thread_time = 0;
    int percent_car_charge = 0;
    int car_battery_size = 0;
    int pri_list_index;
    int env_list_index;
    boolean check_pri_env;
    List<List> timer_pri_data;
    List<List> timer_env_data;
   /* ImageIcon charge_yes = new ImageIcon(getClass().getResource("images/accept.jpg"));
    ImageIcon charge_no = new ImageIcon(getClass().getResource("images/charging_loading.gif"));
    ImageIcon charge_fix = new ImageIcon(getClass().getResource("images/fix.jpg")); */
    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            int val = percent_car_charge;
            int car_battery;
            double cost;
            String[] kw = lb_size.getText().toString().split(" ");
            double move_time = thread_time;
            double fix_time= (double)thread_time/(100-(double)val);
            System.out.println("tt : "+thread_time + " fix_time : "+fix_time);
            thread_check = 0;
            while(percent_car_charge < 100 && thread_check==0) {
                percent_car_charge++;
                System.out.println(percent_car_charge);
                loading_bar.setValue(percent_car_charge);
                loading_bar.setString(percent_car_charge+"%");
                move_time -= fix_time;
                lb_endtime.setText("총 예상 소요시간 : " + Math.round(move_time) + " 분");
                try {
                    Thread.sleep(300);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            bt_start.setEnabled(true);
            bt_end.setEnabled(false);
            car_battery = CalcChargingTime.trans(car_battery_size,percent_car_charge);
            cost = CalcChargingTime.cost_calc(Integer.parseInt(kw[0]),car_battery);
            if(thread_check == 0) {
                JOptionPane.showMessageDialog(null,"배터리 충전이 완료되었습니다.\n 사용료: "+cost+" 원","충전 완료",JOptionPane.PLAIN_MESSAGE);
                lb_endtime.setText("현재 "+percent_car_charge+"% 충전 되어있습니다.");
            } else {
                JOptionPane.showMessageDialog(null,"배터리 충전이 중단되었습니다.\n 사용료: "+cost+" 원","충전 실패",JOptionPane.ERROR_MESSAGE);
            }
            if(check_pri_env == true) {
                timer_pri_data.get(pri_list_index).set(3,"사용가능");
            } else {
                timer_env_data.get(env_list_index).set(3,"사용가능");
            }
        }
    };
    class MenuActionListener implements ActionListener{
        public void actionPerformed(ActionEvent e) {
            String select = e.getActionCommand();
            String regExp = "^[0-9]+$";
            String change;
            if(select.equals("차량 배터리 변경")) {
                change = JOptionPane.showInputDialog("차량의 배터리를 입력해 주세요. [kw 단위] \n 예시: 50kw 차량이면 50 을 입력");
                if(change.matches(regExp) && Integer.parseInt(change) > 0 && Integer.parseInt(change) <= 300) {
                    car_battery_size = Integer.parseInt(change);
                    bt_start.setEnabled(true);
                }
                else {
                    JOptionPane.showMessageDialog(null,"잘못된 값을 입력했습니다.\n" +
                            "[전기 자동차 배터리의 평균 용량 범위: 30 ~ 300]","Error",JOptionPane.ERROR_MESSAGE);
                }
            } else if(select.equals("관리자 모드")) {
                new PWform();
                dispose();
            }
        }
    }

    public List<List> return_env(List<List> env) {
        env = timer_env_data;
        return env;
    }
    public List<List> return_pri(List<List> pri) {
        pri = timer_pri_data;
        return pri;
    }

    public Mainform() {
        File history = new File("./src/check.txt");
        File state = new File("./src/state.txt");
        JMenuBar mb = new JMenuBar();
        JMenu jm = new JMenu("메뉴");
        JMenuItem item1 = new JMenuItem("차량 배터리 변경");
        JMenuItem item2 = new JMenuItem("관리자 모드");
        jm.add(item1);
        jm.add(item2);
        mb.add(jm);
        setJMenuBar(mb);
       // Vector tmp_charge_vec = new Vector();
        Vector charge_vec = new Vector();
        Vector recylce_vec = new Vector();
        ChargeInfo ci = new ChargeInfo();
        List <List> pri_data = new ArrayList();
        List <List> env_data = new ArrayList();
        List<String> st_charge_data = new ArrayList<>();
        String history_line = "";
        String state_line = "";
        setTitle("전기 자동차 사용자용 프로그램");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        sp.setViewportView(arealist);
        setContentPane(JPanel);
        setSize(1100,500);
        //call_manager = manager.return_manager_edit_check();
        pri_data = ci.pri_charge();
        env_data = ci.env_charge();
        try{
            double radomValue;
            int random_num;
           // File history = new File("./src/check.txt");
            String[] index_state;
            FileReader filereader = new FileReader(history);
            BufferedReader bufReader = new BufferedReader(filereader);
            while((history_line = bufReader.readLine()) != null) {
                break;
            }
                filereader = new FileReader(state);
                bufReader = new BufferedReader(filereader);
                while ((state_line = bufReader.readLine()) != null) {
                    radomValue = Math.random();
                    random_num = (int)(radomValue*50) + 30;
                    index_state = state_line.split(" ");
                    if (index_state[2].equals("민간")) {
                        pri_data.get(Integer.parseInt(index_state[0])).set(3, index_state[1]);
                        //System.out.println(pri_data.get(Integer.parseInt(index_state[0])).set(3, index_state[1]));
                        if(pri_data.get(Integer.parseInt(index_state[0])).get(1).equals("완속")) {
                            pri_data.get(Integer.parseInt(index_state[0])).set(4,7);
                        } else {
                            pri_data.get(Integer.parseInt(index_state[0])).set(4,random_num);
                        }
                    } else if (index_state[2].equals("환경")) {
                        env_data.get(Integer.parseInt(index_state[0])).set(3, index_state[1]);
                        //System.out.println(env_data.get(Integer.parseInt(index_state[0])).set(3, index_state[1]));
                        if(env_data.get(Integer.parseInt(index_state[0])).get(1).equals("완속")) {
                            env_data.get(Integer.parseInt(index_state[0])).set(4,7);
                        } else {
                            env_data.get(Integer.parseInt(index_state[0])).set(4,random_num);
                        }
                    }
                }
        } catch (FileNotFoundException e) {
        } catch (IOException e) {
            e.printStackTrace();
        }
       // System.out.println(pri_data);
      //  System.out.println(env_data);
       /* if(history_line.equals("true")) {
            pri_data = ci.pri_charge();
            env_data = ci.env_charge();
            for (int i = 0; i < pri_data.size(); i++) {
                tmp_charge_vec.addElement(pri_data.get(i).get(0));
            }
            for (int i = 0; i < env_data.size(); i++) {
                tmp_charge_vec.addElement(env_data.get(i).get(0));
            }
        } else if(history_line.equals("false")){
            Managerform mg = new Managerform(1);
            pri_data = mg.return_pri();
            env_data = mg.return_env();
            tmp_charge_vec = mg.return_env_for_manager();
        } */
       /* if(history_line.equals("false")) {
            pri_data = ci.pri_charge();
            env_data = ci.env_charge();
        } else if(history_line.equals("true")) {
            Managerform mg = new Managerform(1);
            pri_data = mg.return_pri();
            env_data = mg.return_env();
        } */
        for (int i = 0; i < pri_data.size(); i++) {
            charge_vec.addElement(pri_data.get(i).get(0));
        }
        for (int i = 0; i < env_data.size(); i++) {
            charge_vec.addElement(env_data.get(i).get(0));
        }
        //Vector charge_vec = tmp_charge_vec;
        arealist.setListData(charge_vec);
        st_charge_data.addAll(charge_vec);
        setResizable(false);
        setVisible(true);
        item1.addActionListener(new MenuActionListener());
        item2.addActionListener(new MenuActionListener());
        addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {

            }

            @Override
            public void windowClosing(WindowEvent e) {
                FileWriter writer = null;
                try {
                    writer = new FileWriter(history,false);
                    writer.write("false");
                    writer.flush();
                } catch(IOException ie) {
                    ie.printStackTrace();
                }
            }

            @Override
            public void windowClosed(WindowEvent e) {
                FileWriter writer = null;
                try {
                    writer = new FileWriter(history,false);
                    writer.write("false");
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
        seoul.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if(e.getStateChange()==ItemEvent.SELECTED) {
                    arealist.removeAll();
                    if(recylce_vec.isEmpty()) {
                        re_empty_check = true;
                    } else {
                        re_empty_check = false;
                        start_seoul_size = recylce_vec.size();
                    }
                    for (int i = 0; i < st_charge_data.size(); i++) {
                        if (st_charge_data.get(i).contains("서울시") || st_charge_data.get(i).contains("서울특별시")) {
                            recylce_vec.addElement(st_charge_data.get(i));
                        }
                    }
                    end_seoul_size = recylce_vec.size() - start_seoul_size;
                    if(re_empty_check == true)  {
                        arealist.setListData(recylce_vec);
                    }
                    else {
                        arealist.removeAll();
                        arealist.setListData(recylce_vec);
                    }
                }
                else {
                    if(re_empty_check == true || start_seoul_size == recylce_vec.size()) {
                        arealist.setListData(charge_vec);
                        recylce_vec.removeAllElements();
                    } else {
                       /* while(recylce_vec.size() != seoul_size) {
                            recylce_vec.remove(seoul_size);
                        } */
                        int index=0;
                        for(int i=0;i<recylce_vec.size();i++) {
                            if(recylce_vec.get(i).toString().contains("서울시") || recylce_vec.get(i).toString().contains("서울특별시")) {
                                index = i;
                              //  System.out.println("발견 인덱스"+index);
                                break;
                            }
                        }
                     //   System.out.println("end_seoul_size "+end_seoul_size);
                        while(true) {
                    //        System.out.println("삭제할 index "+recylce_vec.get(index));
                            recylce_vec.remove(index);
                            if(recylce_vec.isEmpty() == true || index == recylce_vec.size() || recylce_vec.get(index).toString().contains("서울시") == false && recylce_vec.get(index).toString().contains("서울특별시") == false) {
                                break;
                            }
                        }
                       /* for(int i=0;i<end_seoul_size;i++) {
                            recylce_vec.remove(index);
                        } */

                        arealist.removeAll();
                        if(recylce_vec.isEmpty()) {
                            arealist.setListData(charge_vec);
                        } else {
                            arealist.setListData(recylce_vec);
                        }
                    }
                }
            }
        });
        kyunggi.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if(e.getStateChange()==ItemEvent.SELECTED) {
                    arealist.removeAll();
                    if(recylce_vec.isEmpty()) {
                        re_empty_check = true;
                    } else {
                        re_empty_check = false;
                        start_kyunggi_size = recylce_vec.size();
                    }
                    for (int i = 0; i < st_charge_data.size(); i++) {
                        if (st_charge_data.get(i).contains("경기도") || st_charge_data.get(i).contains("인천시")) {
                            recylce_vec.addElement(st_charge_data.get(i));
                        }
                    }
                    end_kyunggi_size = recylce_vec.size() - start_kyunggi_size;
                    if(re_empty_check == true) {
                        arealist.setListData(recylce_vec);
                    }
                    else {
                        arealist.removeAll();
                        arealist.setListData(recylce_vec);
                    }
                }
                else {
                    if(re_empty_check == true || start_kyunggi_size == recylce_vec.size()) {
                        arealist.setListData(charge_vec);
                        recylce_vec.removeAllElements();
                    } else {
                       /* while(recylce_vec.size() != kyunggi_size) {
                            recylce_vec.remove(kyunggi_size);
                        }*/
                        int index=0;
                        for(int i=0;i<recylce_vec.size();i++) {
                            if(recylce_vec.get(i).toString().contains("경기도") || recylce_vec.get(i).toString().contains("인천시")) {
                                index = i;
                                break;
                            }
                        }
                       // System.out.println("경기도 첫번째 인덱스 "+index+" 몇번까지 삭제(end_size) "+end_kyunggi_size);
                      /* for(int i=0;i<end_kyunggi_size;i++) {
                                recylce_vec.remove(index);
                        } */
                        while(true) {
                      //      System.out.println("삭제할 index "+recylce_vec.get(index));
                            recylce_vec.remove(index);
                            if(recylce_vec.isEmpty() == true || index == recylce_vec.size() || recylce_vec.get(index).toString().contains("경기도") == false && recylce_vec.get(index).toString().contains("인천시") == false) {
                                break;
                            }
                        }
                        arealist.removeAll();
                       if(recylce_vec.isEmpty()) {
                           arealist.setListData(charge_vec);
                       } else {
                           arealist.setListData(recylce_vec);
                       }
                    }
                }
            }
        });
        junrado.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if(e.getStateChange()==ItemEvent.SELECTED) {
                    arealist.removeAll();
                    if(recylce_vec.isEmpty()) {
                        re_empty_check = true;
                    } else {
                        re_empty_check = false;
                        start_junrado_size = recylce_vec.size();
                    }
                    for (int i = 0; i < st_charge_data.size(); i++) {
                        if (st_charge_data.get(i).contains("전라도") || st_charge_data.get(i).contains("전라북도") ||
                                st_charge_data.get(i).contains("전라남도") || (st_charge_data.get(i).contains("광주시") ==true && st_charge_data.get(i).contains("경기도") ==false) ||
                                st_charge_data.get(i).contains("광주광역시")) {
                            recylce_vec.addElement(st_charge_data.get(i));
                        }
                    }
                    end_junrado_size = recylce_vec.size() - start_junrado_size;
                    if(re_empty_check == true) {
                        arealist.setListData(recylce_vec);
                    }
                    else {
                        arealist.removeAll();
                        arealist.setListData(recylce_vec);
                    }
                }
                else {
                    if(re_empty_check == true || start_junrado_size == recylce_vec.size()) {
                        arealist.setListData(charge_vec);
                        recylce_vec.removeAllElements();
                    } else {
                        /*while(recylce_vec.size() != junrado_size) {
                            recylce_vec.remove(junrado_size);
                        } */
                        int index=0;
                        for(int i=0;i<recylce_vec.size();i++) {
                            if(recylce_vec.get(i).toString().contains("전라도") || recylce_vec.get(i).toString().contains("전라북도") ||
                                    recylce_vec.get(i).toString().contains("전라남도") || (recylce_vec.get(i).toString().contains("광주시") ==true && recylce_vec.get(i).toString().contains("경기도") == false) ||
                                    recylce_vec.get(i).toString().contains("광주광역시")) {
                                index = i;
                                break;
                            }
                        }
                        /*for(int i=0;i<end_junrado_size;i++) {
                            recylce_vec.remove(index);
                        } */
                        while(true) {
                         //  System.out.println("삭제할 index "+ index +" " +recylce_vec.get(index));
                            recylce_vec.remove(index);
                            if(recylce_vec.isEmpty() == true || index == recylce_vec.size() || !recylce_vec.get(index).toString().contains("전라도") && !recylce_vec.get(index).toString().contains("전라북도") &&
                                    !recylce_vec.get(index).toString().contains("전라남도") && !recylce_vec.get(index).toString().contains("광주광역시")) {
                                break;
                            }
                        }
                        arealist.removeAll();
                        if(recylce_vec.isEmpty()) {
                            arealist.setListData(charge_vec);
                        } else {
                            arealist.setListData(recylce_vec);
                        }
                    }
                }
            }
        });
        chungcheongdo.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if(e.getStateChange()==ItemEvent.SELECTED) {
                    arealist.removeAll();
                    if(recylce_vec.isEmpty()) {
                        re_empty_check = true;
                    } else {
                        re_empty_check = false;
                        start_chungcheongdo_size = recylce_vec.size();
                    }
                    for (int i = 0; i < st_charge_data.size(); i++) {
                        if (st_charge_data.get(i).contains("충청도") || st_charge_data.get(i).contains("충청북도") ||
                                st_charge_data.get(i).contains("충청남도") || st_charge_data.get(i).contains("세종시") ||
                                st_charge_data.get(i).contains("세종특별시") || st_charge_data.get(i).contains("대전광역시")){
                            recylce_vec.addElement(st_charge_data.get(i));
                        }
                    }
                    end_chungcheongdo_size = recylce_vec.size() - start_chungcheongdo_size;
                    if(re_empty_check == true) {
                        arealist.setListData(recylce_vec);
                    }
                    else {
                        arealist.removeAll();
                        arealist.setListData(recylce_vec);
                    }
                }
                else {
                    if(re_empty_check == true || start_chungcheongdo_size == recylce_vec.size()) {
                        arealist.setListData(charge_vec);
                        recylce_vec.removeAllElements();
                    } else {
                      /*  while(recylce_vec.size() != chungcheongdo_size) {
                            recylce_vec.remove(chungcheongdo_size);
                        } */
                        int index=0;
                        for(int i=0;i<recylce_vec.size();i++) {
                            if(recylce_vec.get(i).toString().contains("충청도") || recylce_vec.get(i).toString().contains("충청북도") ||
                                    recylce_vec.get(i).toString().contains("충청남도") || recylce_vec.get(i).toString().contains("세종시")||
                                    recylce_vec.get(i).toString().contains("세종특별시")) {
                                index = i;
                                break;
                            }
                        }
                        while(true) {
                           // System.out.println("삭제할 index "+recylce_vec.get(index));
                            recylce_vec.remove(index);
                            if(recylce_vec.isEmpty() == true || index == recylce_vec.size() || !recylce_vec.get(index).toString().contains("충청도") && !recylce_vec.get(index).toString().contains("충청북도") &&
                                    !recylce_vec.get(index).toString().contains("충청남도") && !recylce_vec.get(index).toString().contains("세종시") &&
                                    !recylce_vec.get(index).toString().contains("세종특별시") && !recylce_vec.get(index).toString().contains("대전광역시")) {
                                break;
                            }
                        }
                        arealist.removeAll();
                        if(recylce_vec.isEmpty()) {
                            arealist.setListData(charge_vec);
                        } else {
                            arealist.setListData(recylce_vec);
                        }
                    }
                }
            }
        });
        gyeongsangdo.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if(e.getStateChange()==ItemEvent.SELECTED) {
                    arealist.removeAll();
                    if(recylce_vec.isEmpty()) {
                        re_empty_check = true;
                    } else {
                        re_empty_check = false;
                        start_gyeongsangdo_size = recylce_vec.size();
                    }
                    for (int i = 0; i < st_charge_data.size(); i++) {
                        if (st_charge_data.get(i).contains("경상도") || st_charge_data.get(i).contains("경상북도") ||
                                st_charge_data.get(i).contains("경상남도") || st_charge_data.get(i).contains("부산시") ||
                                st_charge_data.get(i).contains("부산광역시") || st_charge_data.get(i).contains("울산시") ||
                                st_charge_data.get(i).contains("울산광역시")) {
                            recylce_vec.addElement(st_charge_data.get(i));
                        }
                    }
                    end_gyeongsangdo_size = recylce_vec.size() - start_gyeongsangdo_size;
                    if(re_empty_check == true) {
                        arealist.setListData(recylce_vec);
                    }
                    else {
                        arealist.removeAll();
                        if(recylce_vec.isEmpty()) {
                            arealist.setListData(charge_vec);
                        } else {
                            arealist.setListData(recylce_vec);
                        }
                    }
                }
                else {
                    if(re_empty_check == true || start_gyeongsangdo_size == recylce_vec.size()) {
                        arealist.setListData(charge_vec);
                        recylce_vec.removeAllElements();
                    } else {
                        /*while(recylce_vec.size() != gyeongsangdo_size) {
                            recylce_vec.remove(gyeongsangdo_size);
                        } */
                        int index=0;
                        for(int i=0;i<recylce_vec.size();i++) {
                            if(recylce_vec.get(i).toString().contains("경상도") || recylce_vec.get(i).toString().contains("경상북도") ||
                                    recylce_vec.get(i).toString().contains("경상남도") || recylce_vec.get(i).toString().contains("부산시") ||
                                    recylce_vec.get(i).toString().contains("부산광역시") || recylce_vec.get(i).toString().contains("울산시") ||
                                    recylce_vec.get(i).toString().contains("울산광역시")) {
                                index = i;
                                break;
                            }
                        }
                        while(true) {
                           // System.out.println("삭제할 index "+recylce_vec.get(index));
                            recylce_vec.remove(index);
                            if(recylce_vec.isEmpty() == true || index == recylce_vec.size() || !recylce_vec.get(index).toString().contains("경상도") && !recylce_vec.get(index).toString().contains("경상북도") &&
                                    !recylce_vec.get(index).toString().contains("경상남도") && !recylce_vec.get(index).toString().contains("부산시") &&
                                    !recylce_vec.get(index).toString().contains("부산광역시") && !recylce_vec.get(index).toString().contains("울산시") &&
                                    !recylce_vec.get(index).toString().contains("울산광역시")) {
                                break;
                            }
                        }
                        arealist.removeAll();
                        if(recylce_vec.isEmpty()) {
                            arealist.setListData(charge_vec);
                        } else {
                            arealist.setListData(recylce_vec);
                        }
                    }
                }
            }
        });
        jejudo.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if(e.getStateChange()==ItemEvent.SELECTED) {
                    arealist.removeAll();
                    if(recylce_vec.isEmpty()) {
                        re_empty_check = true;
                    } else {
                        re_empty_check = false;
                        start_jejudo_size = recylce_vec.size();
                    }
                    for (int i = 0; i < st_charge_data.size(); i++) {
                        if (st_charge_data.get(i).contains("제주시") || st_charge_data.get(i).contains("제주특별자치도")) {
                            recylce_vec.addElement(st_charge_data.get(i));
                        }
                    }
                    end_jejudo_size = recylce_vec.size() - start_jejudo_size;
                    if(re_empty_check == true) {
                        arealist.setListData(recylce_vec);
                    }
                    else {
                        arealist.removeAll();
                        arealist.setListData(recylce_vec);
                    }
                }
                else {
                    if(re_empty_check == true || start_jejudo_size == recylce_vec.size()) {
                        arealist.setListData(charge_vec);
                        recylce_vec.removeAllElements();
                    } else {
                      /*  while(recylce_vec.size() != jejudo_size) {
                            recylce_vec.remove(jejudo_size);
                        } */
                        int index=0;
                        for(int i=0;i<recylce_vec.size();i++) {
                            if(recylce_vec.get(i).toString().contains("제주시") || recylce_vec.get(i).toString().contains("제주특별자치도")) {
                                index = i;
                                break;
                            }
                        }
                        while(true) {
                         //   System.out.println("삭제할 index "+recylce_vec.get(index));
                            recylce_vec.remove(index);
                            if(recylce_vec.isEmpty() == true || index == recylce_vec.size() || !recylce_vec.get(index).toString().contains("제주시") && !recylce_vec.get(index).toString().contains("제주특별자치도")) {
                                break;
                            }
                        }
                        arealist.removeAll();
                        if(recylce_vec.isEmpty()) {
                            arealist.setListData(charge_vec);
                        } else {
                            arealist.setListData(recylce_vec);
                        }
                    }
                }
            }
        });
        Gwangwondo.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if(e.getStateChange()==ItemEvent.SELECTED) {
                    arealist.removeAll();
                    if(recylce_vec.isEmpty()) {
                        re_empty_check = true;
                    } else {
                        re_empty_check = false;
                        start_gwangwondo_size = recylce_vec.size();
                    }
                    for (int i = 0; i < st_charge_data.size(); i++) {
                        if (st_charge_data.get(i).contains("강원도")) {
                            recylce_vec.addElement(st_charge_data.get(i));
                        }
                    }
                    end_gwangwondo_size = recylce_vec.size() - start_gwangwondo_size;
                    if(re_empty_check == true) {
                        arealist.setListData(recylce_vec);
                    }
                    else {
                        arealist.removeAll();
                        arealist.setListData(recylce_vec);
                    }
                }
                else {
                    if(re_empty_check == true || start_gwangwondo_size == recylce_vec.size()) {
                        arealist.setListData(charge_vec);
                        recylce_vec.removeAllElements();
                    } else {
                      /*  while(recylce_vec.size() != jejudo_size) {
                            recylce_vec.remove(jejudo_size);
                        } */
                        int index=0;
                        for(int i=0;i<recylce_vec.size();i++) {
                            if(recylce_vec.get(i).toString().contains("강원도")) {
                                index = i;
                                break;
                            }
                        }
                        while(true) {
                            //   System.out.println("삭제할 index "+recylce_vec.get(index));
                            recylce_vec.remove(index);
                            if(recylce_vec.isEmpty() == true || index == recylce_vec.size() || !recylce_vec.get(index).toString().contains("강원도")) {
                                break;
                            }
                        }
                        arealist.removeAll();
                        if(recylce_vec.isEmpty()) {
                            arealist.setListData(charge_vec);
                        } else {
                            arealist.setListData(recylce_vec);
                        }
                    }
                }
            }
        });
        List <List> lb_pri_data = pri_data;
        List <List> lb_env_data = env_data;
        timer_pri_data = pri_data;
        timer_env_data = env_data;
        arealist.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                int select_list_index=arealist.getSelectedIndex();
                String select_list_name = arealist.getSelectedValue().toString();
                pri_list_index=0;
                env_list_index=0;
                while(!select_list_name.equals(lb_pri_data.get(pri_list_index).get(0)) && !select_list_name.equals(lb_env_data.get(env_list_index).get(0))) {
                    if(pri_list_index < lb_pri_data.size()-1) {
                        pri_list_index++;
                    }
                    if(env_list_index < lb_env_data.size()-1) {
                        env_list_index++;
                    }
                }
                if(select_list_name.equals(lb_pri_data.get(pri_list_index).get(0))) {
                    check_pri_env = true;
                    lb_locate.setText(lb_pri_data.get(pri_list_index).get(0).toString());
                    lb_battery.setText(lb_pri_data.get(pri_list_index).get(1).toString());
                    lb_time.setText(lb_pri_data.get(pri_list_index).get(2).toString());
                    lb_use.setText(lb_pri_data.get(pri_list_index).get(3).toString());
                    lb_size.setText(lb_pri_data.get(pri_list_index).get(4).toString() + " KW");
                } else if(select_list_name.equals(lb_env_data.get(env_list_index).get(0))) {
                    check_pri_env = false;
                    lb_locate.setText(lb_env_data.get(env_list_index).get(0).toString());
                    lb_battery.setText(lb_env_data.get(env_list_index).get(1).toString());
                    lb_time.setText(lb_env_data.get(env_list_index).get(2).toString());
                    lb_use.setText(lb_env_data.get(env_list_index).get(3).toString());
                    lb_size.setText(lb_env_data.get(env_list_index).get(4).toString() + " KW");
                }
                if(lb_use.getText().equals("사용가능")) {
                  //  lb_use.setIcon(charge_yes);
                    if(car_battery_size > 0) {
                        bt_start.setEnabled(true);
                    }
                    bt_start.setText("충전시작");
                } else if(lb_use.getText().equals("사용중")) {
                   // lb_use.setIcon(charge_no);
                    bt_start.setEnabled(false);
                    bt_start.setText("충전중");
                } else if(lb_use.getText().equals("점검중")) {
                   // lb_use.setIcon(charge_fix);
                    bt_start.setEnabled(false);
                    bt_start.setText("점검중");
                }
            }
        });
        bt_start.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String[] charge_battery = lb_size.getText().split(" ");
                thread_time=0;
                percent_car_charge=0;
                String regExp = "^[0-9]+$";
                int trans_car_charge=0;
                int result = JOptionPane.showConfirmDialog(null,
                        "충전소 이름: "+lb_locate.getText()+"\n충전기 타입: "+lb_battery.getText()+"\n이용가능시간: "
                                +lb_time.getText()+"\n현재상태: "+lb_use.getText()+"\n해당 충전소와 타입이 맞습니까?",
                        "확인",JOptionPane.YES_NO_OPTION);
                if(result == JOptionPane.YES_OPTION) {
                    String str_car_charge = JOptionPane.showInputDialog("현재 차량의 남은 배터리 양을 입력해주세요 (%단위)\n" +
                            "[예시: 배터리가 23% 남았으면 23을 입력해 주세요.");
                    if(str_car_charge != null && str_car_charge.matches(regExp) && Integer.parseInt(str_car_charge) >= 0 && Integer.parseInt(str_car_charge) < 100) {
                        percent_car_charge = Integer.parseInt(str_car_charge);
                        trans_car_charge = CalcChargingTime.trans(car_battery_size,percent_car_charge);
                        thread_time = CalcChargingTime.calc(car_battery_size,trans_car_charge,Integer.parseInt(charge_battery[0]));
                        //String ts = Integer.toString(time);
                        lb_uselocate.setText(lb_locate.getText());
                        lb_usetype.setText(lb_battery.getText());
                        if(thread_time >=10 ) {
                            lb_endtime.setText("총 예상 소요시간 : " + Integer.toString(thread_time) + " 분");
                        } else {
                            lb_endtime.setText("총 예상 소요시간 : " + 10 + " 분 이내");
                        }

                        if(check_pri_env == true) {
                            lb_pri_data.get(pri_list_index).set(3,"사용중");
                        } else {
                            lb_env_data.get(env_list_index).set(3,"사용중");
                        }
                        bt_start.setEnabled(false);
                        bt_end.setEnabled(true);
                        Thread thread = new Thread(runnable);
                        thread.start();
                    } else {
                        JOptionPane.showMessageDialog(null,"잘못된 값을 입력했습니다.","Error",JOptionPane.ERROR_MESSAGE);
                    }
                } else if(result == JOptionPane.NO_OPTION) {
                    System.out.println("이용하지않음, 창을 닫겠습니다.");
                } else {
                    System.out.println("해당사항 없음");
                }
            }
        });
        bt_end.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int result = JOptionPane.showConfirmDialog(null,"정말 충전을 중단 하시겠습니까?");
                if(result == JOptionPane.YES_OPTION) {
                    thread_check = 1;
                    lb_endtime.setText("현재 "+percent_car_charge+"% 충전 되어있습니다.");
                }
            }
        });
    }


}
