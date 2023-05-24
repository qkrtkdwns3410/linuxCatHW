package linux.java.numberBaseball;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * packageName    : linux.java.numberBaseball
 * fileName       : Main
 * author         : ipeac
 * date           : 2023-05-24
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023-05-24        ipeac       최초 생성
 */
public class Main {
    public static void main(String[] args) throws IOException {
        Random random = new Random();
        int randomNumber = random.nextInt(1000);
        int guessNumber = 0;
        
        try (InputStream is = System.in) {
            StringBuilder word = new StringBuilder();
            while (true) {
                byte[] bytes = new byte[3];
                int len = is.read(bytes);
                if (len == -1) {
                    break;
                }
                String str = new String(bytes, 0, len, StandardCharsets.UTF_8);
                word.append(str);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        
    }
    
    
    public static class BaseGamePlayer {
        List<Integer> answerNumbers;
        List<Integer> guessNumbers;
        
        public BaseGamePlayer(List<Integer> answerNumbers) {
            this.answerNumbers = makeRandomNumber();
            this.guessNumbers = new ArrayList<>();
        }
        
        private static List<Integer> makeRandomNumber() {
            List<Integer> list = new ArrayList<>();
            Random random = new Random();
            
            for (int i = 0; i < 3; i++) {
                list.add(random.nextInt(10));
            }
            return list;
        }
    }
    
    public static class BaseBallGame {
        BaseGamePlayer baseGamePlayer1;
        BaseGamePlayer baseGamePlayer2;
        
        public BaseBallGame(BaseGamePlayer baseGamePlayer1, BaseGamePlayer baseGamePlayer2) {
            this.baseGamePlayer1 = baseGamePlayer1;
            this.baseGamePlayer2 = baseGamePlayer2;
        }
        
        public void startGame() {
        
        }
    }
    
}
