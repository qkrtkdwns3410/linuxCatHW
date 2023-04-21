package linux.java.inputstreamrw;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * packageName    : linux.java.inputstreamrw
 * fileName       : InputStreamReaderEx
 * author         : ipeac
 * date           : 2023-04-17
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023-04-17        ipeac       최초 생성
 */
public class InputStreamReaderEx {
    public static void main(String[] args) throws IOException {
        String line = "";
        InputStreamReader isr = new InputStreamReader(System.in);
        //문자 보조 스트림과 메모리기반 입력을 이어주기위하여 InputStreamReader 사용함
        //JDK 5 부터 Scanner 가 추가되었다고함..
        BufferedReader br = new BufferedReader(isr);//문자열 기반 보조 입력 스트림
        
        System.out.println("사용중인 OS의 인코딩 :" + isr.getEncoding());
        
        do {
            System.out.print("문장을 입력하세요. 마치시려면 q를 입력하세요 >> ");
            //사용자의 화면입력을 라인단위로 입력받습니다.
            line = br.readLine();
            System.out.println("입력하신 문장 :" + line);
        } while (!line.equalsIgnoreCase("q"));
    }
}
