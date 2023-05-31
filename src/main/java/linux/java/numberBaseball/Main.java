package linux.java.numberBaseball;

import java.io.IOException;
import java.util.*;

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
        Balls randomBalls = Balls.generateBalls();
        
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
        private final int[] numbers;
        
        public Balls(int[] numbers) {
            this.numbers = numbers;
        }
        
        public static Balls generateBalls() {
            int[] randomNumbers = generateRandomNumber();
            System.out.println("randomNumber = " + Arrays.toString(randomNumbers));
            return new Balls(randomNumbers);
        }
        
        private static int[] generateRandomNumber() {
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
            int[] randomInts = randomSet.stream().mapToInt(Integer::intValue).toArray();
            return randomInts;
        }
        
        @Override
        public String toString() {
            return new StringJoiner(", ", Balls.class.getSimpleName() + "[", "]")
                    .add("numbers=" + Arrays.toString(numbers))
                    .toString();
        }
        
        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Balls balls = (Balls) o;
            return Arrays.equals(numbers, balls.numbers);
        }
        
        @Override
        public int hashCode() {
            return Arrays.hashCode(numbers);
        }
    }
    
    public static class BaseBallPlayer {
    
    }
    
    public static class BaseBallReFeree {
    
    }
}
