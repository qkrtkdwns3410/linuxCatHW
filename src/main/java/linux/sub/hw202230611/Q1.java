package linux.sub.hw202230611;

import java.io.*;
import java.nio.charset.StandardCharsets;

/**
 * packageName    : linux.sub.hw202230611
 * fileName       : Q1
 * author         : ipeac
 * date           : 2023-06-11
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023-06-11        ipeac       최초 생성
 */
public class Q1 {
    public static void main(String[] args) throws IOException {
        /*
                - 메모리 HelloWorld를 파일 test1으로 출력하고 파일 test1을 콘솔로 출력하기
                - 콘솔출력은 OutputStream 사용 (println X)
        */
        String word = "HelloWorld";
        byte[] bytes = word.getBytes(StandardCharsets.UTF_8);
        //출력 인코딩
        BufferedInputStream memoryBis = new BufferedInputStream(new ByteArrayInputStream(bytes), 8192);
        BufferedOutputStream fileBos = new BufferedOutputStream(new FileOutputStream("test1.txt"), 8192);
        streamingToFile(memoryBis, fileBos);
        
        OutputStreamWriter consoleOsw = new OutputStreamWriter(new BufferedOutputStream(System.out, 8192), StandardCharsets.UTF_8);
        InputStreamReader fileIsr = new InputStreamReader(new BufferedInputStream(new FileInputStream("test1.txt"), 8192));
        streamingToConsole(fileIsr, consoleOsw);
    }
    
    public static void streamingToFile(BufferedInputStream bis, BufferedOutputStream fileOutput) {
        try (bis; fileOutput) {
            byte[] bytes = new byte[512];
            while (true) {
                int len = bis.read(bytes);
                if (len == -1) {
                    break;
                }
                //이진데이터
                fileOutput.write(bytes, 0, len);
            }
            fileOutput.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public static void streamingToConsole(InputStreamReader isr, OutputStreamWriter consoleOutput) {
        try (isr) {
            char[] chars = new char[256];
            while (true) {
                int len = isr.read(chars);
                if (len == -1) {
                    break;
                }
                consoleOutput.write(chars, 0, len);
            }
            consoleOutput.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
