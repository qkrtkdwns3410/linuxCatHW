package linux.java.bufferedOutputStream;

import java.io.EOFException;
import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * packageName    : linux.java.bufferedOutputStream
 * fileName       : RandomAccessFileEx1
 * author         : ipeac
 * date           : 2023-04-21
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023-04-21        ipeac       최초 생성
 */
public class RandomAccessFileEx3 {
    public static void main(String[] args) {
        int sum = 0;
        try {
            //모든 사람의 국어 점수의 합
            RandomAccessFile raf = new RandomAccessFile("score.dat", "r");
            int i = 4; //번호 국어 영어 수학 의 int 데이터는 4바이트씩 차지합니다. 1 99 100 200 이라면 1에서 4바이트 이후 국어는 4 위치에 존재하며
                
                while (true) {
                raf.seek(i); // 4부터
                sum += raf.readInt(); //4바이트 인트를 읽습니다.
                i += 16;//국어 영어 수학 번호를 읽고.. 16바이트 후 다시 영어 위치에 도달함.
            }
        } catch (EOFException e) {
            System.out.println("sum: " + sum);
        } catch (IOException e2) {
            e2.printStackTrace();
        }
    }
}


















