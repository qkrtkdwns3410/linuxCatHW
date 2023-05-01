package linux.java.algo.프린터큐1966;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * packageName    : linux.java.algo.프린터큐1966
 * fileName       : Main
 * author         : ipeac
 * date           : 2023-04-29
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023-04-29        ipeac       최초 생성
 */
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        try {
            int tasecase = Integer.parseInt(br.readLine());
            List<Integer> answer = new ArrayList<>();
            
            for (int i = 0; i < tasecase; i++) {
                //몇번째 출력되는지 기록을 위함
                int count = 0;
                
                String[] splited = br.readLine()
                                           .split(" ");
                inRange(splited);
                //문서의 인덱스와 우선순위의 기억을 위해서
                Queue<Document> documentQueue = new LinkedList<>();
                //최대힙으로 우선순위높은 순으로 빼내기위해
                PriorityQueue<Integer> priorityQueue = new PriorityQueue<>(Collections.reverseOrder());
                int n = Integer.parseInt(splited[0]);
                int m = Integer.parseInt(splited[1]);
                String[] numbers = br.readLine()
                                           .split(" ");
                for (int j = 0; j < n; j++) {
                    int number = Integer.parseInt(numbers[j]);
                    documentQueue.add(new Document(j, number));
                    priorityQueue.add(number);
                }
//                System.out.println("documentQueue = " + documentQueue);
                while (!documentQueue.isEmpty()) {
                    //1. 큐 가장 앞에 있는 문서 중요도 체크
                    //2. 나머지 문서들 중 현재 문서보다 중요보가 높은 문서가 하나라도 있으면 , 큐 뒤에 배치
                    Document currentDocument = documentQueue.poll();
                    //최고 우선순위 체크
                    boolean isTopPriority = Objects.equals(currentDocument.priority, priorityQueue.peek());
                    if (isTopPriority) {
                        count++;
                        priorityQueue.poll();
                        boolean isWantedTarget = currentDocument.index == m;
                        if (isWantedTarget) {
                            answer.add(count);
                        }
                    } else {
                        documentQueue.add(currentDocument);
                    }
                }
            }
            
            print(answer);
        } catch (IllegalArgumentException | ArrayIndexOutOfBoundsException exception) {
            exception.printStackTrace();
        }
    }
    
    public static <T> void print(List<T> answer) {
        for (T t : answer) {
            System.out.println(t);
        }
    }
    
    public static void inRange(String[] splited) {
        int n = Integer.parseInt(splited[0]);
        int m = Integer.parseInt(splited[1]);
        if (!(1 <= n && n <= 100)) {
            throw new IllegalArgumentException("올바른 범위의 문서 개수(n)가 아닙니다");
        }
        if (!(0 <= m && m < n)) {
            throw new IllegalArgumentException("올바른 범위의 인쇄 인덱스(m)가 아닙니다.");
        }
    }
    
    static class Document {
        int index;
        int priority;
        
        public Document(int index, int priority) {
            this.index = index;
            this.priority = priority;
        }
        
        @Override
        public String toString() {
            return new StringJoiner(", ", Document.class.getSimpleName() + "[", "]")
                           .add("index=" + index)
                           .add("priority=" + priority)
                           .toString();
        }
    }
}
