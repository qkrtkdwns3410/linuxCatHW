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
    USER_INPUT_NUMBER("숫자를 입력해주세요: ", false),
    STRIKE_MESSAGE("스트라이크: %d", true),
    BALL_MESSAGE("볼: %d", true),
    NOT_MATCH("낫싱", false),
    
    ALL_MATCH("3개의 숫자를 모두 맞히셨습니다! 게임 종료", false),
    RE_GAME("게임을 새로 시작하려면 1, 종료하려면 2를 입력하세요.", false),
    
    DUPLICATE_BALL_ALERT("중복된 BALL 값이 존재합니다.", false),
    
    LESS_THAN_THREE_BALL("야구공이 3개가 주어져야합니다.",false)
    ;
    
    private final String pattern;
    private final boolean requireParam;
    
    Message(String pattern, boolean requireParam) {
        this.pattern = pattern;
        this.requireParam = requireParam;
    }
    
    public String getMessage() {
        if (requireParam) {
            throw new IllegalArgumentException("숫자값을 넣어주세요");
        }
        return pattern;
    }
    
    public String getMessage(int num) {
        return String.format(pattern, num);
    }
    
}
