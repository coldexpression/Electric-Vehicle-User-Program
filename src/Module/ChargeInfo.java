package Module;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
public class ChargeInfo {
    static List m_envinfo;
    static List<List> envinfo;
    static List m_prinfo;
    static List<List> prinfo;
    // 민간기업 '16년도 전국 충전소 목록
    public static void pri_charge() {
        File file = new File("D:\\Git_repos\\Electric-Vehicle-User-Program\\src\\pri_charge.txt");

        try(BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            String[] split_line;
            m_prinfo = new ArrayList();
            prinfo = new ArrayList();
            int line_num=0;
            while ((line = br.readLine()) != null) {
                if(line_num == 1) {
                    line_num--;
                    split_line = line.split(" ");
                    m_prinfo.add(split_line[split_line.length-2]); //배터리 타입 입력
                    m_prinfo.add(split_line[0]); //충전소 이용시간 입력
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
        System.out.println("민간기업 전국 충전소 목록");
        System.out.println(prinfo);
    }

    // 환경부 '16년도 전국 충전소 목록
    public static void env_charge() {
        File file = new File("D:\\Git_repos\\Electric-Vehicle-User-Program\\src\\Env_charge.txt");

        try(BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            String[] split_line;
            m_envinfo = new ArrayList();
            envinfo = new ArrayList();
            int line_num=0;
            while ((line = br.readLine()) != null) {
                if(line_num == 1) {
                    line_num--;
                    split_line = line.split(" ");
                    m_envinfo.add(split_line[0]); //배터리 타입 입력
                    m_envinfo.add(split_line[1]); //충전소 이용시간 입력
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
        System.out.println("환경부 전국 충전소 목록");
        System.out.println(envinfo);
    }
    public static void main(String args[]) {
        pri_charge();
        env_charge();
    }
}
