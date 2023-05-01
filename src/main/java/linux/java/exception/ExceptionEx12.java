package linux.java.exception;

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
public class ExceptionEx12 {
    public static void main(String[] args) throws Exception {
        method1();
    }// main

    private static void method1() throws Exception {
        method2();
    }

    private static void method2() throws Exception {
        throw new Exception();
    }
}//class
