package linux.java.algo.인내의도미노장인호석;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * packageName    : linux.java.algo.인내의도미노장인호석
 * fileName       : Main
 * author         : ipeac
 * date           : 2023-04-09
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023-04-09        ipeac       최초 생성
 */
public class Main {
    static int[] dx = {0, 0, 1, -1};
    static int[] dy = {1, -1, 0, 0};
    static int[][] graph;
    static boolean[][] down;
    static FastReader rd;
    static int n, m, r;
    static int attackScore;
    
    
    public static void main(String[] args) throws IOException {
        rd = new FastReader();
        n = rd.nextInt();
        m = rd.nextInt();
        r = rd.nextInt();
        graph = new int[n][m];
        down = new boolean[n][m];
        getGraph();
        
        
        for (int i = 0; i < r; i++) {
            attack();
            defense();
        }
        System.out.println(attackScore);
        printGraph();
    }
    
    public static void printGraph() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (!down[i][j]) {
                    System.out.printf("%s ", "S");
                } else {
                    System.out.printf("%s ", "F");
                    
                }
            }
            System.out.println();
        }
    }
    
    public static void getGraph() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                graph[i][j] = rd.nextInt();
            }
        }
    }
    
    public static void attack() {
        int x = rd.nextInt();
        int y = rd.nextInt();
        String way = rd.next();
        int moveIdx = setIdx(way);
        
        attackScore += downDomino(x - 1, y - 1, moveIdx);
    }
    
    public static int setIdx(String way) {
        switch (way) {
            case "E":
                return 0;
            case "W":
                return 1;
            case "S":
                return 2;
            case "N":
                return 3;
        }
        return 0;
    }
    
    public static boolean checkDown(int x, int y) {
        if (down[x][y]) {
            return true; // 내려가있음
        } else {
            return false;
        }
    }
    
    private static int downDomino(int x, int y, int moveIdx) {
        int fallenCnt = 0;
        int nx = x, ny = y;
        // 현재 숫자
        int nowValue = graph[x][y];
        if (checkDown(x, y)) {
            return fallenCnt;
        }
        fallenCnt++;
        down[x][y] = true;
        //숫자만큼 원 도미노를 쓰러뜨림
        for (int i = 1; i < nowValue; i++) {
            nx += dx[moveIdx];
            ny += dy[moveIdx];
            if (!inRange(nx, ny)) {
                break;
            }
            int nextValue = graph[nx][ny];
            int leftToEnd = nowValue - i - 1;
            if (checkDown(nx, ny)) {
                continue;
            }
            if (leftToEnd < nextValue - 1) {
                nowValue += nextValue - leftToEnd - 1;
            }
            down[nx][ny] = true;
            fallenCnt++;
        }
        return fallenCnt;
    }
    
    public static boolean inRange(int x, int y) {
        return 0 <= x && x < n && 0 <= y && y < m;
    }
    
    public static void defense() {
        int x = rd.nextInt() - 1;
        int y = rd.nextInt() - 1;
        if (down[x][y]) {
            down[x ][y ] = false;
        }
//        System.out.println("down = " + Arrays.deepToString(down));
        
    }
    
    static class FastReader {
        BufferedReader br;
        StringTokenizer st;
        
        public FastReader() {
            br = new BufferedReader(new InputStreamReader(System.in));
        }
        
        String next() {
            while (st == null || !st.hasMoreElements()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }
        
        int nextInt() {
            return Integer.parseInt(next());
        }
        
        long nextLong() {
            return Long.parseLong(next());
        }
        
        String nextLine() {
            String str = "";
            try {
                str = br.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return str;
        }
    }
}
