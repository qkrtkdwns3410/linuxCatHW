package linux.java.numberBaseball;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static linux.java.numberBaseball.Message.INPUT_NUMBER_STRING;

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
        BaseBallGame.start(randomBalls);
        while (true) {
            System.out.print(INPUT_NUMBER_STRING);
            String inputString = br.readLine();
        }
    }
    
    public static class BaseBallGame {
        private final BaseBallPlayer baseBallPlayer;
        private final BaseBallReFeree baseBallReFeree;
        private final Balls randomBalls;
        
        private BaseBallGame(BaseBallPlayer baseBallPlayer, BaseBallReFeree baseBallReFeree, Balls randomBalls) {
            this.baseBallPlayer = baseBallPlayer;
            this.baseBallReFeree = baseBallReFeree;
            this.randomBalls = randomBalls;
        }
        
        public static BaseBallGame start(Balls randomBalls) {
            BaseBallPlayer baseBallPlayer = new BaseBallPlayer();
            BaseBallReFeree baseBallReFeree = new BaseBallReFeree();
            return new BaseBallGame(baseBallPlayer, baseBallReFeree, randomBalls);
        }
    }
    
    public static class BaseBallPlayer {
    
    }
    
    public static class BaseBallReFeree {
        
        public boolean scoring(int[] userGuessNumbers) {
            
            return true;
        }
    }
}
