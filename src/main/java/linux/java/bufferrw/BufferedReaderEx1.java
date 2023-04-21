package linux.java.bufferrw;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * packageName    : linux.java.bufferrw
 * fileName       : BufferedReaderEx1
 * author         : ipeac
 * date           : 2023-04-13
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023-04-13        ipeac       최초 생성
 */
public class BufferedReaderEx1 {
    public static void main(String[] args) throws IOException {
        FileReader fr = new FileReader("src/main/java/linux/java/bufferrw/BufferedReaderEx1.java");
        BufferedReader br = new BufferedReader(fr);
        //bufferedReader는 내부에 char[]  버퍼를 형성하며 , 최초 버퍼의 크기는 8192 이며, 생성자에서 값을 지정가능함.
        //readLine() 은 메서드 호출시마다 내부 버퍼에서 한줄씩 데이터를 읽습니다. \n 등을 만날때까지나 버퍼가 꽉찰때까지 등등..
        String line = "";
        for (int i = 1; (line = br.readLine()) != null; i++) {
            //"; " 을 포함한 라인 출력
            if (line.indexOf(";") != -1) {
                //sop 는 표준 출력 스트림을 나태는 객체며.
                // sop 는 메서드는 프로그램 실행시 자동으로 생성되는 스트림임.
                // sop 는 버퍼를 가지고있으며, 버퍼가 꽉찬다면 자동으로 출력버퍼를 비움.
                System.out.println(i + " " + line);
            }
        }
    }
}
