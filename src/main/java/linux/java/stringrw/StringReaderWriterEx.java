package linux.java.stringrw;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;

/**
 * packageName    : linux.java.stringrw
 * fileName       : StringReaderWriterEx
 * author         : ipeac
 * date           : 2023-04-13
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023-04-13        ipeac       최초 생성
 */
public class StringReaderWriterEx {
    public static void main(String[] args) throws IOException {
        String inputData = "한글한글";
        StringReader input = new StringReader(inputData);
        StringWriter output = new StringWriter();
        
        int data = 0;
        try {
            while ((data = input.read()) != -1) {
                output.write(data); // void write(int b)
            }
        } catch (IOException e) {
        
        }
        System.out.println("inputData = " + inputData);
        //String toString() => StringWriter || StringBuffer 에 저장된 문자열을 반환합니다.
        System.out.println("output.toString() = " + output.toString());
        //StringBuffer getBuffer() => StringWriter 에 출력한 데이터가 저장된 StringBuffer를 반환합니다.
//        System.out.println("output.getBuffer().toString() = " + output.getBuffer()
//                                                                        .toString());
    }
}
