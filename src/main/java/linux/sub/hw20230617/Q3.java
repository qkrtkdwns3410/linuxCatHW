package linux.sub.hw20230617;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class Q3 {
    public static void main(String[] args) {
        String word = "안녕하세요";
        byte[] bword = word.getBytes(StandardCharsets.UTF_8);
        
        try (
                InputStreamReader isr = new InputStreamReader(new BufferedInputStream(new ByteArrayInputStream(bword), 8192), StandardCharsets.UTF_8);
                OutputStreamWriter osw = new OutputStreamWriter(new BufferedOutputStream(new FileOutputStream("test1.txt"), 8192), "EUC-KR")
        ) {
            char[] chars = new char[512];
            while (true) {
                int len = isr.read(chars);
                if (len == -1) {
                    break;
                }
                osw.write(chars, 0, len);
            }
            osw.flush();
        } catch (IOException e) {
        
        }
        try (
                InputStreamReader isr = new InputStreamReader(new BufferedInputStream(new FileInputStream("test1.txt"), 8192), "EUC-KR");
                OutputStreamWriter osw = new OutputStreamWriter(new BufferedOutputStream(new FileOutputStream("test2.txt"), 8192), StandardCharsets.UTF_8)
        ) {
            char[] chars = new char[512];
            while (true) {
                int len = isr.read(chars);
                if (len == -1) {
                    break;
                }
                osw.write(chars, 0, len);
            }
            osw.flush();
        } catch (IOException e) {
        
        }
    }
}
