package linux.java.inputstreamrw;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Task {
    
    public static void main(String[] args) throws IOException {
        //자바에서의 우선순위큐는 기본적으로 최소힙으로 구현되어있다. 파이썬도 그랬는데 ..
        //https://www.notion.so/2023-05-06-02988f0048d949758745324c31df05ce?pvs=4#43ba9027975e45618b1c7d6fe10a0671 - > 정리및 정정
        Queue<Integer> tagQueue = new PriorityQueue<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9));
        Map<String, Integer> executeFailMap = new HashMap<>();
        //사실 정수 자체를 매개변수에서 객체로 넘겨주기 위해서 해당 자료구조를 이용했음.
        //Integer의 경우 참조형타입이라 매개변수로 넘기면 해당 Integer값이 변경된다고 생각할수도 있다.
        //하지만 Integer는 immmutable 한 클래스이다. Integer의 값이 변경되면 새로운 Integer 객체가 생성되어
        // 원본값을 유지할수 없는 것이다.
        
        int createFailCnt = 0;
        
        //바이트 스트림을 문자 스트림으로 변환하여 입력받기 위하여 InputStreamReader를 사용하고,
        // 별도 버퍼링처리를 통해 효율화를 하기위함 + readline API 사용을 하기위해(개행문자 기준으로 입력을 반환하기 위해) BufferReader를 사용했습니다.
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        System.out.println("n = " + n);
        while (n > 0) {
            try {
                String[] splited = br.readLine()
                                           .split(" ");
                System.out.println("splited = " + Arrays.toString(splited));
                checkFormat(splited);
                //ENUM 타입에서 밸류값의 순회보다 static hashmap에 미리 캐싱해두고 값을 사용하는 방법이 더 효율적인 것 같음
                // 하지만 종류가 많아지는 경우 메모리상에 불이익이 있지않을까 생각은 되는데, 기능이 많아봤자 얼마나 많아지겠어 라는 생각을 함
                FunctionType function = FunctionType.valueOfFunction(splited);
                //일단 분기는 필요하다..
                switch (function) {
                    case EXECUTE:
                        try {
                            //이부분도 단순하게 만들어 보려고 했으나, 분리처리하는방법말고는 생각이 나질않음.
                            //태그저장고라는 tagQueue 때문에 분리처리로 강제되는것 같음. 일단 없어야 실행이 가능해지니까
                            int tagNum = Integer.parseInt(splited[1]);
                            boolean notExecutable = tagQueue.contains(tagNum);
                            if (notExecutable) {
                                executeFailMap.put(String.valueOf(tagNum), executeFailMap.getOrDefault(String.valueOf(tagNum), 0) + 1);
                                throw new IllegalArgumentException("해당 태그를 수행할수 없습니다.");
                            }
                            tagQueue.add(tagNum);
                            n--;
                        } catch (IllegalArgumentException e) {
                            e.printStackTrace();
                        }
                        break;
                    case CREATE:
                        try {
                            n--;
                            //log n false->NPE
                            if (tagQueue.isEmpty()) {
                                throw new NoSuchElementException("생성가능한 태그가 없습니다");
                            }
                            tagQueue.poll();
                        } catch (NoSuchElementException e) {
                            createFailCnt += 1;
                            e.printStackTrace();
                        }
                        break;
                    default:
                        break;
                }
                
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            }
            
        }
        //출력
        System.out.print("사용가능한 TAG:");
        printQueue(tagQueue);
        System.out.println("TASK 생성 실패: " + createFailCnt);
        System.out.print("TASK 수행 실패한 태그");
        printMap(executeFailMap);
    }
    
    public static void printMap(Map<?, ?> map) {
        for (Map.Entry<?, ?> entry : map.entrySet()) {
            System.out.print(entry.getKey() + "(" + entry.getValue() + ") ");
        }
        System.out.println();
    }
    
    public static void printQueue(Queue<?> queue) {
        Iterator<?> iterator = queue.iterator();
        while (iterator.hasNext()) {
            System.out.print(iterator.next() + " ");
        }
        System.out.println();
    }
    
    
    public static void checkFormat(String[] splited) throws ArrayIndexOutOfBoundsException {
        try {
            String function = splited[0];
            isLetter(function);
            String tagNum = splited[1];
            isDigit(tagNum);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("올바른 형식의 인수가 아닙니다");
        }
    }
    
    public static void isLetter(String value) {
        for (int i = 0; i < value.length(); i++) {
            char c = value.charAt(i);
            boolean isDigit = Character.isLetter(c);
            if (!isDigit) {
                throw new IllegalArgumentException("문자형이 아닙니다");
            }
        }
    }
    
    public static void isDigit(String value) {
        for (int i = 0; i < value.length(); i++) {
            char c = value.charAt(i);
            boolean isNotDigit = !Character.isDigit(c);
            if (isNotDigit) {
                throw new IllegalArgumentException("숫자형이 아닙니다.");
            }
        }
    }
    
    public enum FunctionType {
        EXECUTE("execute"), CREATE("create"),
        ;
        private final String value;
        
        //미리 캐싱으로 빠른 접근 또한 HashMap으로 순회가 필요없음
        private static final Map<String, FunctionType> FUNCTION_TYPE_MAP = Stream.of(values())
                                                                                   .collect(Collectors.toMap(FunctionType::getValue, v -> v));
        
        FunctionType(String value) {
            this.value = value;
        }
        
        public static FunctionType valueOfFunction(String[] function) {
            try {
                FunctionType result = FUNCTION_TYPE_MAP.get(function[0]);
                if (result == null) {
                    throw new IllegalArgumentException("올바른 함수가 아닙니다.");
                }
                
                return result;
            } catch (NullPointerException e) {
                System.out.println("올바른 함수가 아닙니다.");
                throw e;
            }
        }
        
        public String getValue() {
            return value;
        }
        
    }
}


