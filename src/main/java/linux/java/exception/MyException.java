package linux.java.exception;

/**
 * packageName    : linux.java.exception
 * fileName       : MyException
 * author         : ipeac
 * date           : 2023-05-04
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023-05-04        ipeac       최초 생성
 */
public class MyException extends Exception {
    private final int ERR_CODE; // 에러 코드 값을
    
    public MyException(String msg, int errCode) {
        super(msg);
        ERR_CODE = errCode;
    }
    
    public MyException(String message) { //생성자
        this(message, 100);// ERR_CODE 를 100(기본값)으로 초기화함
    }
    
    public int getErrCode() { //에러 코드를 얻을 수 있는 메서드로 추가함
        return ERR_CODE;  // 이 메서드는 주로 getMessage() 와 함께 사용될 예정임
    }
    
}
