package linux.java.exception;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * packageName    : linux.java.exception
 * fileName       : ExceptionEx2
 * author         : ipeac
 * date           : 2023-04-30
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023-04-30        ipeac       최초 생성
 */
public class ExceptionEx9 {
    public static void main(String[] args) throws IOException {
        try {
            Exception e = new Exception("고의로 발생시킨 예외");
            throw e;
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("프로그램 정상 종료");
    }// main
}//class
