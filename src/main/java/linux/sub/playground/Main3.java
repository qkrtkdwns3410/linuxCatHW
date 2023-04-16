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
        String word = "asdjlaj alsdjal jd asljd la";
        //논리적 데이터를 물리적 데이터로 변환합니다.
        byte[] bword = word.getBytes(StandardCharsets.UTF_8);
        //물리적 데이터가 메모리에 존재하기에 , 메모리 전용 스트림 생성
        InputStream is = new ByteArrayInputStream(bword);
        //스트리밍
        // 1) 적절한 처리단위를 가지고
        byte[] bytes = new byte[4];
        // 2) 연속적인 스트림 처리합니다.
        while (true) {
            //처리단위 크기만큼 스트림을 받습니다.
            int len = is.read(bytes);
            // -1 을 반환하는 경우 연속적인 처리를 종료해야합니다.
            if (len == -1) {
                break;
            }
            //물리적 데이터를 논리적 데이터로 변환합니다.
            String s = new String(bytes, 0, len, StandardCharsets.UTF_8);
            System.out.print(s);
        }
        
    }
}
