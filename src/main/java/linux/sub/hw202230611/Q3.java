package linux.sub.hw202230611;

import java.io.*;
import java.nio.charset.StandardCharsets;

/**
 * packageName    : linux.sub.hw202230611
 * fileName       : Q3
 * author         : ipeac
 * date           : 2023-06-11
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023-06-11        ipeac       최초 생성
 */
public class Q3 {
    public static void main(String[] args) {
        /*
            메모리 “안녕하세요” UTF8 데이터를 EUC-KR 파일 test1으로 저장하고 test1을 읽어서 UTF-8 방식의 test2 파일로 저장하기
        */
        String word = "안녕하세요";
        byte[] wordBytes = word.getBytes(StandardCharsets.UTF_8);
        
        //입력스트림 - > 디코딩 UTF-8
        //출력 스트림 -> 인코딩 EUC-KR ( 이진데이터 파일 )
        try (
                InputStreamReader isr = new InputStreamReader(new BufferedInputStream(new ByteArrayInputStream(wordBytes), 8192), StandardCharsets.UTF_8);
                OutputStreamWriter osw = new OutputStreamWriter(new BufferedOutputStream(new FileOutputStream("test1.txt"), 8192), "EUC-KR");
        ) {
            char[] chars = new char[128];
            while (true) {
                int len = isr.read(chars);
                if (len == -1) {
                    break;
                }
                osw.write(chars, 0, len);
            }
            osw.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try (
                InputStreamReader isr = new InputStreamReader(new BufferedInputStream(new FileInputStream("test1.txt"), 8192), "EUC-KR");
                OutputStreamWriter osw = new OutputStreamWriter(new BufferedOutputStream(new FileOutputStream("test2.txt"), 8192), StandardCharsets.UTF_8)
        ) {
            char[] chars = new char[128];
            while (true) {
                int len = isr.read(chars);
                if (len == -1) {
                    break;
                }
                osw.write(chars, 0, len);
            }
            osw.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
