package linux.homework;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * packageName    : linux.homework
 * fileName       : Task
 * author         : ipeac
 * date           : 2023-04-16
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023-04-16        ipeac       최초 생성
 */
public class Task {
    public static TreeSet<Integer> TAG = new TreeSet<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9)); // 태그 저장고 역할
    public static int createFailCnt = 0;
    public static Map<Integer, Integer> executeFailcntMap = new HashMap<>();
    
    //해당 문제를 예외처리까지 생각하는지 궁금합니다.
    public static void main(String[] args) throws IOException {
        FastReader rd = new FastReader();
        int n = rd.nextInt();
        while (n > 0) {
            String function = rd.next();
            if (Objects.equals(function, "create")) {
                create();
            } else if (Objects.equals(function, "execute")) {
                int tagNum = rd.nextInt();
                execute(tagNum);
            } else {
                System.out.println("잘못된 입력");
                continue;
            }
            n--;
        }
        //사용가능한 태그 출력
        System.out.print("사용가능한 TAG: ");
        for (Integer integer : TAG) {
            System.out.print(integer + " ");
        }
        System.out.println();
        
        //생성실패 태그 출력
        System.out.println("TASK 생성 실패: " + createFailCnt);
        
        //수행 실패한 태그 출력(내림차순)
        List<Map.Entry<Integer, Integer>> entries = new ArrayList<>(executeFailcntMap.entrySet());
        //밸류 기준 내림차순 정렬
        entries.sort(new Comparator<Map.Entry<Integer, Integer>>() {
            @Override
            public int compare(Map.Entry<Integer, Integer> o1, Map.Entry<Integer, Integer> o2) {
                //밸류 기준 내림차순 정렬
                int returnValue = o2.getValue()
                                          .compareTo(o1.getValue());
                //밸류값이 동일한 경우 키값으로 오름차순
                if (returnValue == 0) {
                    returnValue = o1.getKey()
                                          .compareTo(o2.getKey());
                }
                return returnValue;
            }
        });
        System.out.print("TASK 수행 실패한 태그: ");
        for (Map.Entry<Integer, Integer> entry : entries) {
            System.out.print(entry.getKey() + "(" + entry.getValue() + ") ");
        }
    }
    
    
    /**
     * 태그는 가장 작은 값으로 할당
     * 태그가 없다면 생성 실패 횟수 집계
     */
    public static void create() {
        Integer polledFirst = TAG.pollFirst();
        boolean NotCreatable = polledFirst == null;
        if (NotCreatable) createFailCnt++;
    }
    
    /**
     * 수행할 수 있는 태그가 아니면, 수행에 실패한 태그 실패 횟수 집계
     *
     * @param tagNum 태그 번호
     */
    public static void execute(int tagNum) {
        boolean isExecutable = !TAG.contains(tagNum) && 1 <= tagNum && tagNum <= 9;
        //해당 태그가 태그 저장고에 없다면 수행가능합니다.
        if (isExecutable) {
            //수행
            TAG.add(tagNum);
        } else {
            //수행불가능함
            int value = executeFailcntMap.getOrDefault(tagNum, 0);
            executeFailcntMap.put(tagNum, value + 1);
        }
    }
    
    static class FastReader {
        BufferedReader br;
        StringTokenizer st;
        
        public FastReader() {
            //바이트 기반 스트림을 문자기반 스트림(인콧딩)으로 연결시켜주는 InputStreamReader
            br = new BufferedReader(new InputStreamReader(System.in));
        }
        
        String next() throws IOException {
            //st객체가 null 이거나 , 더이상 불러올 요소가 없을때까지 대기함
            while (st == null || !st.hasMoreElements()) {
                st = new StringTokenizer(br.readLine());
                
            }
            //st 에서 토큰값 하나씩 반환함
            return st.nextToken();
        }
        
        int nextInt() throws IOException {
            //반환된 값을 int로 변환
            return Integer.parseInt(next());
        }
    }
}
