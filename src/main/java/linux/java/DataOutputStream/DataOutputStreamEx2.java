package linux.java.DataOutputStream;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Arrays;

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
public class DataOutputStreamEx2 {
    public static void main(String[] args) throws IOException {
        ByteArrayOutputStream bos = null;
        DataOutputStream dos = null;
        
        byte[] result = null;
        try {
            bos = new ByteArrayOutputStream();
            dos = new DataOutputStream(bos);
            // 4바이트  int  10을 쓴다.
            dos.writeInt(10);
            // 4바이트 float 20.0f 을 씁니다.
            dos.writeFloat(20.0f);
            // 1바이트 1 = true 0 = false 를 씁니다.
            dos.writeBoolean(true);
            
            result = bos.toByteArray();
            
            String[] hex = new String[result.length];
            
            for (int i = 0; i < result.length; i++) {
                if (result[i] < 0) {
                    hex[i] = String.format("%02x", result[i] + 256);
                } else {
                    hex[i] = String.format("%02x", result[i]);
                }
            }
            System.out.println("10진수 :" + Arrays.toString(result));
            System.out.println("16진수 :" + Arrays.toString(hex));
            
            dos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    } // main
}
