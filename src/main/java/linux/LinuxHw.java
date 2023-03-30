package linux;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * packageName    : linux
 * fileName       : LinuxHw
 * author         : ipeac
 * date           : 2023-03-30
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023-03-30        ipeac       최초 생성
 */
public class LinuxHw {
    
    public static void main(String[] args) throws IOException {
        InputStream is = openConsoleInputStream(); // Inputstream 선언
        OutputStream os = System.out;
        byte[] bytes = new byte[4]; // 4바이트단위로 각각 받도록 하겠습니다.
        while (true) {
            int len = is.read(bytes);
            if (len == -1) {
                break;
            }
            os.write(bytes, 0, len);
        }
        
    }
    
    private static InputStream openConsoleInputStream() {
        return System.in;
    }
    
}
