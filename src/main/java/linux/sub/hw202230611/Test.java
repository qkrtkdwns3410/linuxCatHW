package linux.sub.hw202230611;

import java.io.*;
import java.nio.charset.StandardCharsets;

/**
 * packageName    : linux.sub.hw202230611
 * fileName       : Test
 * author         : ipeac
 * date           : 2023-06-17
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023-06-17        ipeac       최초 생성
 */
public class Test {
    public static void main(String[] args) {
        String word = "HelloWorld";
        byte[] bytes = word.getBytes(StandardCharsets.UTF_8);
        OutputStream os = new BufferedOutputStream(System.out, 8192);
        try (
                InputStream is1 = new BufferedInputStream(new FileInputStream("test1.txt"), 8192);
                InputStream is2 = new BufferedInputStream(new FileInputStream("test2.txt"), 8192);
                InputStream is3 = new BufferedInputStream(new FileInputStream("test3.txt"), 8192);
                InputStream sequence = new SequenceInputStream(is1, new SequenceInputStream(is2, is3))
        ) {
            byte[] buffer = new byte[1024];
            while (true) {
                int len = sequence.read(buffer);
                if (len == -1) {
                    break;
                }
                os.write(buffer, 0, len);
            }
            os.flush();
        } catch (IOException e) {
        
        }
    }
}
