package linux.sub.hw20230706;

import java.text.MessageFormat;
import java.util.Arrays;
import java.util.Objects;

public class Q4 {
    public static void main(String[] args) {
        Q4 q4 = new Q4();
        System.out.println(q4.solution(new int[]{97, 98}, new int[]{197, 198}));
        System.out.println(q4.solution(new int[]{131, 132}, new int[]{333, 334}));
        System.out.println(q4.solution(new int[]{1, 2}, new int[]{211, 212}));
    }
    
    public int solution(int[] pobi, int[] crong) {
        int answer = -1;
        try {
            Book pobiBook = new Book(pobi);
            Book crongBook = new Book(crong);
            
            Score pobiBiggestScore = pobiBook.calcBigger();
            Score crongBiggestScore = crongBook.calcBigger();
            
            Score winnerScore = pobiBiggestScore.compareGreaterEqualsThan(crongBiggestScore);
            
            boolean pobiSameWinningScore = Objects.equals(winnerScore, pobiBiggestScore);
            boolean crongSameWinningScore = Objects.equals(winnerScore, crongBiggestScore);
            
            if (pobiSameWinningScore && crongSameWinningScore) {
                answer = 0;
            } else if (pobiSameWinningScore) {
                answer = 1;
            } else if (crongSameWinningScore) {
                answer = 2;
            }
        } catch (RuntimeException e) {
            e.printStackTrace();
        }
       
        return answer;
    }
    
    public static class Book {
        private static final IllegalArgumentException NULL_ARGUMENT_EXCEPTION = new IllegalArgumentException("Null 값은 허용되지 않습니다");
        private static final int START_PAGE = 1;
        private static final int END_PAGE = 400;
        private final int[] pages;
        
        public Book(int[] pages) {
            if (pages == null) {
                throw NULL_ARGUMENT_EXCEPTION;
            }
            
            if (pages.length != 2) {
                String errorMessage = MessageFormat.format("pages => {0} 는 길이가 2이여야 합니다.", Arrays.toString(pages));
                throw new IllegalArgumentException(errorMessage);
            }
            
            for (int page : pages) {
                if (page < START_PAGE || page > END_PAGE) {
                    String errorMessage = MessageFormat.format("page => {0} 는 {1} 이상 {2} 이하의 페이지여야합니다", page, START_PAGE, END_PAGE);
                    throw new IllegalArgumentException(errorMessage);
                }
                if (page == START_PAGE || page == END_PAGE) {
                    String errorMessage = MessageFormat.format("page => {0} 는 시작 면 => {1} 이나 마지막 면이 => {2} 이 나오도록 책을 펼칠수 없습니다", page, START_PAGE, END_PAGE);
                    throw new IllegalArgumentException(errorMessage);
                }
            }
            if (pages[0] + 1 != pages[1]) {
                String errorMessage = MessageFormat.format("pages => {0} 는 연속된 페이지여야합니다", Arrays.toString(pages));
                throw new IllegalArgumentException(errorMessage);
            }
            if (pages[0] % 2 == 0 && pages[1] % 2 != 0) {
                String errorMessage = MessageFormat.format("pages => {0} 는 홀수 | 짝수 조합이여야합니다.", Arrays.toString(pages));
                throw new IllegalArgumentException(errorMessage);
            }
            
            this.pages = pages;
        }
        
        public Score calcBigger() {
            Score tempScore = null;
            for (int index = 0; index < pages.length; index++) {
                int page = pages[index];
                Score sumScore = sumDigits(page);
                Score multiflyScore = multiplyDigits(page);
                Score biggerScore = sumScore.compareGreaterEqualsThan(multiflyScore);
                if (tempScore == null) {
                    tempScore = biggerScore;
                    continue;
                }
                tempScore = tempScore.compareGreaterEqualsThan(biggerScore);
            }
            
            return tempScore;
        }
        
        public Score sumDigits(int value) {
            int result = 0;
            int tempResult = value;
            while (tempResult != 0) {
                result += tempResult % 10;
                tempResult /= 10;
            }
            return new Score(result);
        }
        
        public Score multiplyDigits(int value) {
            int result = 1;
            int tempResult = value;
            while (tempResult != 0) {
                result *= tempResult % 10;
                tempResult /= 10;
            }
            return new Score(result);
        }
        
        @Override
        public String toString() {
            return "Book{" +
                           "pages=" + Arrays.toString(pages) +
                           '}';
        }
    }
    
    public static class Score {
        private static final IllegalArgumentException NULL_ARGUMENT_EXCEPTION = new IllegalArgumentException("Null 값은 허용되지 않습니다");
        private final int value;
        
        public Score(int value) {
            this.value = value;
        }
        
        public int getValue() {
            return value;
        }
        
        public Score compareGreaterEqualsThan(Score other) {
            if (other == null) {
                throw NULL_ARGUMENT_EXCEPTION;
            }
            if (this.value >= other.value) {
                return new Score(this.value);
            }
            return new Score(other.value);
        }
        
        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            
            Score score = (Score) o;
            
            return value == score.value;
        }
        
        @Override
        public int hashCode() {
            return value;
        }
        
        @Override
        public String toString() {
            return "Score{" +
                           "value=" + value +
                           '}';
        }
    }
}
