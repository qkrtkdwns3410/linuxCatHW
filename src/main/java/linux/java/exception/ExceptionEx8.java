package linux.java.exception;

import java.io.*;

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
public class ExceptionEx8 {
    public static void main(String[] args) throws IOException {
        System.out.println(1);
        System.out.println(2);
        try {
            System.out.println(3);
            System.out.println(0 / 0); // arithmeticException 발생
            System.out.println(4);
        } catch (ArithmeticException ae) {
            try (PrintWriter printWriter = new PrintWriter("123.txt")) {
                ae.printStackTrace(printWriter);
            } catch (FileNotFoundException fnn) {
                System.out.println("파일을 찾을 수 없습니다");
            } catch (SecurityException se) {
                System.out.println("파일을 쓸 수 없습니다.");
            }
        }
        System.out.println(6);
    }// main
}//class
