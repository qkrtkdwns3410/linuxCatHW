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
public class RandomAccessFileEx2 {
    public static void main(String[] args) {
        int[] score = {
                1, 100, 90, 90,
                2, 70, 90, 100,
                3, 100, 100, 100,
                4, 70, 60, 80,
                5, 70, 90, 100,
        };
        try {
            RandomAccessFile raf = new RandomAccessFile("score.dat", "rw");
            for (int i = 0; i < score.length; i++) {
                raf.writeInt(score[i]);
            }
            
            while (true) {
                System.out.println(raf.readInt());
            }
        } catch (EOFException eof) {
            //readInt() 호출시 더 이상 읽을 내용이 없다면 EOFException이 발생합니다.
        } catch (IOException e) {
            e.printStackTrace();
            
        }
        
    }
}


















