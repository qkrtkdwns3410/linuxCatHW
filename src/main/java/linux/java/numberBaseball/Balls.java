package linux.java.numberBaseball;

import java.util.*;

import static linux.java.numberBaseball.Message.DUPLICATE_BALL_ALERT;
import static linux.java.numberBaseball.Message.LESS_THAN_THREE_BALL;

/**
 * packageName    : linux.java.numberBaseball
 * fileName       : Balls
 * author         : ipeac
 * date           : 2023-05-31
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023-05-31        ipeac       최초 생성
 */
public class Balls {
    private final int[] numbers;
    
    private Balls(int[] numbers) {
        validate(numbers);
        this.numbers = numbers;
    }
    
    private void validate(int[] numbers) {
        long sameCount = Arrays.stream(numbers).distinct().count();
        boolean isDupNumber = sameCount < numbers.length;
        if (isDupNumber) {
            throw new IllegalArgumentException(DUPLICATE_BALL_ALERT.getMessage());
        }
        if (numbers.length != 3) {
            throw new IllegalArgumentException(LESS_THAN_THREE_BALL.getMessage());
        }
    }
    
    public static Balls generateRandomBalls() {
        int[] randomNumbers = generateRandomNumber();
        System.out.println("randomNumber = " + Arrays.toString(randomNumbers));
        return new Balls(randomNumbers);
    }
    
    public static Balls from(String numbers) {
        String[] split = numbers.split("");
        int[] intNumbers = Arrays.stream(split).mapToInt(Integer::parseInt).toArray();
        return new Balls(intNumbers);
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
    
    public int[] getNumbers() {
        return numbers;
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
