package linux.java.DataOutputStream;

import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * packageName    : linux.java.DataOutputStream
 * fileName       : DataOutputStreamEx3
 * author         : ipeac
 * date           : 2023-04-09
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023-04-09        ipeac       최초 생성
 */
public class DataOutputStreamEx3 {
    public static void main(String[] args) throws IOException {
        int[] score = {100, 90, 95, 85, 50};
        FileOutputStream fos = new FileOutputStream("score.dat");
        DataOutputStream dos = new DataOutputStream(fos);
        for (int j : score) {
            dos.writeInt(j);
        }
        dos.close();
    }
}
