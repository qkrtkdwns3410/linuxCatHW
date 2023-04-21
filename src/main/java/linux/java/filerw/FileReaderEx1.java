package linux.java.filerw;

import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;

/**
 * packageName    : linux.java.filerw
 * fileName       : FileReaderEx1
 * author         : ipeac
 * date           : 2023-04-11
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023-04-11        ipeac       최초 생성
 */
public class FileReaderEx1 {
    public static void main(String[] args) throws IOException {
        String fileName = "input.txt";
        //FileInputStream 과 FileReader 의 차이점을 기술하기 위한 예제입니다.
        FileInputStream fis = new FileInputStream(fileName);
        FileReader fr = new FileReader(fileName);
        
        // Fileinputstream ===========
        int data = 0;
        //FileInputStream 을 이용해서 파일내용을 읽어 화면에 출력한다.
        while ((data = fis.read()) != -1) {
            System.out.print((char) data);
        }
        System.out.println();
        fis.close();
        // Fileinputstream ===========
        
        //FileReader=====================
        //FileReader를 이용해서 파일내용을 읽어 화면에 출력한다.
        // //특정 인코딩을 읽어서 유니코드로 변환함
        while ((data = fr.read()) != -1) {
            System.out.print((char) data);
        }
        System.out.println();
        fr.close();
        //FileReader=====================
    }
}
