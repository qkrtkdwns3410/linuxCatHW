package linux.java.algo.폴더정리small;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

/**
 * packageName    : linux.java.algo.폴더정리small
 * fileName       : Main
 * author         : ipeac
 * date           : 2023-04-10
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023-04-10        ipeac       최초 생성
 */
public class Main {
    static class Structure {
        String parent;
        List<Structure> folders;
        int filesCnt;
        
        public Structure(String parent, List<Structure> folders, int filesCnt) {
            this.parent = parent;
            this.folders = folders;
            this.filesCnt = filesCnt;
        }
    }
    static FastReader rd;
    static int n, m;
    
    public static void main(String[] args) throws IOException {
        n = rd.nextInt(); // 폴더의 총 개수
        m = rd.nextInt();  // 파일의 총 개수
        // n +m +1 번째 까지 상위폴더 P  , 폴더 또는 파일의 이름 F, 폴더가 아닌지 알려주는.. C 폴더  =1 , 파일 = 0
        
        
        
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
