package linux.java.algo.후위표기식2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Random;

/**
 * packageName    : linux.java.algo.후위표기식2
 * fileName       : Main
 * author         : ipeac
 * date           : 2023-05-05
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023-05-05        ipeac       최초 생성
 */
public class Main {
    public static void main(String[] args) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            System.out.println("프로그래밍의 구조란?");
            String inputString = "단단하고 격리된 개별 컨텍스트 공간을 구축하는 것을 의미한다.";
            System.out.println(simplify(inputString));
            
            System.out.println();
            System.out.println("입력 컨텍스트");
            System.out.println(simplify("사용자의 입력을 시스템이 이해할 수 있는 명확하게 캐시된 형태로 변환한다." +
                    " || 이때 유효하지 않는 사용자 입력이라면 " +
                    "시스템에 넘기지 않는다. ||" +
                    "오로지 [명령어] [태그] 그냥 2개의 값만 들어온다는 사실만을 입력 컨텍스트는 인지한다."));
            
            System.out.println();
            System.out.println("처리 컨텍스트");
            System.out.println(
                    simplify("시스템의 관점에서 추상화를 통해서 시스템을 단순화한다.  || " +
                            "시스템 처리에 필요한 결과도 단순화한다."));
            
            System.out.println();
            System.out.println("출력 컨텍스트");
            System.out.println(simplify("" +
                    "출력을 위해서 필요한 최소한의 데이터를 설계를 하고 해당 데이터로 " +
                    "출력의 니즈를 해결한다."));
        } catch (IOException | ArrayIndexOutOfBoundsException | NumberFormatException e) {
            e.printStackTrace();
        }
    }
    
    public static String simplify(String sentence) {
        String[] words = sentence.split("\\s+"); // 문장을 공백 단위로 나눠서 단어 배열 생성
        int numOfBlanks = words.length / 3; // 빈칸으로 바꿀 단어의 수 계산
        Random rand = new Random(); // 랜덤 객체 생성
        
        // 랜덤하게 선택된 인덱스의 단어를 빈칸으로 변경
        for (int i = 0; i < numOfBlanks; i++) {
            int idx = rand.nextInt(words.length);
            words[idx] = "____";
        }
        
        // 빈칸으로 변경된 단어를 다시 문장으로 연결하여 반환
        return String.join(" ", words);
    }
    
}
