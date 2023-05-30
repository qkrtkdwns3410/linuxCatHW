package linux.java.numberBaseball;

import java.io.IOException;
import java.util.LinkedHashSet;
import java.util.Random;
import java.util.Set;

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
        BaseBallGame.start();
    }
    
    public static class BaseBallGame {
        private final BaseBallPlayer baseBallPlayer;
        private final BaseBallReFeree baseBallReFeree;
        private final Balls balls;
        
        public BaseBallGame(BaseBallPlayer baseBallPlayer, BaseBallReFeree baseBallReFeree, Balls balls) {
            this.baseBallPlayer = baseBallPlayer;
            this.baseBallReFeree = baseBallReFeree;
            this.balls = balls;
        }
        
    }
    
    public static class Balls {
        private final int numbers;
        
        public Balls(int numbers) {
            this.numbers = numbers;
        }
        
        public static Balls generateBalls() {
            int randomNumber = generateRandomNumber();
            return new Balls(randomNumber);
        }
        
        public static int generateRandomNumber() {
            Set<Integer> randomSet = new LinkedHashSet<>();
            Random random = new Random();
            int numberCount = 0;
            while (numberCount < 3) {
                int randomNumber = random.nextInt(9) + 1;
                boolean notDulicatedNumber = !randomSet.contains(randomNumber);
                if (notDulicatedNumber) {
                    randomSet.add(randomNumber);
                    numberCount++;
                }
            }
            return randomSet.stream().
        }
    }
    
    public static class BaseBallPlayer {
    
    }
    
    public static class BaseBallReFeree {
    
    }
}
