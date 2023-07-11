package linux.java.inputstreamrw;

import java.util.Map;
import java.util.StringJoiner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum FunctionType {
    EXECUTE("execute"), CREATE("create"),
    ;
    
    private final String value;
    
    
    //미리 캐싱으로 빠른 접근 또한 HashMap으로 순회가 필요없음
    public static final Map<String, FunctionType> FUNCTION_TYPE_MAP = Stream.of(values())
            .collect(Collectors.toMap(FunctionType::getValue, v -> v));
    
    FunctionType(String value) {
        if (value == null) {
            throw new IllegalArgumentException("올바른 명령어가 아닙니다");
        }
        this.value = value;
    }
    
    public static FunctionType from(String s) {
        FunctionType resultFunction = null;
        resultFunction = FUNCTION_TYPE_MAP.get(s);
        return resultFunction;
    }
    
    public String getValue() {
        return value;
    }
    
    @Override
    public String toString() {
        return new StringJoiner(", ", FunctionType.class.getSimpleName() + "[", "]")
                .add("value='" + value + "'")
                .toString();
    }
}
