package linux.sub.hw20230706;

import java.text.MessageFormat;

/**
 * packageName    : linux.sub.hw20230706
 * fileName       : Q3
 * author         : ipeac
 * date           : 2023-07-10
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023-07-10        ipeac       최초 생성
 */
public class Q3 {
    public static void main(String[] args) {
        Q3 q3 = new Q3();
        System.out.println(q3.solution("I love you"));
        // System.out.println(q3.solution("kill nine"));
        // System.out.println(q3.solution("java is hope"));
        // System.out.println(q3.solution("cks is god"));
    }
    public String solution(String word) {
        String answer = "";
        
        return answer;
    }
    
    public static class FROG {
        private final IllegalArgumentException NULL_ARGUMENT_ERROR = new IllegalArgumentException("NULL값은 허용되지 않습니다");
        private final String word;
        
        public FROG(String word) {
            if (word == null) {
                throw NULL_ARGUMENT_ERROR;
            }
            if (word.length() < 1 || word.length() > 1000) {
                String errorMessage = MessageFormat.format("word = {0} 는 1이상 1,000 이하의 문자열이여야합니다", word);
                throw new IllegalArgumentException(errorMessage);
            }
            this.word = word;
        }
        
        @Override
        public String toString() {
            return "FROG{" +
                           "word='" + word + '\'' +
                           '}';
        }
    }
}
