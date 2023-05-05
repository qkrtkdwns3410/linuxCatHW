package linux.java.algo.두수의합;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * packageName    : linux.java.algo.두수의합
 * fileName       : Main
 * author         : ipeac
 * date           : 2023-05-05
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023-05-05        ipeac       최초 생성
 */
public class Main {
    public static void main(String[] args) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            int n = Integer.parseInt(br.readLine());
            String[] arr = br.readLine().split(" ");
            int x = Integer.parseInt(br.readLine());
            
            List<Integer> convertedInts = Arrays.stream(arr).map(Integer::parseInt).sorted(Integer::compareTo).collect(Collectors.toList());
            System.out.println("convertedInts = " + convertedInts);
            int start = 0;
            int end = n - 1;
            int ans = 0;
            while (start < end) {
                int sum = convertedInts.get(start)+ convertedInts.get(end);
                System.out.println("convertedInts.get(start) = " + convertedInts.get(start));
                System.out.println("convertedInts.get(end)" + convertedInts.get(end));
                System.out.println("sum = " + sum);
                boolean sameX = sum == x;
                boolean underX = sum < x;
                
                if (sameX) {
                    ans++;
                } else if (underX) {
                    start++;
                }
                end--;
            }
            System.out.println(ans);
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException ae) {
        }
    }
    
}
