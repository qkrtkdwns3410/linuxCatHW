package linux.sub.playground;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

/**
 * packageName    : linux.sub.playground
 * fileName       : Main3
 * author         : ipeac
 * date           : 2023-04-09
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023-04-09        ipeac       최초 생성
 */
public class Main3 {
    public static void main(String[] args) throws IOException {
        String word = "djadka alskdla alsdkals ";
        byte[] wordByte = word.getBytes(StandardCharsets.UTF_8);
        InputStream is = new ByteArrayInputStream(wordByte);
        byte[] buffer = new byte[4];
        while (true) {
            int len = is.read(buffer);
            if (len == -1) {
                break;
            }
            String s = new String(buffer, 0, len, StandardCharsets.UTF_8);
            System.out.print(s);
        }
    }
}
