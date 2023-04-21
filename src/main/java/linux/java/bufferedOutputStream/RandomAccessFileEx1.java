package linux.java.bufferedOutputStream;

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
public class RandomAccessFileEx1 {
    public static void main(String[] args) throws IOException {
        RandomAccessFile raf = new RandomAccessFile("input.txt", "rw");
        System.out.println("파일 포인터의 위치: " + raf.getFilePointer()); //처음 포인터 시작은 0
        raf.writeInt(100);// int 4바이트
        System.out.println("파일 포인터의 위치: " + raf.getFilePointer());  //포인터 4
        raf.writeLong(100L);// Long 은 8바이트
        System.out.println("파일 포인터의 위치: " + raf.getFilePointer()); // 포인터 12
    }
}
