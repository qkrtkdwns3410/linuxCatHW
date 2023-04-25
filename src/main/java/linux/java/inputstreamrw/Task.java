package linux.java.inputstreamrw;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * packageName    : linux.java.inputstreamrw
 * fileName       : Task
 * author         : ipeac
 * date           : 2023-04-23
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023-04-23        ipeac       최초 생성
 */
public class Task {
    static int n;
    
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
        n = Integer.parseInt(br.readLine());
        System.out.println("n = " + n);
        while (n > 0) {
            String[] splited = br.readLine()
                                       .split(" ");
            checkFormat(splited);
            //ENUM 타입에서 밸류값의 순회보다 static hashmap에 미리 캐싱해두고 값을 사용하는 방법이 더 효율적인 것 같음
            // 하지만 종류가 많아지는 경우 메모리상에 불이익이 있지않을까 생각은 되는데, 기능이 많아봤자 얼마나 많아지겠어 라는 생각을 함
            String function = FunctionType.valueOfFunction(splited);
            //일단 분기는 필요하다..
            switch (function) {
                case "execute":
                    try {
                        int tagNum = Integer.parseInt(splited[1]);
                        boolean executable = !tagQueue.contains(tagNum);
                        if (executable) {
                            tagQueue.add(tagNum);
                            break;
                        }
                        throw new IllegalArgumentException("해당 태그를 수행할수 없습니다.");
                    } catch (IllegalArgumentException e) {
                        executeFailMap.put(splited[1], executeFailMap.getOrDefault(splited[1], 0) + 1);
                        e.printStackTrace();
                    }
                case "create":
                    try {
                        //log n false->NPE
                        tagQueue.poll();
                        break;
                    } catch (NullPointerException e) {
                        createFailCnt += 1;
                        e.printStackTrace();
                    }
                default:
                    break;
            }
            n--;
        }
        
    }
    
    public static void checkFormat(String[] splited) {
        try {
            String function = splited[0];
            isLetter(function);
            String tagNum = splited[1];
            isDigit(tagNum);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (ArrayIndexOutOfBoundsException ignored) {
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
}

enum FunctionType {
    EXECUTE("execute"), CREATE("create"),
    ;
    private final String value;
    
    //미리 캐싱으로 빠른 접근 또한 HashMap으로 순회가 필요없음
    private static final Map<String, FunctionType> FUNCTION_TYPE_MAP = Stream.of(values())
                                                                               .collect(Collectors.toMap(FunctionType::getValue, v -> v));
    
    FunctionType(String value) {
        this.value = value;
    }
    
    public static String valueOfFunction(String[] function) {
        String name = "";
        try {
            name = FUNCTION_TYPE_MAP.get(function[0])
                           .getValue();
        } catch (NullPointerException e) {
            System.out.println("올바른 함수가 아닙니다.");
        }
        return name;
    }
    
    public String getValue() {
        return value;
    }
    
}
