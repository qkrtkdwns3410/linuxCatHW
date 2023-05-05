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
public class ExceptionEx17 {
    public static void main(String[] args) {
        try {
            method1();
        } catch (Exception e) {
            System.out.println("main메서드에서 예외가 처리되었습니다!");
        }
    }// main

    private static void method1() throws Exception{
        try {
            throw new Exception();
        } catch (Exception e) {
            System.out.println("method1 메서드에서 예외가 처리되었습니다");
            throw e; // 다시 예외를 발생시킴
        }
    }

}//class
