package linux.java.DataOutputStream;

import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * packageName    : linux.java.DataOutputStream
 * fileName       : DataOutputStreamEx1
 * author         : ipeac
 * date           : 2023-04-09
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023-04-09        ipeac       최초 생성
 */
public class DataOutputStreamEx1 {
    public static void main(String[] args) throws IOException {
        FileOutputStream fos = null;
        DataOutputStream dos = null;
        try {
            fos = new FileOutputStream("sample.dat");
            //fileoutput.. 을 기반으로 하는 DataOutputStream을 생성한 후, DataoutputStream 메서드들을 이용하여
            // sample.dat파일에 값들을 출력한다.
            dos = new DataOutputStream(fos);
            dos.writeInt(10);
            dos.writeFloat(20.0f);
            dos.writeBoolean(true);
            //출력한 값들은 이진 데이터로 저장되며. 그냥 열어서 확인할수없다. (물리적인 데이터이기때문)
            dos.close();
        } catch (IOException e) {
        
        }

        
    } // main
}
