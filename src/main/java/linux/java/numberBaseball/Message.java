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
    USER_INPUT_NUMBER("숫자를 입력해주세요: "),
    STRIKE_MESSAGE("스트라이크: "),
    BALL_MESSAGE("볼: "),
    NOT_MATCH("낫싱"),
    ALL_MATCH("3개의 숫자를 모두 맞히셨습니다! 게임 종료"),
    RE_GAME("게임을 새로 시작하려면 1, 종료하려면 2를 입력하세요.");
    
    private final String message;
    
    Message(String message) {
        this.message = message;
    }
    
    //직접적인 변수의 호출X -> getter 로 접근 혹시 별도 메서드로 만들어도 되긴한다.
    public String getMessage() {
        return message == null ? "" : message;
    }
}
