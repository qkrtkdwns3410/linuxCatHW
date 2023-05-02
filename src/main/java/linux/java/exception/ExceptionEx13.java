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
public class ExceptionEx13 {
    public static void main(String[] args) throws Exception {
        method1();
    }// main
    
    private static void method1() {
        try {
            throw new Exception();
        } catch (Exception e) {
            System.out.println("method1 에서 예외가 처리되었습니다");
            e.printStackTrace();
        }
    }

}//class
