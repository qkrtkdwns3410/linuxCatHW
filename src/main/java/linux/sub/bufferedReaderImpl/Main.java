package linux.sub.bufferedReaderImpl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;

/**
 * packageName    : linux.sub.bufferedReaderImpl
 * fileName       : Main
 * author         : ipeac
 * date           : 2023-04-04
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023-04-04        ipeac       최초 생성
 */
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        String s1 = br.readLine();
//        System.out.println("s1 = " + s1);
//        String s2 = br.readLine();
//        System.out.println("s2 = " + s2);
        
        CustomBufferedReader cbr = new CustomBufferedReader(new InputStreamReader(System.in));
        
        String s3 = cbr.readLine();
        System.out.println("s3 = " + s3);
        String s4 = cbr.readLine();
        System.out.println("s4 = " + s4);
    }
    
}

class CustomBufferedReader {
    private Reader in;
    private char[] chars;
    
    private int leftOverLen;
    
    private static int DEFAULT_BUFFER_SIZE = 5;
    
    public CustomBufferedReader(Reader in) {
        this(in, DEFAULT_BUFFER_SIZE);
    }
    
    public CustomBufferedReader(Reader in, int bs) {
        this.in = in;
        chars = new char[bs];
        leftOverLen = 0;
    }
    
    public String readLine() throws IOException {
        StringBuilder sb = new StringBuilder();
        if (leftOverLen > 0) { // 이전에 남은 값을  Stringbuilder 에 담아줍니다,
            sb.append(chars, 0, leftOverLen);
        }
        while (true) {
            int len = in.read(chars);
            if (len == -1) {
                break;
                
            }
            String w = new String(chars, 0, len);
            int index = w.indexOf("\n"); // index가 \n 을 만난다면 해당 인덱스까지만 출력을 해야합니다.
            if (index != -1) {
                sb.append(chars, 0, index);
                leftOverLen = len - index - 1;
                break;
                
            } else {
                sb.append(chars, 0, len);
            }
        }
        return sb.toString();
    }
}
