package linux.java.inputstreamrw;

import java.text.MessageFormat;
import java.util.StringJoiner;

/**
 * packageName    : linux.java.inputstreamrw
 * fileName       : Tag
 * author         : ipeac
 * date           : 2023-05-10
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023-05-10        ipeac       최초 생성
 */
public class Tag {
    private static final int MIN_TAG_VALUE = 1;
    private static final int MAX_TAG_VALUE = 9;
    
    private final int tagNum;
    
    public Tag(int tagNum) {
        if (!(MIN_TAG_VALUE <= tagNum && tagNum <= MAX_TAG_VALUE)) {
            String errorMsg = MessageFormat.format("tagNum: {0}이 유효범위({1} ~ {2})에 있지 않습니다", tagNum, MIN_TAG_VALUE, MAX_TAG_VALUE);
            throw new IllegalArgumentException(errorMsg);
        }
        
        this.tagNum = tagNum;
    }
    
    
    public int getTagNum() {
        return tagNum;
    }
    
    @Override
    public String toString() {
        return new StringJoiner(", ", Tag.class.getSimpleName() + "[", "]")
                .add("tagNum=" + tagNum)
                .toString();
    }
    
    public static Tag from(String tagNum) {
        int numValue = Integer.parseInt(tagNum);
        return new Tag(numValue);
    }
}
