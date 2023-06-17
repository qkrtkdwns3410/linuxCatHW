package linux.sub.hw20230617;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class Q1 {
    public static void main(String[] args) {
        String word = "HelloWorld";
        byte[] bword = word.getBytes(StandardCharsets.UTF_8);
        
        try (
                BufferedInputStream bis = new BufferedInputStream(new ByteArrayInputStream(bword), 8192);
                BufferedOutputStream osw = new BufferedOutputStream(new FileOutputStream("test1.txt"), 8192)
        ) {
            byte[] bytes = new byte[512];
            while (true) {
                int len = bis.read(bytes);
                if (len == -1) {
                    break;
                }
                osw.write(bytes, 0, len);
            }
            osw.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        OutputStream os = new BufferedOutputStream(System.out, 8192);
        try (
                InputStream bis = new BufferedInputStream(new FileInputStream("test1.txt"), 8192)
        ) {
            byte[] bytes = new byte[512];
            while (true) {
                int len = bis.read(bytes);
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
