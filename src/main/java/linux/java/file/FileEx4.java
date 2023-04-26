package linux.java.file;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * packageName    : linux.java.file
 * fileName       : FileEx3
 * author         : ipeac
 * date           : 2023-04-23
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023-04-23        ipeac       최초 생성
 */
public class FileEx4 {
    public static void main(String[] args) throws IOException {
        String currDir = System.getProperty("user.dir");
        File dir = new File(currDir);
        
        File[] files = dir.listFiles();
        
        for (int i = 0; i < files.length; i++) {
            File f = files[i];
            String name = f.getName();
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mma");
            String attribute = "";
            String size = "";
            
            if (files[i].isDirectory()) {
                attribute = "DIR";
            } else {
                size = String.valueOf(f.length());
                attribute = f.canRead() ? "R" : " ";
                attribute += f.canWrite() ? "W" : " ";
                attribute += f.isHidden() ? "H" : " ";
            }
            System.out.printf("%s %3s %6s %s\n", df.format(new Date(f.lastModified())), attribute, size, name);
            
        }
    }
}
