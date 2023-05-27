package linux.sub.hw20230514;

import java.io.*;
import java.nio.charset.StandardCharsets;

/**
 * packageName    : linux.sub.hw20230514
 * fileName       : Main2
 * author         : ipeac
 * date           : 2023-05-27
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023-05-27        ipeac       최초 생성
 */
public class Main2 {
    public static void main(String[] args) throws IOException {
        String word = "laksdjlasdj klajldjd jalskjadljdljasdljas";
        
        byte[] bword = word.getBytes(StandardCharsets.UTF_8);
        
        InputStream is = new ByteArrayInputStream(bword);
        byte[] buffer = new byte[8];
        
        while (true) {
            int len = is.read(buffer);
            if (len == -1) {
                break;
            }
            
            String str = new String(buffer, 0, len, StandardCharsets.UTF_8);
            System.out.println(str);
        }
        
        InputStreamReader isr = new InputStreamReader(is, StandardCharsets.UTF_8);
        char[] cBuffer = new char[2];
        while (true) {
            int len = isr.read(cBuffer);
            if (len == -1) {
                break;
            }
            String str = new String(cBuffer, 0, len);
            System.out.println(str);
        }
    }
}
