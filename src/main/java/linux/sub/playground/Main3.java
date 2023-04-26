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
        String word = "asdjlajsdl alsdjlaj ldasljadlasjd laksjd";
        //word 는 내부적으로 유니코드 문자열로 데이터가 저장되어있으며 해당 데이터의 주소값을 참조합니다.
        //스트림을 사용하기 위하여는 이진데이터가 필요합니다.
        // 하지만.. String 클래스에서는 문자데이터의 주소를 참조함과 동시에 해당 유니코드형식 문자열를 이진데이터로 즉시 변환해주는 API를 제공하고 있지 않습니다.
        // 문자데이터를 토대로 데이터를 바이트 배열을 반환하는 API 는 존재하기에 해당 API를 사용합니다.
        byte[] bword = word.getBytes(StandardCharsets.UTF_8);
        // 해당 이진데이터는 메모리에 저장되어있기에 ByteArrayInputStream을 사용합니다.
        InputStream is = new ByteArrayInputStream(bword);
        //스트리밍은
        //1) 적절한 처리단위를 정하여
        byte[] bytes = new byte[4];
        //2)조금씩 스트림을 처리하는 행위입니다.
        while (true) {
            int len = is.read(bytes);
            boolean eol = len == -1;
            if (eol) {
                break;
            }
            //저는 콘솔에 입력받은 데이터를 출력할 것이기 때문에 이진데이터를 문자데이터로 변환할겁니다.
            //디코딩은 인코딩방식과 동일해야합니다 ^^
            String s = new String(bytes, 0, len, StandardCharsets.UTF_8);
            System.out.print(s);
        }
    }
}
