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
    
    public static Function create(FunctionType f, String[] splited) { //처리의 영역 시스템의 요구사항
        Function function = null;
        try {
            int tagNum = Integer.parseInt(splited[1]);
            function = new Function(f, new Tag(tagNum));
        } catch (RuntimeException e) {
            function = new Function(f);
        }
        
        return function;
    }
    
}
