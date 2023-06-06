package linux.sub.hw20230602;

import java.io.*;
import java.nio.charset.StandardCharsets;

/**
 * packageName    : linux.sub.hw20230602
 * fileName       : Main2
 * author         : ipeac
 * date           : 2023-06-02
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023-06-02        ipeac       최초 생성
 */
public class Main2 {
    public static void main(String[] args) {
        String word = "akjdlasjld kaljdslj asldj as";
//        byte[] bword = word.getBytes(StandardCharsets.UTF_8);
        
        try (OutputStream os = new FileOutputStream("123.txt");
             BufferedOutputStream bos = new BufferedOutputStream(os, 8192);
             OutputStreamWriter osw = new OutputStreamWriter(bos, StandardCharsets.UTF_8);
        ) {
            osw.write(word);
            osw.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void print() {
    }
    
    public static class MyResource implements Closeable {
        
        @Override
        public void close() throws IOException {
        
        }
    }
}
