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
        String word = "dkajldjlajd lajdlaj ldjaslj dlaj dal djlaskjdal jlas";
        //논리적 데이터를 물리적데이터로 변환
        byte[] bword = word.getBytes(StandardCharsets.UTF_8);
        //물리적 데이터를 가 메모리에 존재하기에 입력받기위해 입력스트림선언
        InputStream is = new ByteArrayInputStream(bword);
        //스트리밍이란
        //1) 적절한 처리단위를 정하여
        byte[] bytes = new byte[4];
        //2) 조금씩 스트림을 처리하는 행위
        while (true) {
            //처리단위만큼 입력스트림을 받습니다.
            int len = is.read(bytes);
            //-1 을 반환한다면 스트림의 종료를 의미합니다.
            boolean eol = len == -1;
            if (eol) {
                break;
            }
            //물리적 데이터를 논리적 데이터로 변환하여 콘솔에 출력
            String s = new String(bytes, 0, len, StandardCharsets.UTF_8);
            System.out.print(s);
        }
    }
}
