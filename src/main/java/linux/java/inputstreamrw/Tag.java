package linux.java.inputstreamrw;

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
    private int tagNum;
    
    public Tag(int tagNum) {
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
}
