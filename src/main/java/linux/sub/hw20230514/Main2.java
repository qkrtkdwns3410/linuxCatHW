package linux.sub.hw20230514;

import java.io.*;
import java.nio.charset.StandardCharsets;

/**
 * packageName    : linux.sub.hw20230514
 * fileName       : Main2
 * author         : ipeac
 * date           : 2023-05-27
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023-05-27        ipeac       최초 생성
 */
public class Main2 {
    public static void main(String[] args) {
        String word = "akdklsa lajdals dljals";
        byte[] bword = word.getBytes(StandardCharsets.UTF_8);
        
        try (InputStream is = new ByteArrayInputStream(bword);
             BufferedInputStream bis = new BufferedInputStream(is, 8192);
             InputStreamReader isr = new InputStreamReader(bis, StandardCharsets.UTF_8);) {
            char[] chars = new char[4];
            while (true) {
                int len = isr.read(chars);
                if (len == -1) {
                    break;
                }
                String str = new String(chars, 0, len);
                System.out.print(str);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        
    }
    

}

