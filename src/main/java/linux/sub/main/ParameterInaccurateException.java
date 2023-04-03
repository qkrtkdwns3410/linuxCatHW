package linux.sub.main;

/**
 * packageName    : linux.sub.main
 * fileName       : ParameterExceedException
 * author         : ipeac
 * date           : 2023-04-03
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023-04-03        ipeac       최초 생성
 */
public class ParameterInaccurateException extends Exception {
    public ParameterInaccurateException() {
        System.out.println("올바른 매개변수를 입력해주세요");
    }
}
