package linux.java.ex;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

/**
 * packageName    : linux.java.ex
 * fileName       : ImageToBinary
 * author         : ipeac
 * date           : 2023-05-28
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023-05-28        ipeac       최초 생성
 */
public class WordEncodingDecoding {
    public static void main(String[] args) throws IOException {
        String word = "한글입니다 ㅋ 인코딩해보세요";
        byte[] bword = word.getBytes(StandardCharsets.UTF_8);
        System.out.println("bword = " + Arrays.toString(bword));
        byte[] bword2 = word.getBytes(StandardCharsets.UTF_16);
        System.out.println("bword2 = " + Arrays.toString(bword2));
        InputStreamReader isr = new InputStreamReader(new ByteArrayInputStream(bword), StandardCharsets.UTF_8);
        
        char[] buffer1 = new char[1];
        while (true) {
            int len = isr.read(buffer1);
            if (len == -1) {
                break;
            }
            System.out.println(new String(buffer1, 0, len));
        }
        
    }
}
