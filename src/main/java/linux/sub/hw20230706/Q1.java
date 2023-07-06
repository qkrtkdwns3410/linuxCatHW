package linux.sub.hw20230706;

import java.util.Optional;

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
    
    }
    
    public int[] solution(int money) {
        int[] answers = new int[0];
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
        
        public int[] exchange() {
            int[] results = new int[Money.values().length];
            for (Money money : Money.values()) {
            
            }
        }
        
        // 필요한가..?
        public static Bank from(int money) {
            return new Bank(money);
        }
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
            this.won = won;
            this.index = index;
        }
        
        public int getWon() {
            return this.won;
        }
        
        public static int calculate(int won) {
            for (Money money : Money.values()) {
                // Objects.equals 를 사용하려고 생각이 들었으나, 기본자료형에는 null 이 없어서 불필요한 과정임
                if (money.getWon() == won) {
                
                }
            }
            return Optional.empty();
        }
    }
}
