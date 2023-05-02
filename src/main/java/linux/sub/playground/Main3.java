package linux.sub.playground;

import com.sun.source.tree.TryTree;

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
        * 자바에서는 String 클래스가 리터럴로 선언된 경우 문자열 상수 풀에, 객체로 선언된 경우 힙에 유니코드형식의 문자열을 저장합니다.
        * String 클래스로 선언된 변수 자체는 해당 풀 이나 힙에 저장된 데이터를 참조할 뿐입니다.
        * 해당 유니코드 형식의 문자열 값 그 자체를 변환하여 컴퓨터가 이해할 수 있는 이진 데이터로 즉시 변환핤 수 있다면 좋겠지만,
        * String 클래스에서는 제공하지 않고, 별도 byte배열을 새로 생성하여 반환하는 API 는 제공하고 있습니다.
        * */
        byte[] bword = word.getBytes(StandardCharsets.UTF_8);
        //이진데이터 자체는 메모리에 저장되어있기에 byteArraYIS 로 데이터 스트림을 처리합니다.
        InputStream is = new ByteArrayInputStream(bword);
        //스트리밍이란
        // 1 . 적절한 처리단위를 정하여
        byte[] bytes = new byte[4];
        //2. 조금씩 스트림을 처리하는 행위를 말합니다.
        while (true) {
            int len = is.read(bytes);
            boolean eol = len == -1;
            if (eol) {
                break;
            }
            //콘솔에 데이터를 출력할 것이기에 String 클래스로 변환이 필요합니다 + UTF8로 인코딩했기에 같은 방식으로 디코딩해야 올바른 문자열을 볼수잇씁니다.
            String s = new String(bytes, 0, len, StandardCharsets.UTF_8);
            System.out.print(s);
        }
    }
}