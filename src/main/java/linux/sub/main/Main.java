package linux.sub.main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.StringTokenizer;
import java.util.stream.Stream;

/**
 * packageName    : linux.sub.main
 * fileName       : Main
 * author         : ipeac
 * date           : 2023-03-31
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023-03-31        ipeac       최초 생성
 */
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader rd = new BufferedReader(new InputStreamReader(System.in)); //콘솔에 입력하기 위한  BufferReader 객체의 생성
        String words = "";
        while ((words = rd.readLine()) != null) { //\n 개행문자를 만날때까지 문자열을 읽어옵니다. 개행문자이후 읽어오는 데이터가 없으면 종료됩니다.
            StringTokenizer st = new StringTokenizer(words, " ");
            String function = st.nextToken();
            if (function.equals("cat")) {
                while (st.hasMoreTokens()) { // delim 값 을 만나면 포인터를 delim 이후까지 설정합니다. delim 값없이 개행되는경우
                    // 포인터의 위치가 문장의 최대 길이 이상으로 잡히기 때문에
                    // 그때부터는 false 반환하여 다음 단어가 없음을 알려주게됩니다.
                    findFile(st.nextToken()); //해당 파일을 탐색후 출력
                }
            } else {
                System.out.println("command not found:" + function);
                // 처음에는 프린트말고 별도 exception 클래스를 상속받은
                //커스텀 에러 클래스를 만드려고 했습니다.
                // 하지만 중간에 throws 를 통해 별도 커스텀 익셉션을 예외처리하였지만, 프로그램이 자꾸만 종료되었습니다
                // 이런 방식으로 짜려면 어떻게 해야하는건지 궁금합니다.
            }
        }
        
    }
    
    public static void findFile(String filePath) throws IOException {
        try {
            Path path = Paths.get(filePath);
            Stream<String> fileContents = Files.lines(path);
            fileContents.forEach(System.out::println);
            fileContents.close();
        } catch (NoSuchFileException nsfe) {
            System.out.println("");
        }
        
    }
    
}
