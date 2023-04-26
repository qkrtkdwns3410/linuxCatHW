package linux.java.inputstreamrw;

import java.io.IOException;

/**
 * packageName    : linux.java.inputstreamrw
 * fileName       : StandardIOEx1
 * author         : ipeac
 * date           : 2023-04-17
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023-04-17        ipeac       최초 생성
 */
public class StandardIOEx1 {
    public static void main(String[] args) throws IOException {
        int input = 0;
        while ((input = System.in.read()) != -1) { // bufferedInputStream
            System.out.println("input :" + input + ", (char)input :" + (char) input);
        }
    }
}
