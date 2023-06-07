package linux.java.numberBaseball;

import java.util.stream.IntStream;

/**
 * packageName    : linux.java.numberBaseball
 * fileName       : ScoreCalculator
 * author         : ipeac
 * date           : 2023-06-07
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023-06-07        ipeac       최초 생성
 */
public class ScoreCalculator {
    private final int[] answerBalls;
    private final int[] userBalls;
    
    public ScoreCalculator(int[] answerBalls, int[] userBalls) {
        this.answerBalls = answerBalls;
        this.userBalls = userBalls;
    }
    
    public Score calculate() {
        int strikeCount =  (int) IntStream.range(0, answerBalls.length).filter(i -> answerBalls[i] == userBalls[i])
                                          .count();;
        int ballCount = 0;
        
        //ball 카운트
        for (int i = 0; i < 3; i++) {
            int standardIdx = i;
            boolean isBall = IntStream.range(0, answerBalls.length)
                                      .filter(j -> j != standardIdx && answerBalls[standardIdx] == userBalls[j])
                                      .findFirst().isPresent();
            if (isBall) {
                ballCount++;
            }
        }
        
        return new Score(ballCount, strikeCount);
    }
}

