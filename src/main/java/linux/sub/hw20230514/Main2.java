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
    public static void main(String[] args) {
        try (InputStream is = new FileInputStream("test.txt");
             BufferedInputStream bis = new BufferedInputStream(is, 8192);
             InputStreamReader isr = new InputStreamReader(bis, StandardCharsets.UTF_8);) {
            //이진데이터를 스트림에서 읽어오기위한 내부 버퍼가 필요하다
            char[] chars = new char[512];
            //스트리밍 처리를 진행한다.
            while (true) {
                int len = isr.read(chars);
                if (len == -1) {
                    break;
                }
                //char 자료형의 경우 이미 디코딩이 되어있는 상태이기에 그대로 출력하면된다.
                String pp = new String(chars, 0, len);
            }
        } catch (IOException e) {
        
        }
    }
}

