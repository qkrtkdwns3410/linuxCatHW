package linux.java.inputstreamrw;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum FunctionType {
    EXECUTE("execute", true), CREATE("create", false),
    ;
    private final String value;
    private final boolean tagYn;
    
    //미리 캐싱으로 빠른 접근 또한 HashMap으로 순회가 필요없음
    private static final Map<String, FunctionType> FUNCTION_TYPE_MAP = Stream.of(values())
            .collect(Collectors.toMap(FunctionType::getValue, v -> v));
    
    FunctionType(String value, boolean tagYn) {
        this.value = value;
        this.tagYn = tagYn;
    }
    
    public static FunctionType from(String s) throws RuntimeException {
        FunctionType resultFunction = FUNCTION_TYPE_MAP.get(s);
        if (resultFunction == null) {
            throw new RuntimeException("올바른 함수가 아닙니다.");
        }
        
        return resultFunction;
    }
    
    public String getValue() {
        return value;
    }
    
    public boolean isTagYn() {
        return tagYn;
    }
}
