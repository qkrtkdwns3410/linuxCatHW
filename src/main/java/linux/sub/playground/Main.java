package linux.sub.playground;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

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
        String word = "jlasdalsd lajdlajsdlajld ";
        //String 내부적으로 리티럴형식은 힙이 아니라 문자열 상수풀이라는 특별한 공간에 저장됨.
        // 이 문자열 상수풀에서는 저장된 문자 유니코드형식의 이진데이터를 직접적으로 사용하는 것이 아니라
        // 주소를 참조해서 사용하며, 같은 값인 경우 재사용을 하게되빈다 (TMI)
        // String 클래스에서는 byte배열의 참조를 제공해주는 API 자체를 지원하지 않습니다.
        //하지만, byte 배열 자체를 생성해주는 API는 제공해주고있죠.
        //그렇기에 우리는 메모리의 낭비는 존재하긴하지만,, byte배열 자체를 생성해주는 API를 사용해 별도의 byte 배열을 생성해야합니다.
        byte[] bword = word.getBytes(StandardCharsets.UTF_8);
        //byte배열은 메모리에 위치하게되기에 byteArrayInputStream 을 사용하여 스트림을 처리하겠습니다.
        InputStream is = new ByteArrayInputStream(bword);
        //스트리밍은 1. 적절한 처리단위를 정하여 2. 조금씩 스트림을 처리한다는 의미입니다.
        //그렇기에 우리는 적절한 처리단위를 정해주겠습니다.
        byte[] bytes = new byte[4];
        //또한 스트림을 조금씩 처리해볼까여
        while (true) {
            int len = is.read(bytes);
            boolean eol = len == -1;
            if (eol) {
                break;
            }
            //스트림으로 입력받은 이진데이터를 System.out 을 통해 출력해줄것이기때문에 String 으로 변환합니다.
            // 또한 인코딩을 UTF-8 방식으로 했기때문에 디코딩도 UTF-8 방식으로 해줘야한다는 점.!
            String s = new String(bytes, 0, len, StandardCharsets.UTF_8);
            System.out.print(s);
        }
    }
    
}
