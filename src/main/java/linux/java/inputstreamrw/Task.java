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
        Queue<Tag> avaliableTags = new PriorityQueue<>();
        Map<Tag, Integer> failMap = new HashMap<>();
        
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
                Tag tagOrNull = null;
                if (splited.length == 2) {
                    tagOrNull = Tag.from(splited[1]);
                }
                FunctionTypeAndTag functionTypeAndTag = new FunctionTypeAndTag(f, tagOrNull);
                
            } catch (RuntimeException e) {
                e.printStackTrace();
            }
        }
        
        /*입력 컨텍스트
        사용자의 입력에 대한 데이터를 입력의 조건 ( 입력을 뭐 공백기준으로 받는지, 엔터로 받는지 등)
        에 맞춰서 입력을 받는지 검증 하고, 처리 컨텍스트에 필요한 자료의 형태로 데이터를 넘겨주는 역할을 한다.
        처리 컨텍스트에 필요한 자료의 형태 란?
         (명령어) (태그) 에 해당하는 객제를 말함. 입력컨텍스트에서는 명령어와 태그 자체만 전달하는거지.
         해당 객체에 대한 유효성을 고려하지 않는다.
        */
    }
    
}


