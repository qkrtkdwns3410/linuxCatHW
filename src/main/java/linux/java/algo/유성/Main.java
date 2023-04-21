package linux.java.algo.유성;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Objects;
import java.util.StringTokenizer;

/**
 * packageName    : linux.java.algo.유성
 * fileName       : Main
 * author         : ipeac
 * date           : 2023-04-21
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023-04-21        ipeac       최초 생성
 */
public class Main {
    static int r, s;
    static char[][] graph;
    
    public static void main(String[] args) throws IOException {
        FastReader rd = new FastReader();
        r = rd.nextInt();
        s = rd.nextInt();
        graph = new char[r][s];
        for (int i = 0; i < r; i++) {
            String next = rd.nextLine();
            for (int j = 0; j < s; j++) {
                graph[i][j] = next.charAt(j);
            }
        }
        int cnt = cloestFallCnt();
//        System.out.println("cnt = " + cnt);
        
        swapX(cnt);
        printGraph();
    }
    
    private static void printGraph() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < s; j++) {
                sb.append(graph[i][j]);
            }
            sb.append("\n");
        }
        System.out.println(sb.toString());
    }
    
    /**
     * 모든 X를 cnt 에 맞게 내림
     */
    private static void swapX(int cnt) {
        for (int col = 0; col < s; col++) {
            for (int row = r - 1 - cnt; row >= 0; row--) {
                boolean isX = Objects.equals(graph[row][col], 'X');
                boolean isZero = Objects.equals(graph[row + cnt][col], '.');
                if (isX && isZero) {
                    char temp = graph[row][col];
                    graph[row][col] = graph[row + cnt][col];
                    graph[row + cnt][col] = temp;
                }
            }
        }
    }
    
    private static int cloestFallCnt() {
        int minCnt = Integer.MAX_VALUE;
        for (int col = 0; col < s; col++) {
            int cnt = 0;
            boolean checkX = false;
            boolean checkSharp = false;
            for (int row = 0; row < r; row++) {
                if (checkX && !checkSharp) {
                    cnt++;
                }
                boolean isX = Objects.equals(graph[row][col], 'X');
                if (isX) {
                    cnt = 0;
                    checkX = true;
                }
                boolean isSharp = Objects.equals(graph[row][col], '#');
                if (isSharp) {
                    checkSharp = true;
                }
            }
            if (checkSharp && checkX) {
                minCnt = Math.min(minCnt, cnt);
            }
        }
        return minCnt - 1;
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
