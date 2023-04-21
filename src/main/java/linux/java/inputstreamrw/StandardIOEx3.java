package linux.java.inputstreamrw;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;

/**
 * packageName    : linux.java.inputstreamrw
 * fileName       : StandardIOEx2
 * author         : ipeac
 * date           : 2023-04-21
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023-04-21        ipeac       최초 생성
 */
public class StandardIOEx3 {
    public static void main(String[] args) throws IOException {
        PrintStream ps = null;
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream("123.txt", true);
            ps = new PrintStream(fos);
            
            System.setOut(ps);//Systme.out 의 출력대상을 123.txt 파일로 변경함
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
        }
        System.out.println("Hello by System.out");
        System.err.println("Hello by System.err");
    }
    
}
