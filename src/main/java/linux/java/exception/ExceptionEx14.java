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
public class ExceptionEx14 {
    public static void main(String[] args) throws Exception {
        try {
            method1();
        } catch (Exception e) {
            System.out.println("main 메서드에서 예외가 처리되었습니다.");
            e.printStackTrace();
        }
    }// main
    
    private static void method1() throws Exception {
        throw new Exception();
    }
    
}//class
