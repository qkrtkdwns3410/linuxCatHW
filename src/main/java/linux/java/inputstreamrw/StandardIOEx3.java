package linux.java.inputstreamrw;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

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
public class StandardIOEx3 {
    public static void main(String[] args) throws IOException {
        String word = "hello world!";
        // 자바에서는 유니코드 형식의 이진데이터로 처리하고있는데..
        // String 클래스내부에서는 별도로 유니코드화된 이진데이터를 사용해서 처리하고있음.
        // 하지만 String 클래스 내부 API 는 해당 유니코드화된 이진데이터를 참조하여 반환해주는 방식을 사용하고 있지않음.
        // 하지만 우리는 이진데이터 그 자체를 필요로 하기 때문에 일단, String 클래스에서 제공하는 getBytes API 를 통해 특정 인코딩방식으로 해석된 이진데이터를 들고오는 것임.
        
        byte[] bword = word.getBytes("EUC-KR");
        // 이진데이터는 메모리에 저장되어있기때문에 ByteAr... 스트림을 선언
        InputStream is = new ByteArrayInputStream(bword);
        //스트리밍
        //1) 적절한 처리단위를 정하여
        byte[] bytes = new byte[4];
        //2) 조금씩 스트림을 처리한다
        while (true) {
            int len = is.read(bytes);
            boolean eol = len == -1;
            if (eol) {
                break;
            }
            // 난 처리를 sop 로 콘솔에 출력할거임
            // sop은 String 를 필요로함 그렇기에 난 byte 배열을 String으로 변환할거임
            String s = new String(bytes, 0, len, "EUC-KR");
            System.out.print(s);
        }
        
        
    }
    
}
