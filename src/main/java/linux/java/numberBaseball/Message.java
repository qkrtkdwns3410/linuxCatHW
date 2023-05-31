package linux.java.numberBaseball;

/**
 * packageName    : linux.java.numberBaseball
 * fileName       : Message
 * author         : ipeac
 * date           : 2023-06-01
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023-06-01        ipeac       최초 생성
 */
public enum Message {
    INPUT_NUMBER_STRING("숫자를 입력해주세요: "),
    ;
    
    private final String message;
    
    Message(String message) {
        this.message = message;
    }
}
