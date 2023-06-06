package linux.java.numberBaseball;

import java.util.*;

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
        long sameCount = Arrays.stream(numbers).distinct().count();
        boolean isDupNumber = sameCount < numbers.length;
        if (isDupNumber) {
            throw new IllegalArgumentException("중복된 BALL 값이 존재합니다");
        }
        if (numbers.length != 3) {
            throw new IllegalArgumentException("야구공이 3개가 주어져야합니다.");
        }
        this.numbers = numbers;
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
