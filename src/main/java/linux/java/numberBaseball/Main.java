package linux.java.numberBaseball;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

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
    public static void main(String[] args) {
        Random random = new Random();
        int randomNumber = random.nextInt(899) + 100;
        
        BaseBallNumberMaker baseBallNumberMaker = BaseBallNumberMaker.from(randomNumber);
        BaseGamePlayer baseGamePlayer = BaseGamePlayer.fromConsole();
        while (true) {
            System.out.print("숫자를 입력해주세요: ");
            String guessNumber = baseGamePlayer.writeGuessNumber();
            System.out.println("guessNumber = " + guessNumber);
        }
    }
    
    public static class Application {
        private List<Integer> guessNumber;
        
        public Application(List<Integer> guessNumber) {
            this.guessNumber = guessNumber;
        }
        
        public
    }
    
    public static class BaseGamePlayer {
        BufferedReader bufferedReader;
        
        public BaseGamePlayer(BufferedReader bufferedReader) {
            this.bufferedReader = bufferedReader;
        }
        
        public static BaseGamePlayer fromConsole() {
            return new BaseGamePlayer(openConsoleBufferedInputStream());
        }
        
        private static BufferedReader openConsoleBufferedInputStream() {
            return new BufferedReader(new InputStreamReader(System.in));
        }
        
        public List<Integer> writeGuessNumber() {
            List<Integer> arr = new ArrayList<>();
            try {
                String inputWord = bufferedReader.readLine();
                arr = Arrays.stream(converStringToIntArr(inputWord)).boxed()
                            .collect(Collectors.toUnmodifiableList());
                return arr;
            } catch (IOException e) {
                e.printStackTrace();
            }
            return arr;
            
        }
        
        private static int[] converStringToIntArr(String randomNumbers) {
            int[] converArr = new int[randomNumbers.length()];
            try {
                for (int i = 0; i < randomNumbers.length(); i++) {
                    converArr[i] = Character.getNumericValue(randomNumbers.charAt(i));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return converArr;
        }
    }
    
    public static class BaseBallNumberMaker {
        
        private final int[] answer;
        
        private BaseBallNumberMaker(int[] answer) {
            if (answer.length != 3) {
                throw new IllegalArgumentException("3개의 숫자로 하는 게임임 ");
            }
            this.answer = answer;
        }
        
        public String scoring(String guessNumber) {
            return null;
        }
        
        public static BaseBallNumberMaker from(int randomNumbers) {
            int[] answerArr = converStringToIntArr(randomNumbers);
            return new BaseBallNumberMaker(answerArr);
        }
        
        private static List<Integer> converToIntArr(int randomNumbers) {
            String numberStr = Integer.toString(randomNumbers);
            int[] converArr = new int[numberStr.length()];
            try {
                for (int i = 0; i < numberStr.length(); i++) {
                    converArr[i] = Character.getNumericValue(numberStr.charAt(i));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return converArr;
        }
        
    }
    
}
