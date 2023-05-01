package linux.java.file;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;

/**
 * packageName    : linux.java.file
 * fileName       : FileEx6
 * author         : ipeac
 * date           : 2023-04-27
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023-04-27        ipeac       최초 생성
 */
public class FileEx7 {
    
    public static void main(String[] args) throws IOException {
        if (args.length != 1) {
            System.out.println("USAGE : java FileEx7 pattern");
            System.exit(0);
        }
        
        String currDir = System.getProperty("user.dir");
        
        File dir = new File(currDir);
        final String pattern = args[0];
        
    }
    
}
