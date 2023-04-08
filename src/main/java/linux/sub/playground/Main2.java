package linux.sub.playground;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.StringTokenizer;

/**
 * packageName    : linux.sub.playground
 * fileName       : Main2
 * author         : ipeac
 * date           : 2023-04-08
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023-04-08        ipeac       최초 생성
 */
public class Main2 {
    public static void main(String[] args) throws IOException {
        String word = "hello1 123123";
        byte[] wordByte = word.getBytes(StandardCharsets.UTF_8);
        InputStream is = new ByteArrayInputStream(wordByte);
        InputStream bis = new BufferedInputStream(is, 8192);
        byte[] bytes = new byte[4];
        
        while (true) {
            int len = bis.read(bytes);
            if (len == -1) {
                break;
            }
            String s = new String(bytes, 0, len, StandardCharsets.UTF_8);
            System.out.print(s);
        }
        
    }
}
