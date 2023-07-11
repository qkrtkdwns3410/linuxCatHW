package linux.sub.hw20230610;

import java.io.*;
import java.nio.charset.StandardCharsets;

/**
 * packageName    : linux.sub.hw20230610
 * fileName       : Main
 * author         : ipeac
 * date           : 2023-06-10
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023-06-10        ipeac       최초 생성
 */
public class Main {
    public static void main(String[] args) {
        String word = "안녕하세요 안깨지네요!!!";
        byte[] bword = word.getBytes(StandardCharsets.UTF_8);
        
        
        try (
                InputStream is = new ByteArrayInputStream(bword);
                BufferedInputStream bis = new BufferedInputStream(is, 8192);
                OutputStream os = new FileOutputStream("123.txt");
                BufferedOutputStream bos = new BufferedOutputStream(os, 8192);
        ) {
            
            byte[] bytes = new byte[4];
            while (true) {
                int len = bis.read(bytes);
                if (len == -1) {
                    break;
                }
                bos.write(bytes, 0, len);
            }
            
            
        } catch (IOException e) {
            e.printStackTrace();
        }
        OutputStream consoleOs = System.out;
        BufferedOutputStream consoleBos = new BufferedOutputStream(consoleOs, 8192);
        try (
                InputStream is2 = new FileInputStream("123.txt");
                BufferedInputStream bis = new BufferedInputStream(is2, 8192);
                
                OutputStream fileOs = new FileOutputStream("123-2.txt");
                BufferedOutputStream bos = new BufferedOutputStream(fileOs, 8192);
                
                OutputStream fileOs2 = new FileOutputStream("123-3.txt");
                BufferedOutputStream bos2 = new BufferedOutputStream(fileOs2, 8192);
        ) {
            OutputStream[] outputStreams = new OutputStream[]{
                    consoleBos, bos, bos2
            };
            
            byte[] bytes = new byte[4];
            while (true) {
                int len = bis.read(bytes);
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
        
    }
}
