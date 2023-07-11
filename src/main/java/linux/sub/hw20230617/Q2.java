package linux.sub.hw20230617;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class Q2 {
    public static void main(String[] args) {
        String word = "HelloWorld";
        byte[] bword = word.getBytes(StandardCharsets.UTF_8);
        
        try (
                InputStream is = new BufferedInputStream(new ByteArrayInputStream(bword), 8192);
                OutputStream os1 = new BufferedOutputStream(new FileOutputStream("test1.txt"), 8192);
                OutputStream os2 = new BufferedOutputStream(new FileOutputStream("test2.txt"), 8192);
                OutputStream os3 = new BufferedOutputStream(new FileOutputStream("test3.txt"), 8192);
        ) {
            byte[] bytes = new byte[512];
            OutputStream[] outputStreams = new OutputStream[]{
                    os1, os2, os3
            };
            while (true) {
                int len = is.read(bytes);
                if (len == -1) {
                    break;
                }
                for (OutputStream os : outputStreams) {
                    os.write(bytes, 0, len);
                }
            }
            for (OutputStream os : outputStreams) {
                os.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        try (
                OutputStream fileOutputStream = new BufferedOutputStream(new FileOutputStream("test4.txt"), 8192);
                InputStream is1 = new FileInputStream("test1.txt");
                InputStream is2 = new FileInputStream("test2.txt");
                InputStream is3 = new FileInputStream("test3.txt");
                InputStream sis = new SequenceInputStream(is1, new SequenceInputStream(is2, is3));
                InputStream bsis = new BufferedInputStream(sis, 8192)
        ) {
            byte[] bytes = new byte[1024];
            while (true) {
                int len = bsis.read(bytes);
                if (len == -1) {
                    break;
                }
                fileOutputStream.write(bytes, 0, len);
            }
            fileOutputStream.flush();
        } catch (IOException e) {
        
        }
        
    }
}
