package linux.java.algo.숫자야구S3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.IntStream;

/**
 * packageName    : linux.java.algo.숫자야구S3
 * fileName       : Main
 * author         : ipeac
 * date           : 2023-05-11
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023-05-11        ipeac       최초 생성
 */
public class Main {
    public static void main(String[] args) throws IOException {
        final int BASEBALL_MIN_VALUE = 123;
        final int BASEBALL_MAX_VALUE = 987;
        int ans = 0;
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            int n = Integer.parseInt(br.readLine());
            List<BaseBall> baseBalls = new LinkedList<>();
            for (int i = 0; i < n; i++) {
                String[] splited = br.readLine().split(" ");
                BaseBall ball = BaseBallFactory.create(splited);
                baseBalls.add(ball);
            }
            //123
            //예측한 숫자
            for (int i = BASEBALL_MIN_VALUE; i <= BASEBALL_MAX_VALUE; i++) {
                try {
                    boolean notAvaliableBase = Objects.equals(hasUniqueDigits(i) && hasNoZero(i), false );
                    if (notAvaliableBase) {
                        throw new RuntimeException("생성불가능한 숫자");
                    }
                    BaseBall predicatedNum = BaseBallFactory.createPredicatedNumber(i);
                    for (BaseBall baseBall : baseBalls) {
                        predicatedNum.compareToSB(baseBall);
                    }
                    ans++;
                } catch (RuntimeException ignored) {
                
                }
                
            }
            
        } catch (RuntimeException e) {
            e.printStackTrace();
        } finally {
            System.out.println(ans);
        }
        
    }
    
    public static boolean hasUniqueDigits(int num) {
        String s = String.valueOf(num);
        
        return s.chars().distinct().count() == s.length();
    }
    
    public static boolean hasNoZero(int num) {
        String s = String.valueOf(num);
        
        return s.chars().noneMatch(ch -> ch == '0');
    }
    
}

class BaseBall {
    public int[] baseNumber;
    private int strike;
    private int ball;
    
    public BaseBall(int[] baseNumber) {
        this(baseNumber, 0, 0);
    }
    
    public BaseBall(int[] baseNumber, int strike, int ball) {
        this.baseNumber = baseNumber;
        this.strike = strike;
        this.ball = ball;
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BaseBall ball1 = (BaseBall) o;
        return strike == ball1.strike && ball == ball1.ball && Arrays.equals(baseNumber, ball1.baseNumber);
    }
    
    @Override
    public int hashCode() {
        int result = Objects.hash(strike, ball);
        result = 31 * result + Arrays.hashCode(baseNumber);
        return result;
    }
    
    public boolean compareToSB(BaseBall o) {
        int strike = (int) IntStream.range(0, Math.min(this.baseNumber.length, o.baseNumber.length))
                .filter(i -> this.baseNumber[i] == o.baseNumber[i])
                .count();
        int ball = (int) IntStream.range(0, this.baseNumber.length)
                .filter(i -> this.baseNumber[i] != o.baseNumber[i] && contains(o.baseNumber, this.baseNumber[i]))
                .count();
        boolean diff = !Objects.equals(o.strike, strike) || !Objects.equals(o.ball, ball);
        if (diff) {
            throw new RuntimeException("스트라이크나 볼이 다름");
        }
        return true;
    }
    
    @Override
    public String toString() {
        return new StringJoiner(", ", BaseBall.class.getSimpleName() + "[", "]")
                .add("baseNumber=" + Arrays.toString(baseNumber))
                .add("strike=" + strike)
                .add("ball=" + ball)
                .toString();
    }
    
    public boolean contains(int[] arr, int target) {
        return IntStream.of(arr).anyMatch(num -> num == target);
    }
    
}

class BaseBallFactory {
    public static BaseBall create(String[] splited) throws RuntimeException {
        int[] nums = splited[0].chars().map(Character::getNumericValue).toArray();
        int strike = Integer.parseInt(splited[1]);
        int ball = Integer.parseInt(splited[2]);
        
        return new BaseBall(nums, strike, ball);
    }
    
    public static BaseBall createPredicatedNumber(int i) throws RuntimeException {
        int[] nums = String.valueOf(i).chars().map(Character::getNumericValue).toArray();
        return new BaseBall(nums, 0, 0);
    }
}
