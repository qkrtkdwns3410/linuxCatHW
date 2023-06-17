package linux.sub.hw20230617_2;

import java.io.*;
import java.nio.charset.StandardCharsets;

/**
 * packageName    : linux.sub.hw20230617_2
 * fileName       : Q1
 * author         : ipeac
 * date           : 2023-06-17
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023-06-17        ipeac       최초 생성
 */
public class Q1 {
    public static void main(String[] args) throws IOException {
        String word = "안녕하세요 ㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋ";
        byte[] bytes = word.getBytes(StandardCharsets.UTF_8);
        InputStream is = new BufferedInputStream(new ByteArrayInputStream(bytes), 8192);
        OutputStream os = new BufferedOutputStream(System.out, 8192);
        while (true) {
            try {
                int len = is.read(bytes);
                if (len == -1) {
                    break;
                }
                os.write(bytes, 0, len);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            os.flush();
            
        }
    }
}
