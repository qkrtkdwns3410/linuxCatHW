package linux.sub.hw20230514;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * packageName    : linux.sub.hw20230514
 * fileName       : Main
 * author         : ipeac
 * date           : 2023-05-14
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023-05-14        ipeac       최초 생성
 */
public class Main {
    /*
     고려 요구사항
     1. 문자열 데이터 읽기
     2. 파일 쓰기
     3. 파일 읽기
     4. 콘솔 출력
     */
    public static void main(String[] args) throws IOException {
        
        final String OUPUT_INPUT_FILENAME = "input.txt";
        
        try {
            //처음에는 그냥 String 만 받아서 처리해도 되지 않을까 라는
            // 생각을 했습니다.
            // 하지만, 혹여나 중간에 로직의 변경으로 사용자 검증이 필요한 경우가 추가
            // 될 가능성이 있기때문에 별도 클래스로 생성했습니다.
            
            //시스템 검증 자체가 필요없는 구조라고 생각하면 솔직히 String 으로 보내도 무관할 것 같습니다.
            String word = "HelloWorld";
            Input input = Input.from(word);
            Path path = Paths.get(OUPUT_INPUT_FILENAME);
            
            PathAndInput pathAndInput = new PathAndInput(input, path);
            //추상화할 부분이 없어보인다. input 과 output스트림모두 반대 행위임
            pathAndInput.writeInput();
            String returnedResult = pathAndInput.readInput();
            
            System.out.println(returnedResult);
            
        } catch (RuntimeException e) {
            e.printStackTrace();
        }
        
    }
    
}
