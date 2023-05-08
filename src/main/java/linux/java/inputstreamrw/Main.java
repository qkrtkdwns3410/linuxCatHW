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
    }
}
