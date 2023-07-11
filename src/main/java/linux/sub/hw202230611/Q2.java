package linux.sub.hw202230611;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class Q2 {
    public static void main(String[] args) throws IOException {
        /*
        - 메모리 HelloWorld를 파일 test1, test2, test3로 출력하고 3개의 파일 내용을 하나의 파일 test4로 출력하기
        - 내용을 합치자!
        */
        String word = "HelloWorld";
        byte[] bword = word.getBytes(StandardCharsets.UTF_8);
        
        
        try (
                BufferedInputStream bis = new BufferedInputStream(new ByteArrayInputStream(bword), 8192);
                BufferedOutputStream bos1 = new BufferedOutputStream(new FileOutputStream("test1.txt"), 8192);
                BufferedOutputStream bos2 = new BufferedOutputStream(new FileOutputStream("test2.txt"), 8192);
                BufferedOutputStream bos3 = new BufferedOutputStream(new FileOutputStream("test3.txt"), 8192);
        ) {
            BufferedOutputStream[] outputStreams = new BufferedOutputStream[]{
                    bos1, bos2, bos3
            };
            byte[] bytes = new byte[512];
            while (true) {
                int len = bis.read(bytes);
                if (len == -1) {
                    break;
                }
                for (BufferedOutputStream bos : outputStreams) {
                    bos.write(bytes, 0, len);
                }
            }
            for (BufferedOutputStream bos : outputStreams) {
                bos.flush();
            }
        }
        BufferedInputStream bisfile1 = new BufferedInputStream(new FileInputStream("test1.txt"), 8192);
        BufferedInputStream bisfile2 = new BufferedInputStream(new FileInputStream("test2.txt"), 8192);
        BufferedInputStream bisfile3 = new BufferedInputStream(new FileInputStream("test3.txt"), 8192);
        
        BufferedInputStream[] bufferedInputStreams = new BufferedInputStream[]{
                bisfile1, bisfile2, bisfile3
        };
        concatFiles(bufferedInputStreams, "test4.txt");
    }
    
    public static void concatFiles(BufferedInputStream[] bufferedInputStreams, String fileName) throws IOException {
        try (BufferedOutputStream fileBos = new BufferedOutputStream(new FileOutputStream(fileName), 8192)) {
            for (BufferedInputStream bis : bufferedInputStreams) {
                streamingToFile(bis, fileBos);
            }
        }
    }
    
    public static void streamingToFile(BufferedInputStream bis, BufferedOutputStream fileOutput) {
        try  {
            byte[] bytes = new byte[512];
            while (true) {
                int len = bis.read(bytes);
                if (len == -1) {
                    break;
                }
                // 이진데이터
                fileOutput.write(bytes, 0, len);
            }
            fileOutput.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
