package linux.java.file;

import java.io.File;
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
public class FileEx6 {
    static int found = 0;
    
    public static void main(String[] args) throws IOException {
        if (args.length != 2) {
            System.out.println("USAGE : java FileEx6 DIRECTORY KEYWORD");
            System.exit(0);
        }
        File dir = new File(args[0]);
        String keyword = args[1];
        
        if (!dir.exists() || !dir.isDirectory()) {
            System.out.println("유효하지 않은 디렉토리입니다.");
            System.exit(0);
        }
        try {
        
        } catch (IOException e) {
            
        }
    }
}
