package linux.java.DataOutputStream;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * packageName    : linux.java.DataOutputStream
 * fileName       : DataInputStreamEx1
 * author         : ipeac
 * date           : 2023-04-09
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023-04-09        ipeac       최초 생성
 */
public class DataInputStreamEx1 {
    public static void main(String[] args) throws IOException {
        FileInputStream fis = new FileInputStream("sample.dat");
        DataInputStream dis = new DataInputStream(fis);
        
        System.out.println(dis.readInt());
        System.out.println(dis.readFloat());
        System.out.println(dis.readBoolean());
        dis.close();
        /*
        데이터를 읽어올때 아무런 변환이나 자릿수를 셀 필요없이 단순히
         readInt() 와 같이 읽어 올 데이터의 타입에 맞는 메서드를 사용하기만 하면됨
        */
    }
}
