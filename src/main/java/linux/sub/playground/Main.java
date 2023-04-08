package linux.sub.playground;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.StringTokenizer;

/**
 * packageName    : linux.sub.playground
 * fileName       : Main
 * author         : ipeac
 * date           : 2023-04-08
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023-04-08        ipeac       최초 생성
 */
public class Main {
    public static void main(String[] args) throws IOException {
        //논리적인 데이터 입니다. 해당 데이터를 컴퓨터에서 이해할수있도록 물리적 데이터로 변환해야합니다.
        String word = "hello world!";
        //논리적인 데이터를 물리적인 데이터로 변환합니다.
        // 인코딩의 경우 **변환법칙**이 필요하기에 매개변수에 인코딩방식을 별도 정의해줍니다.
        byte[] byteWord = word.getBytes(StandardCharsets.UTF_8);
        // 변환된 물리적데이터가 메모리에 존재하기에 , 메모리 전용 스트림인 ByteArray .. 을 만들어줬습니다.
        InputStream is = new ByteArrayInputStream(byteWord);
        //구성된 InputStream 에 대한 스트리밍 처리를 합니다.
        //스트리밍은 1) 일정한 처리단위를 정하여 연속적인 처리를 하는 행위를 의미하기에
        //처리단위에 대한 정의가 필요합니다.
        byte[] buffer = new byte[2];
        //연속적인 스트림 처리를 해야합니다.
        while (true) {
            //처리단위에 기반하여 스트림을 받습니다.
            int len = is.read(buffer);
            //스트림이 끝난 경우 -1 을 반환합니다
            // -1 을 반환받은 경우 연속적인 처리를 종료해야합니다.
            if (len == -1) {
                break;
            }
            //물리적인 데이터를 논리적인 데이터로 변환합니다
            String s = new String(buffer, 0, len, StandardCharsets.UTF_8);
            System.out.print(s);
        }
    }
}
