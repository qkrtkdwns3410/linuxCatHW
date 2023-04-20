package linux.homework;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

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
public class Main {
    //최대한 추상화 하여, 공통 범용적인 시스템을 만들어야함. 실행 ? 성공 : 실패
    //정답을 찾지말고 연구해라
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        //====================선언 및 입력=======================//
        // 생성되지 않은 태그
        TreeSet<Integer> notCreatedTask = new TreeSet<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9));
        //생성실패 카운트
        AtomicInteger createFailCnt = new AtomicInteger();
        //실행실패에 대한 카운트 기록
        Map<Integer, Integer> executeFailMap = new TreeMap<>();
        //라인단위 + 메모리 입력스트림 + 버퍼링
        StringTokenizer st = new StringTokenizer(br.readLine());
        //반복 횟수
        int n = Integer.parseInt(st.nextToken());
        
        while (n-- > 0) {
            st = new StringTokenizer(br.readLine(), " ");
            List<String> attributes = parseInputLines(st);//데이터 파싱
            //=========유효성 검증=============//
            int validatorReturn = validator(attributes, notCreatedTask, createFailCnt, executeFailMap); // 유효성 검증
            boolean isNotValid = validatorReturn == -1;
            boolean isNotValidParam = validatorReturn == 0;
            if (isNotValid) {
                n++;
                continue;
            } else if (isNotValidParam) {
                continue;
            }
            //=========유효성 검증완료=============//
            //넘어오는 데이터는 모두 유효성이 검증됨.
            boolean executeCheck = Objects.equals(attributes.get(0), "execute");
            boolean createCheck = Objects.equals(attributes.get(0), "create");
            if (executeCheck) {
                int tagNum = Integer.parseInt(attributes.get(1));
                execute(tagNum, notCreatedTask);
            } else if (createCheck) {
                create(notCreatedTask);
            }
        }
        printUsableTag(notCreatedTask);
        printCreatFailCnt(createFailCnt.get());
        printExecuteFailTag(executeFailMap);
    }
    
    //====================선언 및 입력 종료=======================//
    
    private static List<String> parseInputLines(StringTokenizer st) {
        List<String> attributes = new ArrayList<>();
        while (st.hasMoreElements()) {
            attributes.add(st.nextToken());
        }
        return attributes;
    }
    
    
    /**
     * 입력값의 유효성을 검증
     *
     * @param attributes
     * @param notCreatedTask
     * @param createFailCnt
     * @param executeFailMap
     * @return <pre>0=> 유효하지않은 매개변수 인 경우</pre><pre> 1 => 유효한 형식이 아닌경우</pre><pre>2=> 유효성 검증 통과</pre>
     */
    public static int validator(List<String> attributes, TreeSet<Integer> notCreatedTask, AtomicInteger createFailCnt, Map<Integer, Integer> executeFailMap) {
        if (attributes.isEmpty()) {
            return 1;
        }
        boolean executeCheck = Objects.equals(attributes.get(0), "execute");
        boolean createCheck = Objects.equals(attributes.get(0), "create");
        boolean sizeCheck;
        if (executeCheck) {
            try {
                sizeCheck = attributes.size() != 2;
                int tagNum = Integer.parseInt(attributes.get(1));
                boolean tagNumValid = (1 <= tagNum && tagNum <= 9);
                boolean NotExcutableTagNum = notCreatedTask.contains(tagNum);
                if (NotExcutableTagNum || !tagNumValid) {
                    executeFailMap.put(tagNum, executeFailMap.getOrDefault(tagNum, 0) + 1);
                    if (!tagNumValid) {
                        return 0;
                    }
                    return 1;
                }
            } catch (NumberFormatException e) {
                System.out.println("execute [태그넘버 : 숫자] 만 가능합니다.");
                return 1;
            }
            
        } else if (createCheck) {
            if (notCreatedTask.isEmpty()) {
                createFailCnt.getAndAdd(1);
            }
            sizeCheck = attributes.size() != 1;
            
        } else {
            System.out.println("올바른 함수가 아닙니다.");
            return 1;
        }
        
        if (sizeCheck) {
            System.out.println("명령어의 개수가 맞지 않습니다.");
            return 1;
        }
        
        return 2;
    }
    
    public static void execute(int tagNum, TreeSet<Integer> notCreatedTask) {
        //수행된 태그 다시 저장함
        notCreatedTask.add(tagNum);
    }
    
    public static void create(TreeSet<Integer> notCreatedTask) {
        //TAG 생성으로 태그저장고 제외
        notCreatedTask.pollFirst();
    }
    
    public static void printUsableTag(TreeSet<Integer> notCreatedTask) {
        System.out.print("사용가능한 TAG : ");
        notCreatedTask.forEach(integer -> System.out.print(integer + " "));
        System.out.println();
    }
    
    public static void printCreatFailCnt(Integer createFailCnt) {
        System.out.print("TASK 생성 실패: " + createFailCnt);
        System.out.println();
    }
    
    public static void printExecuteFailTag(Map<Integer, Integer> executeFailMap) {
        System.out.print("TASK 수행 실패한 태그: ");
        executeFailMap.entrySet()
                .stream()
                .sorted(
                        Map.Entry.<Integer, Integer>comparingByValue()
                                .reversed()
                                .thenComparing(Map.Entry.comparingByKey())
                )
                .forEach(entry -> System.out.print(entry.getKey() + "(" + entry.getValue() + ") "));
        //Map value 값
    }
    
}


