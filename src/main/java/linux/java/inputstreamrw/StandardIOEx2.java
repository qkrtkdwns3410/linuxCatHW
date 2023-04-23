package linux.java.inputstreamrw;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import static java.nio.charset.StandardCharsets.UTF_8;

/**
 * packageName    : linux.java.inputstreamrw
 * fileName       : StandardIOEx2
 * author         : ipeac
 * date           : 2023-04-21
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023-04-21        ipeac       최초 생성
 */
public class StandardIOEx2 {
    public static void main(String[] args) throws IOException {
        String word = "hello world!!";
        // 자바 내부적으로 String 클래스는 리터럴로 선언시 문자열상수풀에 저장됨.
        // 문자열 상수풀에서는 HashMap 형태로 해당 문자가 이진데이터 형식으로 저장됨.
        // 동일한 키값을 검색을 성공하면 해당 문자열 객체의 주소 반환
        // 반환된 유니코드의 형식의 이진데이터를 byte 배열로 변환해주는 API를 제공하고있지않음.
        // 그래서 유니코드 형식의 이진데이터를 어떠한 방식으로 인코딩해야한다는 것을 알려주는 별도의
        // charset을 적어주고 별도의 byte 배열을 새로 만들어줘야함
        byte[] bword = word.getBytes(UTF_8);
        //해당 이진데이터는 메모리에 기록되어있기에 byteArrayInputStream 으로 입력스트림 처리를 해줘야함
        InputStream is = new ByteArrayInputStream(bword);
        //스트리밍이란 - 1. 적절한 처리단위를 정하여 2. 조금씩 스트림을 처리하는 행위를 말함.
        //그렇기에 1 .일단 적절한 처리단위를 정해줘야하는데 이진데이터를 받아야하기때문에 byte 배열 선언함
        byte[] bytes = new byte[4];
        //처리단위를 정했으면 조금씩 스트림을 처리해보자
        while (true) {
            int len = is.read(bytes);
            boolean eol = len == -1;
            if (eol) {
                break;
            }
            //난 system.out 함수로 String 을 출력할 예정이기에때문에 이진데이터를 String 클래스로 변환합니다.
            // 또한 UTF-8 방식의 이진데이터로 인코딩했기에 UTF-8 방식으로 디코딩해야합니다.
            String s = new String(bytes, 0, len, UTF_8);
            System.out.print(s);
        }
    }
    
}
