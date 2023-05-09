package linux.java.inputstreamrw;

/**
 * packageName    : linux.java.inputstreamrw
 * fileName       : FunctionFactory
 * author         : ipeac
 * date           : 2023-05-09
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023-05-09        ipeac       최초 생성
 */
public class FunctionFactory {
    
    public static Function create(FunctionType f, int tagNum) {
        Function function = null;
        String funcStr = f.getValue();
        
        if (f.isTagYn()) {
            function = new Function(funcStr, -1);
        } else {
            function = new Function(funcStr, tagNum);
        }
        
        return function;
    }
    
}
