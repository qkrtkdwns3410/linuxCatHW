package linux.sub.hw20230610;

import java.io.*;
import java.nio.charset.StandardCharsets;

/**
 * packageName    : linux.sub.hw20230610
 * fileName       : Main2
 * author         : ipeac
 * date           : 2023-06-10
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023-06-10        ipeac       최초 생성
 */
public class Main2 {
    public static void main(String[] args) {
        // EUC-KR 파일 -> UTF- 8 파일 -> 콘솔
        String word = "한글입니다 한글입니다 한글입니다 한글 입니다,";
        byte[] bytes = toEucKrBytes(word);
        
        try (
                InputStream is = new ByteArrayInputStream(bytes);
                BufferedInputStream bis = new BufferedInputStream(is, 8192);
                OutputStream os = new FileOutputStream("euc-kr.txt");
                BufferedOutputStream bos = new BufferedOutputStream(os, 8192);
        ) {
            byte[] bb = new byte[100];
            while (true) {
                int len = bis.read(bb);
                if (len == -1) {
                    break;
                }
                bos.write(bb, 0, len);
            }
            bos.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //파일을 읽어서 -> 파일로 출력
        try (
                InputStream is = new FileInputStream("euc-kr.txt");
                BufferedInputStream bis = new BufferedInputStream(is, 8192);
                InputStreamReader isr = new InputStreamReader(bis, "EUC-KR");// 디코딩
                
                //논리 EUC-KR 디코딩
                
                OutputStream os = new FileOutputStream("utf-8.txt");
                BufferedOutputStream bos = new BufferedOutputStream(os, 8192);
                OutputStreamWriter osw = new OutputStreamWriter(bos, StandardCharsets.UTF_8); // 인코딩
        ) {
            char[] chars = new char[100];
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
                InputStream is = new FileInputStream("utf-8.txt");
                BufferedInputStream bis = new BufferedInputStream(is, 8192);
                InputStreamReader isr = new InputStreamReader(bis, StandardCharsets.UTF_8);
        ) {
            char[] chars = new char[100];
            while (true) {
                int len = isr.read(chars);
                if (len == -1) {
                    break;
                }
                String s = new String(chars, 0, len);
                System.out.print(s);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private static byte[] toEucKrBytes(String word) {
        try {
            return word.getBytes("EUC-KR");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }
    
}
