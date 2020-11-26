package Module;

import java.io.*;
public class FOPmodule {
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
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        } else if(check.equals("민간")) {
            try {
                bw = new BufferedWriter(new FileWriter(path, true));
                PrintWriter pw = new PrintWriter(bw, true);
                pw.write(" " + "\n");
                pw.write(addr_name + " " + addr + "\n" + time + " " + type);
                pw.flush();
                pw.close();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }
    }
    public void File_remove(String path,String delete_addr_name) {
        String line;
        try{
            BufferedReader br = new BufferedReader(new FileReader(path));
            File file = new File(path);
            file.createNewFile();
            FileWriter fw = new FileWriter(file);
            BufferedWriter bw = new BufferedWriter(fw);
            while((line = br.readLine()) != null) {
                if(!line.contains(delete_addr_name)) {
                    bw.write(line);
                    bw.write("\n");
                }
                bw.flush();
            }
            bw.close();
            fw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
