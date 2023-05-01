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
        String word = "jdjas alsdklaj ldjlaj asljd a";
        //String 클래스는 문자열을 리터럴형식으로 저장하는 경우 문자열 상수풀에 해당 데이터를 유니코드형식의 문자열로 저장하게됩니다.
        // word 변수는 유니코드형식의 문자열의 주소를 참조하게되며, 해당 참조된 데이터를 즉시 이진데이터로 변환해주는 API가 존재한다면  사용하겠지만, 자바에서는 해당 API를 제공해주고 있지 않습니다.
        // 그렇기에 별도 해당 문자열을 순회하며 byte배열로 변환해주는 별도 API를 사용해서 이진데이터로 변환합니다
        byte[] bword = word.getBytes(StandardCharsets.UTF_8);
        //이진데이터는 byte 형식으로 메모리에 저장되어있기에 byteArrrayInputStream 으로 스트림을 처리합니다.
        InputStream is = new ByteArrayInputStream(bword);
        //스트리밍이란 무엇일까요?
        // 1. 적절한 처리단위를 정해서
        byte[] bytes = new byte[4];
        //2. 조금씩 스트림을 처리하는 행위를 말합니다.
        while (true) {
            int len = is.read(bytes);
            boolean eol = len == -1;
            if (eol) {
                break;
            }
            //우리는 콘솔에 입력받은 이진데이터를 출력할 예정입니다. System.out.println 에서 String 데이터를 요구하기에 우리가 처음에 인코딩한 방식대로 디코딩해서 출력해줘야합니다.
            String s = new String(bytes, 0, len, StandardCharsets.UTF_8);
            System.out.print(s);
        }
    }
}