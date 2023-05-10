package linux.java.inputstreamrw;

import java.util.Map;
import java.util.StringJoiner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum FunctionType {
    EXECUTE("execute"), CREATE("create"),
    ;
    
    private final String value;
    private int tagNum;
    
    
    //미리 캐싱으로 빠른 접근 또한 HashMap으로 순회가 필요없음
    private static final Map<String, FunctionType> FUNCTION_TYPE_MAP = Stream.of(values())
            .collect(Collectors.toMap(FunctionType::getValue, v -> v));
    
    FunctionType(String value) {
        this(value, -1);
    }
    
    FunctionType(String value, int tagNum) {
        this.value = value;
        this.tagNum = tagNum;
    }
    
    public static FunctionType from(String s) throws RuntimeException {
        FunctionType resultFunction = FUNCTION_TYPE_MAP.get(s);
        return resultFunction;
    }
    
    public String getValue() {
        return value;
    }
    
    public int getTagNum() {
        return tagNum;
    }
    
    @Override
    public String toString() {
        return new StringJoiner(", ", FunctionType.class.getSimpleName() + "[", "]")
                .add("value='" + value + "'")
                .add("tagNum=" + tagNum)
                .toString();
    }
}
