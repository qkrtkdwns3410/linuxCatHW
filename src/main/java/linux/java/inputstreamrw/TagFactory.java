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
public class TagFactory {
    public static Tag createTag(int tagNum) {
        return new Tag(tagNum);
    }
}
