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
        String word = "ajsld alksdj lajsd jaldsj lasd";
        /*
        * 자바에서는 String 클래스에서 new 선언은 힙 , 리터럴 ( " " ) 은 문자열 상수풀에 유니코드형식의 문자열로 값을 저장하고, 해당 값을 word 변수가 참조해서 사용합니다.
        * String 클래스 자체에서는 해당 문자열의 참조한 값을 복사하여 byte 배열로 리턴해주는 방식의 API만 제공하고 있습니다.
        * 그렇기에 별도의 메모리 공간의 할당하여 새로운 byre 배열을 선언해야합니다.
        * */
        byte[] bword = word.getBytes(StandardCharsets.UTF_8);
        //byte 배열은 메모리에 올라가있습니다. byteArris.. 로 스트리밍 처리를 해야합니다.
        InputStream is = new ByteArrayInputStream(bword);

        //스트리밍이란
        //1 .적절한 처리단위를 정하여
        byte[] bytes = new byte[4];
        //2. 조금씩 스트림을 처리하는 행위를 말합니다.
        while (true) {
            int len = is.read(bytes);
            boolean eol = len == -1;
            if (eol) {
                break;
            }
            //콘솔에 데이터를 출력해서 사용자에게 보이게 할것이기에 sys.out 에서 요하는 String으로 변환
            String s = new String(bytes, 0, len, StandardCharsets.UTF_8);
            System.out.print(s);
        }
    }
}