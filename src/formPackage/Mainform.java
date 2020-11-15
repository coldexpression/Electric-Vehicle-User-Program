package formPackage;

import javax.swing.*;
import Module.*;

import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.*;
import java.util.List;

public class Mainform extends JFrame{
    private javax.swing.JPanel JPanel;
    private JList arealist;
    private JScrollPane sp;
    private JCheckBox seoul;
    private JCheckBox kyunggi;
    private JCheckBox junrado;
    private JCheckBox chungcheongdo;
    private JLabel lb1;
    private JCheckBox gyeongsangdo;
    private JCheckBox jejudo;
    boolean re_empty_check = true;
    int vec_size=0;
    public void Buttonsize() {
        seoul.setBounds(200,200,200,200);
        kyunggi.setBounds(200,210,200,200);
        junrado.setBounds(200,220,200,200);
        chungcheongdo.setBounds(200,230,200,200);
        System.out.println("호출");
    }

    public Mainform() {
        Vector charge_vec = new Vector();
        Vector recylce_vec = new Vector();
        ChargeInfo ci = new ChargeInfo();
        List <List> pri_data = new ArrayList();
        List <List> env_data = new ArrayList();
        List<String> st_charge_data = new ArrayList<>();
        setTitle("전기 자동차 사용자용 프로그램");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        sp.setViewportView(arealist);
        setContentPane(JPanel);
        setSize(1300,600);
        Buttonsize();
        pri_data = ci.pri_charge();
        env_data = ci.env_charge();
        for(int i=0;i<pri_data.size();i++) {
            charge_vec.addElement(pri_data.get(i).get(0));
        }
        for(int i=0;i<env_data.size();i++) {
            charge_vec.addElement(env_data.get(i).get(0));
        }
        arealist.setListData(charge_vec);
        st_charge_data.addAll(charge_vec);
        setResizable(false);
        setVisible(true);
        seoul.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if(e.getStateChange()==ItemEvent.SELECTED) {
                    arealist.removeAll();
                    if(recylce_vec.isEmpty()) {
                        re_empty_check = true;
                    } else {
                        re_empty_check = false;
                        vec_size = recylce_vec.size();
                    }
                    for (int i = 0; i < st_charge_data.size(); i++) {
                        if (st_charge_data.get(i).contains("서울시") || st_charge_data.get(i).contains("서울특별시")) {
                            recylce_vec.addElement(st_charge_data.get(i));
                        }
                    }
                    if(re_empty_check == true) {
                        arealist.setListData(recylce_vec);
                    }
                    else {
                        arealist.removeAll();
                        arealist.setListData(recylce_vec);
                    }
                }
                else {
                    if(re_empty_check == true || vec_size == recylce_vec.size()) {
                        arealist.setListData(charge_vec);
                        recylce_vec.removeAllElements();
                    } else {
                        while(recylce_vec.size() != vec_size) {
                            recylce_vec.remove(vec_size);
                        }
                        arealist.removeAll();
                        arealist.setListData(recylce_vec);
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
                        vec_size = recylce_vec.size();
                    }
                    for (int i = 0; i < st_charge_data.size(); i++) {
                        if (st_charge_data.get(i).contains("경기도") || st_charge_data.get(i).contains("인천시")) {
                            recylce_vec.addElement(st_charge_data.get(i));
                        }
                    }
                    if(re_empty_check == true) {
                        arealist.setListData(recylce_vec);
                    }
                    else {
                        arealist.removeAll();
                        arealist.setListData(recylce_vec);
                    }
                }
                else {
                    if(re_empty_check == true || vec_size == recylce_vec.size()) {
                        arealist.setListData(charge_vec);
                        recylce_vec.removeAllElements();
                    } else {
                        while(recylce_vec.size() != vec_size) {
                            recylce_vec.remove(vec_size);
                        }
                        arealist.removeAll();
                        arealist.setListData(recylce_vec);
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
                        vec_size = recylce_vec.size();
                    }
                    for (int i = 0; i < st_charge_data.size(); i++) {
                        if (st_charge_data.get(i).contains("전라도") || st_charge_data.get(i).contains("전라북도") ||
                                st_charge_data.get(i).contains("전라남도") || (st_charge_data.get(i).contains("광주시") ==true && st_charge_data.get(i).contains("경기도") ==false) ||
                                st_charge_data.get(i).contains("광주광역시")) {
                            recylce_vec.addElement(st_charge_data.get(i));
                        }
                    }
                    if(re_empty_check == true) {
                        arealist.setListData(recylce_vec);
                    }
                    else {
                        arealist.removeAll();
                        arealist.setListData(recylce_vec);
                    }
                }
                else {
                    if(re_empty_check == true || vec_size == recylce_vec.size()) {
                        arealist.setListData(charge_vec);
                        recylce_vec.removeAllElements();
                    } else {
                        while(recylce_vec.size() != vec_size) {
                            recylce_vec.remove(vec_size);
                        }
                        arealist.removeAll();
                        arealist.setListData(recylce_vec);
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
                        vec_size = recylce_vec.size();
                    }
                    for (int i = 0; i < st_charge_data.size(); i++) {
                        if (st_charge_data.get(i).contains("충청도") || st_charge_data.get(i).contains("충청북도") ||
                                st_charge_data.get(i).contains("충청남도") || st_charge_data.get(i).contains("세종시") ||
                                st_charge_data.get(i).contains("세종특별시")){
                            recylce_vec.addElement(st_charge_data.get(i));
                        }
                    }
                    if(re_empty_check == true) {
                        arealist.setListData(recylce_vec);
                    }
                    else {
                        arealist.removeAll();
                        arealist.setListData(recylce_vec);
                    }
                }
                else {
                    if(re_empty_check == true || vec_size == recylce_vec.size()) {
                        arealist.setListData(charge_vec);
                        recylce_vec.removeAllElements();
                    } else {
                        while(recylce_vec.size() != vec_size) {
                            recylce_vec.remove(vec_size);
                        }
                        arealist.removeAll();
                        arealist.setListData(recylce_vec);
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
                        vec_size = recylce_vec.size();
                    }
                    for (int i = 0; i < st_charge_data.size(); i++) {
                        if (st_charge_data.get(i).contains("경상도") || st_charge_data.get(i).contains("경상북도") ||
                                st_charge_data.get(i).contains("경상남도") || st_charge_data.get(i).contains("부산시") ||
                                st_charge_data.get(i).contains("부산광역시") || st_charge_data.get(i).contains("울산시") ||
                                st_charge_data.get(i).contains("울산광역시")) {
                            recylce_vec.addElement(st_charge_data.get(i));
                        }
                    }
                    if(re_empty_check == true) {
                        arealist.setListData(recylce_vec);
                    }
                    else {
                        arealist.removeAll();
                        arealist.setListData(recylce_vec);
                    }
                }
                else {
                    if(re_empty_check == true || vec_size == recylce_vec.size()) {
                        arealist.setListData(charge_vec);
                        recylce_vec.removeAllElements();
                    } else {
                        while(recylce_vec.size() != vec_size) {
                            recylce_vec.remove(vec_size);
                        }
                        arealist.removeAll();
                        arealist.setListData(recylce_vec);
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
                        vec_size = recylce_vec.size();
                    }
                    for (int i = 0; i < st_charge_data.size(); i++) {
                        if (st_charge_data.get(i).contains("제주시") || st_charge_data.get(i).contains("제주특별자치도")) {
                            recylce_vec.addElement(st_charge_data.get(i));
                        }
                    }
                    if(re_empty_check == true) {
                        arealist.setListData(recylce_vec);
                    }
                    else {
                        arealist.removeAll();
                        arealist.setListData(recylce_vec);
                    }
                }
                else {
                    if(re_empty_check == true || vec_size == recylce_vec.size()) {
                        arealist.setListData(charge_vec);
                        recylce_vec.removeAllElements();
                    } else {
                        while(recylce_vec.size() != vec_size) {
                            recylce_vec.remove(vec_size);
                        }
                        arealist.removeAll();
                        arealist.setListData(recylce_vec);
                    }
                }
            }
        });
    }
}
