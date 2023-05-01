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
public class ExceptionEx2 {
    public static void main(String[] args) throws IOException {
        int number = 100;
        int result = 0;
        
        for (int i = 0; i < 10; i++) {
            try {
                result = number / (int) (Math.random() * 10); // 7번째 라인
                System.out.println(result);
            } catch (ArithmeticException e) {
                System.out.println("0");
            }
            
        }// for
    }// main
}//class
