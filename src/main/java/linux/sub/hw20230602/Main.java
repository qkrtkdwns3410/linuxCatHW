package linux.sub.hw20230602;

import java.io.*;
import java.nio.charset.StandardCharsets;

/**
 * packageName    : linux.sub.hw20230602
 * fileName       : Main
 * author         : ipeac
 * date           : 2023-06-02
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023-06-02        ipeac       최초 생성
 */
public class Main {
    public static void main(String[] args) throws IOException {
        //메모리 -> 파일a 저장 -> 다른 파일b 저장
        String word = "adjkaskljd aljdlaj sdlajd alda";
        byte[] bword = word.getBytes(StandardCharsets.UTF_8);
        char[] buffer = new char[100];
        try (ByteArrayInputStream bs = new ByteArrayInputStream(bword);
             BufferedInputStream bis = new BufferedInputStream(bs, 8192);
             InputStreamReader isr = new InputStreamReader(bis, StandardCharsets.UTF_8);
             OutputStreamWriter osw = new OutputStreamWriter(new FileOutputStream("input.txt"), StandardCharsets.UTF_8)
        ) {
            while (true) {
                int len = isr.read(buffer);
                if (len == -1) {
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
}
