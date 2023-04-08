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
        String word = "hello hello hello hello hello hello";
        //논리적인 데이터를 물리적인 데이터로 변환합니다.
        byte[] wordByte = word.getBytes(StandardCharsets.UTF_8);
        //물리적인 데이터를 스트림으로
        InputStream is = new ByteArrayInputStream(wordByte);
        //스트리밍 -  일정한 처리단위를 통하여 연속적인 처리
        //1) 일정한 처리단위가 필요합니다
        byte[] bytes = new byte[4];
        //2) 연속적인 처리가 필요합니다.
        while (true) {
            int len = is.read(bytes);
            //입력스트림이 닫힌 경우 연속적인 처리를 종료해야합니다.
            if (len == -1) {
                break;
            }
            //입력받은 물리적인 데이터를 논리적인 데이터로 변환합니다
            String s = new String(bytes, 0, len, StandardCharsets.UTF_8);
            System.out.print(s);
        }
    }
}
