package linux.sub.playground;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

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
        //bufferInputStream
        String word = "dajsdjhajklsdhq 12 3jl1jljwelq qje lqwj elqjwle";
        //논리 데이터를 물리 데이터(이진데이터) 로 변환합니다.
        byte[] byteWord = word.getBytes(StandardCharsets.UTF_8);
        //물리 데이터에서 입력 스트림을 만듭니다.
        InputStream is = new ByteArrayInputStream(byteWord);
        //버퍼링된 입력스트림을 만듭니다. 8192 사이즈가 꽉찰때까지나 flush 혹은 close 가 호출될때까지 스트림을 유지합니다.
        InputStream bis = new BufferedInputStream(is, 8192);
        //스트리밍을 위해 일정한 단위를 정해서 스트림을 조금씩 처리
        // 입력스트림에서 데이터를 읽기 위한 버퍼사이즈입니다. 일정한 처리단위 선언
        byte[] bytes = new byte[4];
        //스트림을 조금씩 처리하기 행위 - while
        while (true) {
            //처리단위만큼 스트림에서 읽습니다.
            int len = bis.read(bytes);
            //-1 을 반환하는 경우 스트림이 닫힌 경우입니다 (eol)
            boolean eol = len == -1;
            if (eol) {
                break;
            }
            //버퍼에 이진코드가 들어가있으며
            //이진코드는 물리적인 데이터입니다,
            // 사람이 편하기 처리하기 위하여 이진코드(물리데이터)를 논리적 데이터의 대표격인 String 으로 변환합니다.
            String s = new String(bytes, 0, len, StandardCharsets.UTF_8);
            System.out.print(s);
        }
    }
}
