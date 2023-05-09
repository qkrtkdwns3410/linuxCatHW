package linux.java.inputstreamrw;

import java.util.StringJoiner;

/**
 * packageName    : linux.java.inputstreamrw
 * fileName       : Function
 * author         : ipeac
 * date           : 2023-05-09
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023-05-09        ipeac       최초 생성
 */
public class Function implements Comparable<Function> {
    String function;
    int tagNum;
    
    public Function(String function) {
        this(function, -1);
    }
    
    public Function(String function, int tagNum) {
        this.function = function;
        this.tagNum = tagNum;
    }
    
    @Override
    public int hashCode() {
        return super.hashCode();
    }
    
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            throw new RuntimeException("함수가 NULL");
        }
        if (!(obj instanceof Function)) {
            return false;
        }
        Function other = (Function) obj;
        return other.tagNum == this.tagNum;
    }
    
    @Override
    public String toString() {
        return new StringJoiner(", ", "Function{", "}")
                .add("function='" + function + "'")
                .add("tagNum=" + tagNum)
                .toString();
    }
    
    @Override
    public int compareTo(Function o) {
        return this.tagNum - o.tagNum;
    }
    //
    
}
