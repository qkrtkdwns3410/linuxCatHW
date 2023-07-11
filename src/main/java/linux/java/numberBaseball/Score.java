package linux.java.numberBaseball;

/**
 * packageName    : linux.java.numberBaseball
 * fileName       : Score
 * author         : ipeac
 * date           : 2023-06-07
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023-06-07        ipeac       최초 생성
 */
public class Score {
    private int ballCount;
    private int strikeCount;
    
    public Score(int ballCount, int strikeCount) {
        this.ballCount = ballCount;
        this.strikeCount = strikeCount;
    }
    
    // ballCount와 strikeCount에 대한 getter 메서드를 추가할 수 있습니다.
    public int getBallCount() {
        return ballCount;
    }
    
    public int getStrikeCount() {
        return strikeCount;
    }
    
}
