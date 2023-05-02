package linux.java.exception;

import java.io.IOException;

/**
 * packageName    : linux.java.exception
 * fileName       : FinallyTeest
 * author         : ipeac
 * date           : 2023-05-02
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023-05-02        ipeac       최초 생성
 */
public class FinallyTest3 {
    public static void main(String[] args) throws IOException {
        //method1 은 static 메서드이므로 인스턴스 생성없이 직접 호출이 가능하다.
        method1();
        System.out.println("method1() 의 수행을 마치고 main 메서드로 복귀함");
    }
    
    public static void method1() {
        try {
            System.out.println("method1 () 이 호출되었습니다");
            return;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.out.println("method1 () 의 finally 블럭이 실행되었습니다.");
        }
    }
}
