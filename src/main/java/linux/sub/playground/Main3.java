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
        String word = "1230128 0198 23012830ojdaldjl adslk ajsdl";
        //자바에서 Stirng은 유니코드 형식의 이진데이터로 메모리에 저장되어있습니다.
        // 하지만 String 에서는 해당 이진데이터의 주소를 참조하여 바로 byte배열로 반환하는 API를 제공하고 있지않고,
        // String 클래스는 해당 이진데이터를 복사하여 새로운 byte 배열을 반환받도록하는 API는 제공하고있습니다.
        byte[] bword = word.getBytes(StandardCharsets.UTF_8);
        //bword는 메모리에 올라가있는 데이터이기에 byteArrrayStr i 을 사용하여 입력스트림을 처리합니다.
        InputStream is = new ByteArrayInputStream(bword);
        //스트리밍이란 1
        // 적절한 처리단위를 정하여
        byte[] bytes = new byte[4];
        // 조금씩 스트림을 처리한다.
        while (true) {
            int len = is.read(bytes);
            boolean eol = len == -1;
            if (eol) {
                break;
            }
        }
    }
}