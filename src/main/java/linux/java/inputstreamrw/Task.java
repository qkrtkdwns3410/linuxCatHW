package linux.java.inputstreamrw;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;
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
        //바이트 스트림을 문자 스트림으로 변환하여 입력받기 위하여 InputStreamReader를 사용하고,
        // 별도 버퍼링처리를 통해 효율화를 하기위함 + readline API 사용을 하기위해(개행문자 기준으로 입력을 반환하기 위해) BufferReader를 사용했습니다.
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        System.out.println("n = " + n);
        while (n > 0) {
            String[] splited = br.readLine()
                                       .split(" ");
            checkFormat(splited);
            String function = FunctionType.valueOfFunction(splited);
            n--;
        }
    }
    
    private static void execute() {
    
    }
    
    private static void create() {
    
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
    EXECUTE("execute"),
    CREATE("create"),
    ;
    private final String value;
    
    //미리 캐싱으로 빠른 접근 또한 HashMap으로 순회가 필요없음
    private static final Map<String, FunctionType> FUNCTION_TYPE_MAP =
            Stream.of(values())
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
    
    public void run(String function) {
    
    }
    
    
}
