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
    FunctionType function;
    Tag tagNum;
    
    public Function(FunctionType function) {
        this(function, new Tag(-1));
    }
    
    public Function(FunctionType function, Tag tagNum) {
        this.function = function;
        this.tagNum = tagNum;
    }
    
    public boolean fail() {
        
        return true;
    }
    
    @Override
    public int hashCode() {
        return super.hashCode();
    }
    
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            throw new RuntimeException("비교대상이 NULL");
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
        return this.tagNum.getTagNum() - o.tagNum.getTagNum();
    }
    
}
