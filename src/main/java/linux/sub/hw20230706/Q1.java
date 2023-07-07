package linux.sub.hw20230706;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

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
        System.out.println(Arrays.toString(q1.solution(15000)));
    }
    
    public int[] solution(int money) {
        Bank bank = new Bank(money);
        int[] answers = bank.exchange();
        return answers;
    }
    
    public static class Bank {
        private int money;
        
        private Bank(int money) {
            if (1 > money || money > 1_000_000) {
                throw new IllegalArgumentException("money 는 1 이상 1,000,000 이하의 자연수만 가능합니다.");
            }
            this.money = money;
        }
        //!의도는 있으나, 과연 enum 에서 Bank 클래스 위해 정렬을 수행하는 메서드를 짜서 반환하는 형식이 유효한가에 대한 의구심이 든다. -> 시나리오성이 있는가에 대ㅏ한 고민.
        public int[] exchange() {
            int[] results = new int[Money.values().length];
            List<Money> descWons = Money.getMoneyByWonDesc();
            for (Money money : descWons) {
                // null 위험 제로 `==` <- 주소비교 연산자로 비교 기본자료형은 실제 값을 자바에서 가지고 있다. 13 이면 그냥 13임.
                // ENUM 스펙상 ZeroDiv 에러는 발생할수없음
                int remainer = this.money / money.getWon();
                this.money %= money.getWon();
                results[money.getIndex()] = remainer;
            }
            return results;
        }
        
        // 필요한가..?
/*         public static Bank from(int money) {
            return new Bank(money);
        } */
    }
    
    public enum Money {
        FIFTY_THOUSANDS_BILL(50000, 0),
        TEN_THOUSANDS_BILL(10000, 1),
        FIVE_THOUSANDS_BILL(5000, 2),
        ONE_THOUSAND_BILL(1000, 3),
        FIVE_HUNDREDS_COIN(500, 4),
        ONE_HUNDRED_COIN(100, 5),
        FIFTY_COIN(50, 6),
        TEN_COIN(10, 7),
        ONE_COIN(1, 8),
        ;
        
        private final int won;
        private final int index;
        
        Money(int won, int index) {
            if (won < 1) {
                throw new IllegalArgumentException("won은 양수만 가능합니다");
            }
            if (index < 0) {
                throw new IllegalArgumentException("index 는 0혹은 양수만 가능합니다");
            }
            this.won = won;
            this.index = index;
        }
        
        public int getWon() {
            return this.won;
        }
        
        public int getIndex() {
            return this.index;
        }
        
        public static List<Money> getMoneyByWonDesc() {
            List<Money> descWons = Arrays.asList(Money.values());
            descWons.sort(Comparator.comparingInt(Money::getWon).reversed());
            return descWons;
        }
    }
}
