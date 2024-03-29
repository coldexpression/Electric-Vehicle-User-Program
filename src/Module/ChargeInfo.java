package Module;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
public class ChargeInfo {
    //static List m_envinfo;
    //static List<List> envinfo;
    //static List m_prinfo;
    //static List<List> prinfo;
    // 리스트 = [위치, 배터리 타입, 충전소 이용시간, 충전소 사용여부]
    // 민간기업 '16년도 전국 충전소 목록
    public static List<List> pri_charge() {
        File file = new File("./src/pri_charge.txt");
        List m_prinfo;
        List<List> prinfo = new ArrayList();
        double radomValue;
        int random_num;
        try(BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            String[] split_line;
            m_prinfo = new ArrayList();
            //prinfo = new ArrayList();
            int line_num=0;
            while ((line = br.readLine()) != null) {
                radomValue = Math.random();
                random_num = (int)(radomValue*50) + 30;
                if(line_num == 1) {
                    line_num--;
                    split_line = line.split(" ");
                    m_prinfo.add(split_line[split_line.length-2]); //배터리 타입 입력
                    m_prinfo.add(split_line[0]); //충전소 이용시간 입력
                    m_prinfo.add("사용가능"); // 충전소 사용여부
                    //System.out.println(m_prinfo.get(1));
                    if(m_prinfo.get(1).toString().contains("완속")) {
                        m_prinfo.add(7);
                    } else {
                        m_prinfo.add(random_num); // 충전소 설비용량
                    }
                    prinfo.add(m_prinfo);
                    m_prinfo = new ArrayList();
                } else {
                    m_prinfo.add(line); //충전소 위치 입력
                    line_num++;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
       // System.out.println("민간기업 전국 충전소 목록");
       // System.out.println(prinfo);
        return prinfo;
    }

    // 환경부 '16년도 전국 충전소 목록
    public static List<List> env_charge() {
        File file = new File("./src/Env_charge.txt");
        List m_envinfo;
        List<List> envinfo;
        envinfo = new ArrayList();
        double radomValue;
        int random_num;
        try(BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            String[] split_line;
            m_envinfo = new ArrayList();
            //envinfo = new ArrayList();
            int line_num=0;
            while ((line = br.readLine()) != null) {
                radomValue = Math.random();
                random_num = (int)(radomValue*50) + 30;
                if(line_num == 1) {
                    line_num--;
                    split_line = line.split(" ");
                    m_envinfo.add(split_line[0]); //배터리 타입 입력
                    m_envinfo.add(split_line[1]); //충전소 이용시간 입력
                    m_envinfo.add("사용가능");
                    m_envinfo.add(random_num); //충전소 설비용량
                    envinfo.add(m_envinfo);
                    m_envinfo = new ArrayList();
                } else {
                    m_envinfo.add(line); //충전소 위치 입력
                    line_num++;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        //System.out.println("환경부 전국 충전소 목록");
       // System.out.println(envinfo);
        return envinfo;
    }
    public static String find_charge_index(List<List> pri_data,List<List> env_data,String full_addr) {
        int pri_list_index = 0;
        int env_list_index = 0;
        String agency_index = null;
        while(!full_addr.equals(pri_data.get(pri_list_index).get(0)) && !full_addr.equals(env_data.get(env_list_index).get(0))) {
            if(pri_list_index < pri_data.size()-1) {
                pri_list_index++;
            }
            if(env_list_index < env_data.size()-1) {
                env_list_index++;
            }
        }
        if(full_addr.equals(pri_data.get(pri_list_index).get(0))) {
            agency_index = "pri"+ " " + String.valueOf(pri_list_index);
        } else if(full_addr.equals(env_data.get(env_list_index).get(0))){
            agency_index = "env"+ " " + String.valueOf(env_list_index);
        }
        return agency_index;
    }

    public static void main(String args[]) {
        List<List> test = new ArrayList();
        pri_charge();
        test = env_charge();
    }
}
