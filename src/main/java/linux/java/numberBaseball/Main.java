package linux.java.numberBaseball;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Objects;
import java.util.function.Consumer;

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
        playGame();
    }
    
    private static void playGame() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            Balls randomBalls = Balls.generateRandomBalls();
            System.out.print(USER_INPUT_NUMBER.getMessage());
            String input = br.readLine();
            try {
                Balls userBall = Balls.from(input);
                ScoreCalculator scoreCalculator = new ScoreCalculator(randomBalls.getNumbers(), userBall.getNumbers());
                ResultPrinter resultPrinter = new ResultPrinter(scoreCalculator.calculate());
                BaseBallReFeree baseBallReFeree = new BaseBallReFeree(scoreCalculator, resultPrinter);
                boolean reGame = baseBallReFeree.playGame(System.out::println);
                
                if (reGame) {
                    String reGameInput = br.readLine();
                    if (Objects.equals("2", reGameInput)) {
                        break;
                    }
                }
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            }
        }
        
    }
    
    public static class BaseBallReFeree {
        private final ScoreCalculator scoreCalculator;
        private final ResultPrinter resultPrinter;
        
        public BaseBallReFeree(ScoreCalculator scoreCalculator, ResultPrinter resultPrinter) {
            this.scoreCalculator = scoreCalculator;
            this.resultPrinter = resultPrinter;
        }
        
        public boolean playGame(Consumer<String> printer) {
            return resultPrinter.print(printer);
            
        }
    }
}
