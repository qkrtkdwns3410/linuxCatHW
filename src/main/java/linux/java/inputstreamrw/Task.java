package linux.java.inputstreamrw;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

public class Task {
    
    public static void main(String[] args) throws IOException {
        Queue<Tag> executableTags = new PriorityQueue<>();
        Map<Tag, Integer> executeFailMap = new HashMap<>();
        Map<Tag, Integer> createFailMap = new HashMap<>();
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        System.out.println("n = " + n);
        //입력컨텍스트 -  사용자의 입력을 토대로 시스템이 이해할수있는 형태로 메모리에 캐싱한다.
        // -이때 유효한 값이 아닌 경우 처리컨텍스트에 넘기지 않음.
        // 시스템의 요구사항을 입력컨텍스트는 모르는 상황이라고 생각해야한다.
        while (n > 0) {
            try {
                String[] splited = br.readLine().split(" ");
                FunctionType f = FunctionType.from(splited[0]);//입력컨텍스트에서 생성되었지만, 처리컨텍스스트에 사용자 입력 검증
                Function function = FunctionFactory.create(f, splited); // 여기에 splited 이 들어가는게 맞는가? => 머릿속으로 안된다는 건 알고있는데 대처법을 생각을 못하겠음
                System.out.println("function = " + function);
                //입력 컨텍스트가 처리 컨텍스트에 영향을 주면 안됨.
                if (function.fail()) {
                    throw new RuntimeException("수행불가");
                }
                
            } catch (RuntimeException e) {
                e.printStackTrace();
            }
        }
        
    }
    
}


