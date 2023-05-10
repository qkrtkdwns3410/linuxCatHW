package linux.java.inputstreamrw;

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
        if (1 <= tagNum && tagNum <= 9) {
        
        } else if (tagNum == -1) {
        
        }
        this.tagNum = tagNum;
    }
    
    public Tag createTag(int tagNum) {
        return new Tag(tagNum);
    }
    
    public int getTagNum() {
        return tagNum;
    }
}
