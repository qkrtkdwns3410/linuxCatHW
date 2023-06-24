package linux.sub.hw20230624;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class Q1 {
    public static void main(String[] args) {
        String word = "HelloWorld";
        byte[] bword = word.getBytes(StandardCharsets.UTF_8);
        
        byte[] bytes = new byte[1024];
        
        try (
                InputStream is = new BufferedInputStream(new ByteArrayInputStream(bword), 8192);
                OutputStream os = new BufferedOutputStream(new FileOutputStream("test1.txt"), 8192);
        ) {
            while (true) {
                int len = is.read(bytes);
                if (len == -1) {
                    break;
                }
                os.write(bytes, 0, len);
            }
            os.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        OutputStream os = new BufferedOutputStream(System.out, 8192);
        try (
                InputStream is = new BufferedInputStream(new FileInputStream("test1.txt"), 8192);
        ) {
            while (true) {
                int len = is.read(bytes);
                if (len == -1) {
                    break;
                }
                os.write(bytes, 0, len);
            }
            os.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
