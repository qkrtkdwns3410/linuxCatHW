package linux.sub.hw20230617_2;

import java.io.*;
import java.nio.charset.StandardCharsets;

/**
 * packageName    : linux.sub.hw20230617_2
 * fileName       : Q1
 * author         : ipeac
 * date           : 2023-06-17
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023-06-17        ipeac       최초 생성
 */
public class Q2 {
    public static void main(String[] args) throws IOException {
        String word = "헬로 월드!";
        byte[] bytes = word.getBytes(StandardCharsets.UTF_8);
        try (
                InputStream is = new BufferedInputStream(new ByteArrayInputStream(bytes), 8192);
                OutputStream os1 = new BufferedOutputStream(new FileOutputStream("test1.txt"), 8192);
                OutputStream os2 = new BufferedOutputStream(new FileOutputStream("test2.txt"), 8192);
                OutputStream os3 = new BufferedOutputStream(new FileOutputStream("test3.txt"), 8192);
        ) {
            OutputStream[] outputStreams = new OutputStream[]{
                    os1, os2, os3
            };
            byte[] bytes1 = new byte[512];
            while (true) {
                int len = is.read(bytes1);
                if (len == -1) {
                    break;
                }
                for (OutputStream outputStream : outputStreams) {
                    outputStream.write(bytes1, 0, len);
                }
            }
            for (OutputStream outputStream : outputStreams) {
                outputStream.flush();
            }
            
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        try (
                InputStream is1 = new FileInputStream("test1.txt");
                InputStream is2 = new FileInputStream("test2.txt");
                InputStream is3 = new FileInputStream("test3.txt");
                InputStream sequenceIs = new BufferedInputStream(new SequenceInputStream(is1, new SequenceInputStream(is2, is3)));
                OutputStream os = new BufferedOutputStream(new FileOutputStream("test4.txt"))
        ) {
            byte[] buffer = new byte[1024];
            while (true) {
                int len = sequenceIs.read(buffer);
                if (len == -1) {
                    break;
                }
                os.write(buffer, 0, len);
            }
            os.flush();
        } catch (IOException e) {
            
            e.printStackTrace();
        }
    }
}
