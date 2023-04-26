package linux.java.filerw;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * packageName    : linux.java.filerw
 * fileName       : FileConversion
 * author         : ipeac
 * date           : 2023-04-11
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023-04-11        ipeac       최초 생성
 */
public class FileConversion {
    public static void main(String[] args) throws IOException {
        FileReader fr = new FileReader("src/main/java/linux/java/filerw/FileConversion.java");
        FileWriter fw = new FileWriter("src/main/java/linux/java/filerw/convert.txt");
        
        //처리단위입니다.
        int data = 0;
        // -1 을 반환하면 스트림이 닫혔다는 의미이고 반복이 중단됩니다.
        // data 는 처리단위겸 . 데이터의 처리단위가 됩니다!
        while ((data=fr.read())!=-1) {
            // 공백 탭, 줄바꿈 등이라면 데이터를 쓰지않습니다.
            if (data != '\t' && data != '\n' && data != ' ' && data != '\r') {
                fw.write(data);
            }
        }
        fr.close();
        fw.close();
    }
    
}
