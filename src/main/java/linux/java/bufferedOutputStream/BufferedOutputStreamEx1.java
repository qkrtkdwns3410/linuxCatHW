package linux.java.bufferedOutputStream;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * packageName    : linux.java.bufferedOutputStream
 * fileName       : BufferedOutputStreamEx1
 * author         : ipeac
 * date           : 2023-04-09
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023-04-09        ipeac       최초 생성
 */
public class BufferedOutputStreamEx1 {
    public static void main(String[] args) throws IOException {
        try {
            //논리적 데이터를 물리적 데이터로 변환하고 123.txt파일에 쓰기위해  fos 생성
            FileOutputStream fos = new FileOutputStream("123.txt");
            //퍼버링 처리를 위한 보조스트림 생성 - 버퍼의 크기는 5입니다.
            BufferedOutputStream bos = new BufferedOutputStream(fos, 5);
            // 1 부터9까지의 숫자를 보조스트림에 담습니다.
            for (int i = '1'; i <= '9'; i++) {
                bos.write(i);
            }
            //하지만 출력되는 것은 1~ 5 까지입니다.
            //보조스트림 버퍼의 크기는 5입니다. 하지만 1 2 3 4 5 까지 쓰이고 flush 호출이나 버퍼의 크기가 꽉찬다면 파일에 쓰게되빈다.
            // -> 6  ->  6 7  .... -> 6 7 8 9  까지 쌓이고 버퍼 크기인 5까지 차지못했으니 버퍼에 데이터가 flush 되지 못한 상태로 스트림이 종료되어 데이터가 소실됩니다.
            fos.close();
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
