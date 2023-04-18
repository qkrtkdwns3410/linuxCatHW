package linux.homework;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.TreeSet;

/**
 * packageName    : linux.homework
 * fileName       : Task
 * author         : ipeac
 * date           : 2023-04-16
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023-04-16        ipeac       최초 생성
 */
public class Main {
    //최대한 추상화 하여, 공통 범용적인 시스템을 만들어야함. 실행 ? 성공 : 실패
    //정답을 찾지말고 연구해라
    public static void main(String[] args) throws IOException {
        //====================선언 및 입력=======================//
        // 내가 가진 태스크값에 대해 정렬된 값을 보장하며, 중복값을 배제하기위해 선언
        TreeSet<Integer> taskTank = new TreeSet<>();
        //생성실패 카운트
        int createFailCnt = 0;
        //실행실패에 대한 카운트 기록
        Map<Integer, Integer> executeFailMap = new HashMap<>();
        //반복 횟수
        int n = 0;
        //라인단위 + 메모리 입력스트림 + 버퍼링
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            
        }
        
        //====================선언 및 입력 종료=======================//
    }
    
    /**
     * StringToken 안의 값을 전부 플러시합니다.
     * @param st
     */
    public static void flushAllTokens(StringTokenizer st) {
        while (st != null && st.hasMoreElements()) {
            st.nextToken();
        }
    }
    
    
    public static void validator() {
    
    }
    
    public static void execute() {
    
    }
    
    public static void create() {
    
    }
    
    public static void executeFail() {
    
    }
    
    public static void createFail() {
    
    }
    
    public static void print() {
    
    }
    
}


