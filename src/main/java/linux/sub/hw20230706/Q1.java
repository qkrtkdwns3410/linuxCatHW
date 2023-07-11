package linux.sub.hw20230706;

import java.text.MessageFormat;
import java.util.*;

/**
 * packageName    : linux.sub.hw20230706
 * fileName       : Q1
 * author         : ipeac
 * date           : 2023-07-06
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023-07-06        ipeac       최초 생성
 */
public class Q1 {
    public static void main(String[] args) {
        Q1 q1 = new Q1();
        System.out.println(Arrays.toString(q1.solution(50237)));
        // System.out.println(Arrays.toString(q1.solution(15000)));
        // System.out.println(Arrays.toString(q1.solution(55555)));
    }
    
    public int[] solution(int money) {
        
        Money remainMoney = new Money(money);
        List<Money> monies = new ArrayList<>(List.of(
                new Money(50_000),
                new Money(10_000),
                new Money(5_000),
                new Money(1_000),
                new Money(500),
                new Money(100),
                new Money(50),
                new Money(10),
                new Money(1)
        ));
        // monies.sort(new MoneyAscComparator().reversed());
        Collections.sort(monies);
        // System.out.println("monies = " + monies);
        
        List<Integer> resultCount = new ArrayList<>();
        for (Money comparableMoney : monies) {
            if (remainMoney.isSmallerThan(comparableMoney)) {
                continue;
            }
            
            int countPerMoney = remainMoney.countPer(comparableMoney);
            Money minusMoney = comparableMoney.times(countPerMoney);
            
            remainMoney = remainMoney.minus(minusMoney);
            
            resultCount.add(countPerMoney);
        }
        
        return toArray(resultCount);
    }
    
    private static int[] toArray(List<Integer> integers) {
        int[] resultInts = new int[integers.size()];
        int resultIntsIndex = 0;
        for (int count : integers) {
            resultInts[resultIntsIndex] = count;
            resultIntsIndex++;
        }
        return resultInts;
    }
    
    public static class MoneyAscComparator implements Comparator<Money> {
        @Override
        public int compare(Money o1, Money o2) {
            return o1.value - o2.value;
        }
        
    }
    
    public static class Money implements Comparable<Money> {
        private static final IllegalArgumentException NULL_ARGUMENT_EXCEPTION = new IllegalArgumentException("NULL 값은 허용되지 않습니다");
        
        private final int value;
        
        public Money(int value) {
            if (value < 1 || 1_000_000 < value) {
                String errorMsg = MessageFormat.format("{0} 은 유효한 금액이 아닙니다.", value);
                throw new IllegalArgumentException(errorMsg);
            }
            this.value = value;
        }
        
        // 금액을 나누다.
        public int countPer(Money otherMoney) {
            if (otherMoney == null) {
                throw NULL_ARGUMENT_EXCEPTION;
            }
            return this.value / otherMoney.value;
        }
        
        public Money times(int count) {
            if (count < 1) {
                String errorMessage = MessageFormat.format("{0} 값은 1 이상이여야 합니다.", count);
                throw new IllegalStateException(errorMessage);
            }
            return new Money(count * this.value);
        }
        
        public Money minus(Money otherMoney) {
            if (otherMoney == null) {
                throw NULL_ARGUMENT_EXCEPTION;
            }
            return new Money(this.value - otherMoney.value);
        }
        
        public boolean isSmallerThan(Money ohterMoney) {
            if (ohterMoney == null) {
                throw NULL_ARGUMENT_EXCEPTION;
            }
            return this.value < ohterMoney.value;
        }
        
        @Override
        public int compareTo(Money o) {
            return o.value - this.value;
        }
        
        @Override
        public String toString() {
            return "Money{" +
                           "value=" + value +
                           '}';
        }
    }
    
    
}
