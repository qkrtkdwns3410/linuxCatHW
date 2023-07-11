package linux.sub.hw20230624;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;

public class Q2 {
    public static void main(String[] args) {
        String word = "HelloWorld";
        byte[] bword = word.getBytes(StandardCharsets.UTF_8);
        byte[] bytes = new byte[1024];
        
        try (
                InputStream is = new BufferedInputStream(new ByteArrayInputStream(bword), 8192);
                OutputStream os1 = new BufferedOutputStream(new FileOutputStream("file1.txt"), 8192);
                OutputStream os2 = new BufferedOutputStream(new FileOutputStream("file2.txt"), 8192);
                OutputStream os3 = new BufferedOutputStream(new FileOutputStream("file3.txt"), 8192);
        ) {
            List<OutputStream> outputStreams = List.of(os1, os2, os3);
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
                InputStream is1 = new BufferedInputStream(new FileInputStream("file1.txt"), 8192);
                InputStream is2 = new BufferedInputStream(new FileInputStream("file2.txt"), 8192);
                InputStream is3 = new BufferedInputStream(new FileInputStream("file3.txt"), 8192);
                SequenceInputStream sis = new SequenceInputStream(is1, new SequenceInputStream(is2, is3));
                OutputStream os = new BufferedOutputStream(new FileOutputStream("file4.txt"), 8192);
        ) {
            while (true) {
                int len = sis.read(bytes);
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
