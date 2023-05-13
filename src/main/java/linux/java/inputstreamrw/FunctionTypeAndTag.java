package linux.java.inputstreamrw;

import java.util.Optional;

/**
 * packageName    : linux.java.inputstreamrw
 * fileName       : FunctionAndTag
 * author         : ipeac
 * date           : 2023-05-13
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023-05-13        ipeac       최초 생성
 */
public class FunctionTypeAndTag {
    private final FunctionType functionType;
    private final Tag tagOrNull;
    
    public FunctionTypeAndTag(FunctionType functionType, Tag tagOrNull) {
        if (functionType == null) {
            throw new IllegalArgumentException("functionType 이 NULL 일 수없습니다");
        }
        
        this.functionType = functionType;
        this.tagOrNull = tagOrNull;
    }
    
    public FunctionType getFunctionType() {
        return this.functionType;
    }
    
    public Tag getTagOrNull() {
    
    }
    
    public Optional<Tag> getTag() {
        return Optional.ofNullable(tagOrNull);
    }
    
}
