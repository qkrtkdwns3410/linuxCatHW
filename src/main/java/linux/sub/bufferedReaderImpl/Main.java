package linux.sub.bufferedReaderImpl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

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
        String s1 = br.readLine();
        System.out.println("s1 = " + s1);
    
        CustomBufferedReader cbr = new CustomBufferedReader();
    
        String s2 = cbr.readLine();
        System.out.println("s2 = " + s2);
    }
    
}

class CustomBufferedReader {
    public  String readLine() throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            StringBuilder sb = new StringBuilder();
            char[] buffer = new char[5];
            while (true) {
                int len = br.read(buffer);
                if (len == -1) {
                    break;
                }
                String w = new String(buffer, 0, len);
                int index = w.indexOf("\n");
                if (index != -1) {
                    sb.append(buffer, 0, index);
                    break;
                    
                } else {
                    sb.append(buffer, 0, len);
                }
            }
            return sb.toString();
        }
    }
}
