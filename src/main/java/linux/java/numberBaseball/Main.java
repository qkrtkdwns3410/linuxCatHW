package linux.java.numberBaseball;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.MessageFormat;
import java.util.Arrays;
import java.util.function.Consumer;
import java.util.stream.IntStream;

import static linux.java.numberBaseball.Message.USER_INPUT_NUMBER;

/**
 * packageName    : linux.java.numberBaseball
 * fileName       : Main
 * author         : ipeac
 * date           : 2023-05-29
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023-05-29        ipeac       최초 생성
 */
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        Balls randomBalls = Balls.generateRandomBalls();
        while (true) {
            System.out.print(USER_INPUT_NUMBER.getMessage());
            //사용자의 입력 -> 객체를 여러개 만들고싶지 않았음.. 매번 반복할때마다 referee객체가 만들어지지 않았으면 좋겠지만,
            // 결국 userBall 과 answer 자체 BALLS 객체들을 생성시에 알고있어야 객체 생성시에 유효성이 검증될수있다고 생각함.
            String input = br.readLine();
            Balls userBall = Balls.from(input);
            BaseBallReFeree baseBallReFeree = BaseBallReFeree.from(randomBalls, userBall);
            Consumer<BaseBallReFeree> scorePrinter = new Consumer<BaseBallReFeree>() {
                @Override
                public void accept(BaseBallReFeree baseBallReFeree) {
                    if (baseBallReFeree.getBallCount() != 0) {
                    
                    }
                }
            }
            baseBallReFeree.scoring(System.out::println);
        }
    }
    
    public static class BaseBallGame {
        private final BaseBallPlayer baseBallPlayer;
        private final BaseBallReFeree baseBallReFeree;
        
        private BaseBallGame(BaseBallPlayer baseBallPlayer, BaseBallReFeree baseBallReFeree) {
            this.baseBallPlayer = baseBallPlayer;
            this.baseBallReFeree = baseBallReFeree;
        }
        
        
    }
    
    public static class BaseBallPlayer {
        private final int[] submitNumber;
        
        public BaseBallPlayer(int[] submitNumber) {
            this.submitNumber = submitNumber;
        }
        
        public static BaseBallPlayer from(String submitNumberStr) {
            String[] splited = submitNumberStr.split("");
            int[] convertedNumbers = Arrays.stream(splited).mapToInt(Integer::parseInt).toArray();
            return new BaseBallPlayer(convertedNumbers);
        }
    }
    
    public static class BaseBallReFeree {
        private final int[] answerBalls;
        private final int[] userBalls;
        
        private int ballCount;
        private int strikeCount;
        
        public BaseBallReFeree(int[] answerBalls, int[] userBalls) {
            this.answerBalls = answerBalls;
            this.userBalls = userBalls;
        }
        
        public static BaseBallReFeree from(Balls answerRandomBall, Balls userGuessBall) {
            int[] answers = answerRandomBall.getNumbers();
            int[] userBalls = userGuessBall.getNumbers();
            return new BaseBallReFeree(answers, userBalls);
        }
        
        private void scoring(Consumer<String> printer) {
            
            //strike 카운트
            strikeCount = (int) IntStream.range(0, answerBalls.length).filter(i -> answerBalls[i] == userBalls[i])
                                         .count();
            
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
            
            printer.accept(msg);
            
        }
        
        public int getBallCount() {
            return ballCount;
        }
        
        public int getStrikeCount() {
            return strikeCount;
        }
    }
    
    public static class Score {
        private int ballCount;
        private int strikeCount;
        
        public Score(int ballCount, int strikeCount) {
            this.ballCount = ballCount;
            this.strikeCount = strikeCount;
        }
        
        public void
    }
}
