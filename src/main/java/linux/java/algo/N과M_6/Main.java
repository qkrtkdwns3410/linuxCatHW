package linux.java.algo.N과M_6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * packageName    : linux.java.algo.N과M_6
 * fileName       : Main
 * author         : ipeac
 * date           : 2023-05-01
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023-05-01        ipeac       최초 생성
 */
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        try {
            String[] splited = br.readLine()
                                       .split(" ");
            int n = Integer.parseInt(splited[0]);
            int m = Integer.parseInt(splited[1]);
            int[] result = new int[m];
            String[] numbersStr = br.readLine()
                                          .split(" ");
            // n 입력에 대해 모든 데이터를 숫자형으로 변환할수는 없기에 별도 배열 순회로 숫자형 체크를 한다.
            // n 과  m 은 parseInt 를 통해 이미 검증이 완료 된다.
            isDigits(numbersStr);
            int[] numbers = convertStringArrToIntArr(numbersStr);
            //n 개의 자연수 중에서 m 개를 고른 수열
            //같은수 여러번 가능
            // 고른 수열은 오름차순임.
            Arrays.sort(numbers);
            
            Permutation permutation = new Permutation(n, m, result, numbers);
            //처리와 출력 컨텍스트의 분리방법이 생각이 안났음..
            permutation.permutate(0, 0);
            
        } catch (IllegalArgumentException | ArrayIndexOutOfBoundsException e) {
            e.printStackTrace();
        }
    }
    
    
    public static int[] convertStringArrToIntArr(String[] arr) throws NumberFormatException {
        int[] ints = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            ints[i] = Integer.parseInt(arr[i]);
        }
        return ints;
    }
    
    
    public static void isDigits(String[] strArr) {
        final String REGEX = "[0-9]+";
        for (String s : strArr) {
            if (!s.matches(REGEX)) {
                throw new IllegalArgumentException("숫자값을 입력받아야합니다");
            }
        }
    }
}

class Permutation {
    int n;
    int m;
    int[] result;
    int[] numbers;
    StringBuilder sb;
    
    public Permutation(int n, int m, int[] result, int[] numbers) {
        this.n = n;
        this.m = m;
        this.result = result;
        this.numbers = numbers;
        this.sb = new StringBuilder();
    }
    
    public void permutate(int depth, int start) {
        if (depth == m) {
            //돌고자하는 값까지만 돕니다.
            for (int i : result) {
                sb.append(i)
                        .append(" ");
            }
            System.out.println(sb);
            sb.setLength(0);
            return;
        }
        for (int i = start; i < n; i++) {
            result[depth] = numbers[i];
            permutate(depth + 1, i);
        }
    }
    
}
