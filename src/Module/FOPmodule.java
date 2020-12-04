package Module;

import java.io.*;
import java.util.List;
import java.util.Scanner;

public class FOPmodule {
    int count_line=0;
    int delete_line=0;
    boolean remove_env_pri_check;
    boolean add_env_pri_check;
    public boolean return_add_env_pri_check() {
        return this.add_env_pri_check;
    }
    public boolean return_remove_env_pri_check() {
        return this.remove_env_pri_check;
    }
    public int return_deleteline() {
        return this.delete_line;
    }
    public void File_add(String check,String path,String addr_name,String addr,String time,String type) {
        BufferedWriter bw = null;
        if(check.equals("환경부")) {
            try {
                bw = new BufferedWriter(new FileWriter(path, true));
                PrintWriter pw = new PrintWriter(bw, true);
                pw.write(" " + "\n");
                pw.write(addr_name + " " + addr + "\n" + type + " " + time);
                pw.flush();
                pw.close();
                add_env_pri_check = true;
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        } else if(check.equals("민간")) {
            try {
                bw = new BufferedWriter(new FileWriter(path, true));
                PrintWriter pw = new PrintWriter(bw, true);
                pw.write(" " + "\n");
                pw.write(addr_name + " " + addr + "\n" + time + " " + type + " Y");
                pw.flush();
                pw.close();
                add_env_pri_check = false;
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }
    }
    public void File_remove(String path,String delete_addr_name,int pri_size,int env_size) {
        String line;
        count_line = 1;
        int size=0;
        String substring_path = path.substring(0,path.indexOf(".txt"));
        File file = new File(substring_path+"_result"+".txt");
     //   System.out.println(substring_path);
        if(substring_path.contains("Env")) {
            remove_env_pri_check = true;
            size = env_size;
        } else if(substring_path.contains("pri")) {
            remove_env_pri_check = false;
            size = pri_size;
        }
        try{
            BufferedReader br = new BufferedReader(new FileReader(new File(path)));
            file.createNewFile();
            FileWriter fw = new FileWriter(file);
            BufferedWriter bw = new BufferedWriter(fw);
            while((line = br.readLine()) != null) {
            //    System.out.println(line);
                if(line.contains(delete_addr_name) == false) {
                    if(line.contains("서울") || line.contains("경기") || line.contains("강원") || line.contains("충청") || line.contains("전라")
                        || line.contains("경상") || line.contains("광주") || line.contains("부산") || line.contains("울산") || line.contains("인천")
                        || line.contains("제주특별자치도") || line.contains("세종") || line.contains("대구") || line.contains("대전")) {
                        count_line++;
                       // System.out.println(count_line);
                       // System.out.println("문제 >> "+line);
                    }
                    bw.write(line);
                  //  if(size-1 != count_line) {
                        bw.write("\n");
                  //  }
                } else {
                    delete_line = count_line;
                 //   System.out.println(delete_line);
                    line = br.readLine();
                }
                bw.flush();
            }
            delete_line++;
          //  System.out.println(delete_line);
            bw.close();
            fw.close();
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        File deleteFile = new File(path);
        if(deleteFile.exists()) {
            deleteFile.delete();
            file.renameTo(new File(path));
        }
    }
    public void File_state_chagne(File input,List<List> pri,List<List> env) {
        FileWriter fw = null;
        try{
            fw = new FileWriter(input,false);
                for (int i = 0; i < pri.size(); i++) {
                    fw.write(i + " ");
                    fw.write(pri.get(i).get(3).toString() + " 민간" + "\n");
                    fw.flush();
                }
                for (int i = 0; i < env.size(); i++) {
                    fw.write(i + " ");
                    fw.write(env.get(i).get(3).toString() + " 환경" + "\n");
                    fw.flush();
                }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void eraseLast(String path) throws IOException {
        File file = new File(path);
        RandomAccessFile randomAccessFile = new RandomAccessFile(file, "rw");
        byte b;
        long length = randomAccessFile.length() ;
        if (length != 0) {
            do {
                length -= 1;
                randomAccessFile.seek(length);
                b = randomAccessFile.readByte();
            } while (b != 10 && length > 0);
            randomAccessFile.setLength(length);
            randomAccessFile.close();
        }
    }
 }
