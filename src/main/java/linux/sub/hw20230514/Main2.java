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
        //현재는 사람이 이해할 수 있는 문자열 형태이다.
        String word = "akdklsa lajdals dljals";
        byte[] bword = word.getBytes(StandardCharsets.UTF_8);
        /*
         인코딩 형식으로 UTF-8 로 인코딩을 수행할것이며, 향후에도 올바른 문자열로 출력하려면 동일한 디코딩 방식을 유지해야함을 알 수 있다
         InputStreamReader 를 사용하여 인코딩과 디코딩의 책임을 일원화해보도록 한다.
         */
        InputStream is = new ByteArrayInputStream(bword);
        Reader isr = new InputStreamReader(is, StandardCharsets.UTF_8);
        char[] chars = new char[1024];
        while (true) {
            int len = isr.read(chars);
            if (len == -1) {
                break;
            }
            String str = new String(chars, 0, len);
            
            //디코딩시 별도 디코딩타입의 선언이 필요없이 출력이 가능하다, 왜냐고?
            // 이미 디코딩된 값을 char 배열에 담기때문이다.
            System.out.print(str);
        }
    }
}
