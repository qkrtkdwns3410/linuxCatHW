package linux.java.inputstreamrw;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Task {
    
    public static void main(String[] args) throws IOException {
        Queue<Function> executableTags = new PriorityQueue<>();
        Map<Function, Integer> executeFailMap = new HashMap<>();
        Map<Function, Integer> createFailMap = new HashMap<>();
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        System.out.println("n = " + n);
        //입력컨텍스트 -  사용자의 입력을 토대로 시스템이 이해할수있는 형태로 메모리에 캐싱한다.
        // -이때 유효한 값이 아닌 경우 처리컨텍스트에 넘기지 않음.
        // 시스템의 요구사항을 입력컨텍스트는 모르는 상황이라고 생각해야한다.
        while (n > 0) {
            try {
                String[] splited = br.readLine().split(" ");
                FunctionType f = FunctionType.from(splited[0]);
                int tagNum = Optional.ofNullable(splited[1]).orElse(-1);
                Function function = FunctionFactory.create(f, tagNum);
                System.out.println("function = " + function);
            } catch (RuntimeException e) {
                e.printStackTrace();
            }
        }
        
    }
    
}


