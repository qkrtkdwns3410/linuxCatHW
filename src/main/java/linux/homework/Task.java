package linux.homework;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Task {
    
    public static void main(String[] args) throws IOException {
        Queue<Integer> tagQueue = new PriorityQueue<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9));
        Map<String, Integer> executeFailMap = new HashMap<>();
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        System.out.println("n = " + n);
        while (n > 0) {
            try {
                String[] splited = br.readLine()
                        .split(" ");
                System.out.println("splited = " + Arrays.toString(splited));
                FunctionType function = FunctionType.valueOfFunction(splited);
                switch (function) {
                    case EXECUTE:
                        try {
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


