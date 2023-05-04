package linux.java.algo.불4179;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringJoiner;

/**
 * packageName    : linux.java.algo.불4179
 * fileName       : Main
 * author         : ipeac
 * date           : 2023-05-04
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023-05-04        ipeac       최초 생성
 */
public class Main {
    static int[] dx = {0, 0, 1, -1};
    static int[] dy = {1, -1, 0, 0};
    
    static char[][] maze;
    static int r, c;
    
    public static void main(String[] args) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            String[] splited = br.readLine()
                                       .split(" ");
            r = Integer.parseInt(splited[0]);
            c = Integer.parseInt(splited[1]);
            Queue<Point> jihoonQ = new LinkedList<>();
            Queue<Point> FireQ = new LinkedList<>();
            
            maze = new char[r][c];
            for (int i = 0; i < r; i++) {
                maze[i] = br.readLine()
                                  .toCharArray();
                for (int j = 0; j < c; j++) {
                    if (maze[i][j] == 'J') {
                        jihoonQ.add(new Point(i, j, 0));
                    } else if (maze[i][j] == 'F') {
                        FireQ.add(new Point(i, j, 0));
                    }
                }
            }
//            System.out.println("maze = " + Arrays.deepToString(maze));
            bfs(jihoonQ, FireQ);
            
            
        } catch (NumberFormatException e) {
            e.printStackTrace();
        } catch (ArrayIndexOutOfBoundsException| IllegalArgumentException ignored) {
        
        }
    }
    
    public static void bfs(Queue<Point> jihoonQ, Queue<Point> fireQ) {
        while (!jihoonQ.isEmpty()) {
            int fireSize = fireQ.size(); // 현재 불의 큐의 개수만큼 확산을 고려합니다.
            for (int i = 0; i < fireSize; i++) {
                Point curr = fireQ.poll();
//                System.out.println("curr = " + curr);
                for (int j = 0; j < 4; j++) {
                    int nx = curr.x + dx[j];
                    int ny = curr.y + dy[j];
                    if (!isRange(nx, ny)) {
                        continue;
                    }
                    if (maze[nx][ny] == '.' || maze[nx][ny] == 'J') {
                        maze[nx][ny] = 'F';
                        fireQ.add(new Point(nx, ny, curr.time + 1));
                    }
                }
            }
            int jihoonSize = jihoonQ.size();
            for (int i = 0; i < jihoonSize; i++) {
                Point curr = jihoonQ.poll();
//                System.out.println("curr = " + curr);
                for (int j = 0; j < 4; j++) {
                    int nx = curr.x + dx[j];
                    int ny = curr.y + dy[j];
                    if (!isRange(nx, ny)) {
                        System.out.println(curr.time+1);
                        throw new ArrayIndexOutOfBoundsException("정답임");
                    }
                    if (maze[nx][ny] == '.' && maze[nx][ny] != 'J') {
                        maze[nx][ny] = 'J';
                        jihoonQ.add(new Point(nx, ny, curr.time + 1));
                    }
                }
            }
            
        }
        System.out.println("IMPOSSIBLE");
        throw new IllegalArgumentException("탈출할수없습니다");
    }
    
    public static boolean isRange(int x, int y) {
        return 0 <= x && x < r && 0 <= y && y < c;
    }
}

class Point {
    int x;
    int y;
    int time;
    
    public Point(int x, int y, int time) {
        this.x = x;
        this.y = y;
        this.time = time;
    }
    
    @Override
    public String toString() {
        return new StringJoiner(", ", Point.class.getSimpleName() + "[", "]")
                       .add("x=" + x)
                       .add("y=" + y)
                       .toString();
    }
}
