package linux.sub.playground;

import java.io.BufferedInputStream;
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
        //논리적 데이터를 물리적 데이터로 변환합니다.
        byte[] bword = word.getBytes(StandardCharsets.UTF_8);
        //물리적 데이터에 스트림을 연결합니다.
        InputStream is = new ByteArrayInputStream(bword);
        //버퍼링된 보조스트림 연결, 8192 사이즈가 꽉찰때 flush 혹은 close 호출시까지 스트림을 유지합니다. => 이 보조스트림은 기반 스트림의 버퍼의 개념과 다릅니다.
        InputStream bis = new BufferedInputStream(is);
        //스트리밍
        //1) 적절한 처리단위를 정하여
        byte[] bytes = new byte[4];
        //2) 조금씩 스트림처리
        while (true) {
            //처리단위만큼 버퍼에 씀
            int len = bis.read(bytes);
            boolean eol = len == -1;
            if (eol) {
                break;
            }
            //물리 -> 논
            String s = new String(bytes, 0, len, StandardCharsets.UTF_8);
            System.out.print(s);
        }
    }
}
