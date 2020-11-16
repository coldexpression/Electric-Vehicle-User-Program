package formPackage;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

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
    private JLabel lb_locate;
    private JCheckBox gyeongsangdo;
    private JCheckBox jejudo;
    private JLabel lb_battery;
    private JLabel lb_time;
    private JLabel lb_use;
    boolean re_empty_check = true;
    int vec_size=0;
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
                                st_charge_data.get(i).contains("세종특별시")){
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
                                    !recylce_vec.get(index).toString().contains("세종특별시")) {
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
        arealist.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                int select_list_index=arealist.getSelectedIndex();
                String select_list_name = arealist.getSelectedValue().toString();
                int list_index;
                while(select_list_name.equals())
            }
        });
    }
}
