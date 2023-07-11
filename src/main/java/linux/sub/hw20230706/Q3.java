package linux.sub.hw20230706;

import java.text.MessageFormat;
import java.util.Arrays;

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
        System.out.println(q3.solution("kill nine process"));
        System.out.println(q3.solution("vandal is immutable"));
        System.out.println(q3.solution("cks is god"));
    }
    
    public String solution(String word) {
        FROG oldFrog = FROG.from(word);
        FROG newFrog = oldFrog.convertAlpahbet();
        return newFrog.getWord();
    }
    
    public static class FROG {
        private final IllegalArgumentException NULL_ARGUMENT_ERROR = new IllegalArgumentException("NULL값은 허용되지 않습니다");
        private final char[] words;
        
        public FROG(char[] words) {
            if (words == null) {
                throw NULL_ARGUMENT_ERROR;
            }
            if (words.length < 1 || words.length > 1000) {
                String errorMessage = MessageFormat.format("word = {0} 는 1이상 1,000 이하의 길이이여야합니다", Arrays.toString(words));
                throw new IllegalArgumentException(errorMessage);
            }
            this.words = words;
        }
        
        // https://stepbystep1.tistory.com/10
        public FROG convertAlpahbet() {
            int upperAsciiCriterion = (int) 'A' + (int) 'Z';
            int lowerAsciiCritertion = (int) 'a' + (int) 'z';
            char[] newChars = new char[words.length];
            
            for (int index = 0; index < newChars.length; index++) {
                char word = words[index];
                if (!Character.isAlphabetic(word)) {
                    newChars[index] = words[index];
                    continue;
                }
                if (Character.isUpperCase(words[index])) {
                    newChars[index] = (char) (upperAsciiCriterion - (int) words[index]);
                } else if (Character.isLowerCase(words[index])) {
                    newChars[index] = (char) (lowerAsciiCritertion - (int) words[index]);
                }
            }
            return new FROG(newChars);
        }
        
        public String getWord() {
            StringBuilder stringWord = new StringBuilder();
            for (char word : this.words) {
                stringWord.append(word);
            }
            return stringWord.toString();
        }
        
        public static FROG from(String words) {
            return new FROG(words.toCharArray());
        }
        
        @Override
        public String toString() {
            return "FROG{" +
                           ", word=" + Arrays.toString(words) +
                           '}';
        }
    }
}
