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
        final int BUFFER_SIZE = 8024;
        
        try (InputStreamReader isr = new InputStreamReader(new FileInputStream("123.txt"), StandardCharsets.UTF_8);
             OutputStreamWriter osw = new OutputStreamWriter(new FileOutputStream("sample.txt", true), StandardCharsets.UTF_8)) {
            StringBuilder sb = new StringBuilder();
            char[] buffer = new char[BUFFER_SIZE];
            while (true) {
                int len = isr.read(buffer);
                if (len == -1) {
                    break;
                }
                sb.append(buffer, 0, len);
                if (sb.length() >= BUFFER_SIZE) {
                    osw.write(sb.toString());
                    sb.setLength(0);
                }
            }
            if (sb.length() > 0) {
                osw.write(sb.toString());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        
    }
}
