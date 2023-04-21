package linux.java.pipe;

import java.io.IOException;
import java.io.PipedReader;
import java.io.PipedWriter;
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
        OuputThread ouputThread = new OuputThread("OutputThread");
        
        //PipedReader 와 PiperdWriter 를 연결
        inThread.connect(ouputThread.getOutput());
        
        inThread.start();
        ouputThread.start();
    }
}// main

class InputThread extends Thread {
    PipedReader input = new PipedReader();
    StringWriter sw = new StringWriter();
    
    public InputThread(String name) {
        super(name);
    }
    
    public PipedReader getInput() {
        return input;
    }
    
    public void connect(PipedWriter output) {
        try {
            input.connect(output);// PipedReader 에 PipedWriter 연결
        } catch (IOException e) {
        
        }
    }//connect
    
    @Override
    public void run() {
        try {
            int data = 0; // String writer는 메모리를 사용하는 스트림이며 내부적으로 StringBuffer를 가지고 있기에 내용이 sw 에 저장됩니당
            //스트림이 종료시까지 입력을 sw 에 받습니다.
            while ((data = input.read()) != -1) {
                sw.write(data);
            }
            System.out.println(getName() + " received : " + sw.toString());
        } catch (IOException e) {
        }
    }// run
}

class OuputThread extends Thread {
    PipedWriter output = new PipedWriter();
    
    public OuputThread(String name) {
        super(name); // Thread(String name);
    }
    
    public void run() {
        try {
            String msg = "hello";
            System.out.println(getName() + " sent : " + msg);
            output.write(msg);
            output.close();
        } catch (IOException e) {
        
        }
    }// run
    
    public PipedWriter getOutput() {
        return output;
    }//getOutput
    
    public void connect(PipedReader input) {
        try {
            output.connect(input);
        } catch (IOException e) {
        
        }
    }
}