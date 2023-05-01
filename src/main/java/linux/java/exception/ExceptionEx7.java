package linux.java.exception;

import java.io.IOException;

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
public class ExceptionEx7 {
    public static void main(String[] args) throws IOException {
        System.out.println(1);
        System.out.println(2);
        try {
            System.out.println(3);
            System.out.println(0 / 0); // arithmeticException 발생
            System.out.println(4);
        } catch (ArithmeticException arithmeticException) {//exception은 위 에러의 조상클래스이기에 instanceof ==> true 반환
            if (arithmeticException instanceof ArithmeticException) {
                System.out.println("true");
            }
            System.out.println("ArithmeticException");
        } catch (Exception e) {
            System.out.println("Exception");
        }
        System.out.println(6);
    }// main
}//class
