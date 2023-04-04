package linux.sub.bufferedReaderImpl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;

/**
 * packageName    : linux.sub.bufferedReaderImpl
 * fileName       : Main
 * author         : ipeac
 * date           : 2023-04-04
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023-04-04        ipeac       최초 생성
 */
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        String s1 = br.readLine();
//        System.out.println("s1 = " + s1);
//        String s2 = br.readLine();
//        System.out.println("s2 = " + s2);
        
        CustomBufferedReader cbr = new CustomBufferedReader(new InputStreamReader(System.in));
        
        String s3 = cbr.readLine();
        System.out.println("s3 = " + s3);
        String s4 = cbr.readLine();
        System.out.println("s4 = " + s4);
    }
    
}

class CustomBufferedReader {
    private Reader in;
    private char[] chars;
    
    private char[] leftOver;
    private int leftOverLen;
    
    private StringBuilder sb;
    
    private static int DEFAULT_BUFFER_SIZE = 5;
    
    public CustomBufferedReader(Reader in) {
        this(in, DEFAULT_BUFFER_SIZE);
    }
    
    public CustomBufferedReader(Reader in, int bs) {
        this.in = in;
        chars = new char[bs];
        leftOver = new char[bs];
        leftOverLen = 0;
        this.sb = new StringBuilder();
    }
    
    public String readLine() throws IOException {
        String result = null;
        chars = new char[DEFAULT_BUFFER_SIZE]; // 버퍼 초기화 ( 이전값이 남아있기에 )
        while (true) {
            int len = in.read(chars);
            if (len == -1) {
                break;
            }
            int index = findTarget(chars, '\n'); //   n 시간 탐색
            boolean containNewLine = index != -1;
            if (containNewLine) { // if 문의 boolean 처리는 프로그래밍 처리가 아닌 => (if( index !=-1 ) 가 아닌) 개념 처리 [[ boolean con.... = index!=-1 ]]로 합니다.
                sb.append(chars, 0, index); // 엔터 시점 이전의 데이터
                result = sb.toString(); // 엔터이전 문자열을 result 에 담아 return 을 준비합니다.
                sb.setLength(0); //내부의 버퍼를 비웁니다.
                sb.append(chars, index+1, chars.length-index-1);
                // 엔터 시점 이후의 남은 데이터를 담아주는 이유 한줄씩 입력이 들어올 수 도 있지만, 2줄 이상으로 입력이 들어오는 경우 이후의 값을 저장할 수 있어야함
                break;
                
            } else {
                sb.append(chars, 0, len);
            }
        }
        return result;
    }
    
    /**
     * @param arr    특정 char 배열
     * @param target 그 배열에서 찾을 값
     * @return index 값 없는 경우 -1 리턴
     */
    public int findTarget(char[] arr, char target) {
        int index = -1;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == target) {
                index = i;
                return index;
            }
        }
        
        return index;
    }
}
