package linux.java.algo.수리공항승;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringJoiner;

/**
 * packageName    : linux.java.algo.수리공항승
 * fileName       : Main
 * author         : ipeac
 * date           : 2023-05-02
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023-05-02        ipeac       최초 생성
 */
public class Main {
    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            String[] firstLine = br.readLine().split(" ");
            int N = Integer.parseInt(firstLine[0]);//물이 새는 곳의 개수
            int L = Integer.parseInt(firstLine[1]); // 테이프의 길이
            String[] leaks = br.readLine().split(" ");
            Pipe[] pipes = new Pipe[N];
            for (int i = 0; i < pipes.length; i++) {
                int number = Integer.parseInt(leaks[i]);
                pipes[i] = new Pipe(number);
            }
            Arrays.sort(pipes, (a, b) -> a.getLeakedPosition() - b.leakedPosition);
//            System.out.println("pipes = " + Arrays.toString(pipes));

            int index = 0;
            int count = 0;
            while (index < N) {
                int startPosition = pipes[index].getLeakedPosition();
                while (index < N && pipes[index].getLeakedPosition() - startPosition < L) {
                    index++;
                }
                count++;
            }
            System.out.println(count);

        } catch (IOException | NumberFormatException | ArrayIndexOutOfBoundsException e) {
            e.printStackTrace();
        }
    }
}

class Pipe {
    int leakedPosition;

    public Pipe(int leakedPosition) {
        this.leakedPosition = leakedPosition;
    }

    public int getLeakedPosition() {
        return leakedPosition;
    }

    public void setLeakedPosition(int leakedPosition) {
        this.leakedPosition = leakedPosition;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Pipe.class.getSimpleName() + "[", "]")
                .add("leakedPosition=" + leakedPosition)
                .toString();
    }
}
