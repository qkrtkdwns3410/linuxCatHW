package linux.sub.hw20230514;

/**
 * packageName    : linux.sub.hw20230514
 * fileName       : Input
 * author         : ipeac
 * date           : 2023-05-14
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023-05-14        ipeac       최초 생성
 */
public class Input {
    private final String inputString;
    
    public Input(String inputString) {
        if (inputString == null) {
            throw new IllegalArgumentException("inputString 이 null 입니다.");
        }
        // 별도 입력 검증이 없습니다.
        this.inputString = inputString;
    }
    
    public static Input from(String inputString) {
        return new Input(inputString);
    }
    
    public String getInputString() {
        return inputString;
    }
    
}
