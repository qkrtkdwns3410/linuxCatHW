package linux.java.inputstreamrw;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Objects;
import java.util.StringJoiner;
import java.util.StringTokenizer;

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
            String lines = br.readLine();
            String[] splited = lines.split(" ");
            MessageSender msgResult = checkFormat(lines);
            System.out.println("msgResult = " + msgResult.getMessage());
            boolean isFalse = Objects.equals(msgResult.isStatus(), false);
            if (isFalse) {
                System.out.println(msgResult.getMessage());
                continue;
            }
            String function = splited[0];
            MessageSender returnedFunction = isExistFunction(function);
            boolean isNotRightFunction = Objects.equals(returnedFunction.isStatus(), false);
            if (isNotRightFunction) {
                System.out.println(returnedFunction.getMessage());
                continue;
            }
            
            
        }
    }
    
    public static MessageSender isExistFunction(String funtion) {
        String returned;
        try {
            returned = FunctionType.getValue(funtion);
            
        } catch (IllegalArgumentException e) {
            return new MessageSender("해당 함수는 존재하지 않습니다", false);
        }
        return new MessageSender("올바른 함수입니다.", true, returned);
        
    }
    
    
    //입력 콘텍스트..
    //첫번째 값이 문자열인지 두번째값이 숫자형인지 체크
    //뭐가 들어오든간 난 상관안합니데이
    public static MessageSender checkFormat(String lines) {
        StringTokenizer st = new StringTokenizer(lines, " ");
        String function = st.nextToken();
        boolean isLetter = isLetter(function);
        if (!isLetter) {
            return new MessageSender("첫번째 인수가 문자열이 아닙니다.", false);
        }
        if (st.hasMoreElements()) {
            String tagNum = st.nextToken();
            boolean isDigit = isDigit(tagNum);
            if (!isDigit) {
                return new MessageSender("두번째 인수가 숫자형이 아닙니다.", false);
            }
        }
        return new MessageSender("올바른 인수입니다.", true);
    }
    
    public static boolean isLetter(String value) {
        for (int i = 0; i < value.length(); i++) {
            char c = value.charAt(i);
            boolean isDigit = Character.isLetter(c);
            if (!isDigit) {
                return false;
            }
        }
        return true;
    }
    
    public static boolean isDigit(String value) {
        for (int i = 0; i < value.length(); i++) {
            char c = value.charAt(i);
            boolean isNotDigit = !Character.isDigit(c);
            if (isNotDigit) {
                return false;
            }
        }
        return true;
    }
}

class MessageSender {
    private String message;
    private boolean status;
    
    private String value;
    
    public MessageSender(String message, boolean status) {
        new MessageSender(message, status, "");
    }
    
    public MessageSender(String message, boolean status, String value) {
        this.message = message;
        this.status = status;
        this.value = value;
    }
    
    public String getMessage() {
        return message;
    }
    
    public boolean isStatus() {
        return status;
    }
    
    public String getValue() {
        return value;
    }
    
    @Override
    public String toString() {
        return new StringJoiner(", ", MessageSender.class.getSimpleName() + "[", "]")
                       .add("message='" + message + "'")
                       .add("status=" + status)
                       .add("value=" + value)
                       .toString();
    }
}

enum FunctionType {
    EXECUTE("execute"),
    CREATE("create"),
    ;
    private final String value;
    
    FunctionType(String value) {
        this.value = value;
    }
    
    public static String getValue(String function) {
        for (FunctionType value : FunctionType.values()) {
            if (Objects.equals(function, value.value)) {
                return value.value;
            }
        }
        throw new IllegalArgumentException("유효하지 않은 function입니다. : " + function);
    }
    
    
}
