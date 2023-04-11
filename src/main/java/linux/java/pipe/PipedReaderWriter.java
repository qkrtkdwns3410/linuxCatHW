package linux.java.pipe;

import java.io.IOException;
import java.io.PipedReader;
import java.io.StringWriter;

/**
 * packageName    : linux.java.pipe
 * fileName       : PipedReaderWriter
 * author         : ipeac
 * date           : 2023-04-11
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023-04-11        ipeac       최초 생성
 */
public class PipedReaderWriter {
    public static void main(String[] args) throws IOException {
        InputThread inThread = new InputThread("InputThread");
        
    }
    
}

class InputThread extends Thread {
    PipedReader input = new PipedReader();
    StringWriter sw = new StringWriter();
    
    public InputThread(String name) {
        super(name);
    }
    
    @Override
    public void run() {
        try {
            int data = 0;
            while ((data = input.read()) != -1) {
                sw.write(data);
            }
            System.out.println(getName() + " received : " + sw.toString());
        } catch (IOException e) {
        
        }
    }
}