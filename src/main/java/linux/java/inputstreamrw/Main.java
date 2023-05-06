package linux.java.inputstreamrw;

import java.util.PriorityQueue;
import java.util.Queue;

/**
 * packageName    : linux.java.inputstreamrw
 * fileName       : Main
 * author         : ipeac
 * date           : 2023-05-06
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023-05-06        ipeac       최초 생성
 */
public class Main {
    public static void main(String[] args) {
        Queue<Integer> q = new PriorityQueue<>();
        q.offer(5);
        q.offer(1);
        q.offer(3);

        System.out.println("q.poll() = " + q.poll());

        Queue<Tag> tq = new PriorityQueue<>();
        tq.offer(new Tag(5));
        tq.offer(new Tag(1));
        tq.offer(new Tag(3));

        System.out.println("tq.poll() = " + tq.poll());

        Tag tag = new Tag(1);
        Tag tag2 = new Tag(1);

        System.out.println(tag.equals(tag2));
    }
}
