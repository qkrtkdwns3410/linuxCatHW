package linux.java.printStream;

import java.io.IOException;
import java.util.Date;

/**
 * packageName    : linux.java.printStream
 * fileName       : PrintStreamEx1
 * author         : ipeac
 * date           : 2023-04-10
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023-04-10        ipeac       최초 생성
 */
public class PrintStreamEx1 {
    public static void main(String[] args) throws IOException {
        int i = 65;
        float f = 1234.56789f;
        
        Date d = new Date();
        
        //%c =>문자 | %d => 10진수
        System.out.printf("문자 %c의 코드는 %d\n", i, i);
        //%o  => 8진수 %x => 16진수
        System.out.printf("%d는 8진수로 %o, 16진수로 %x\n", i, i, i);
        //%3d => 3자리 숫자 빈자리는 공백으로 채웁니다. (오른쪽 정렬)
        System.out.printf("%3d%3d%3d\n", 100, 90, 80);
        System.out.println();
        System.out.printf("19273971372123717327173822\n");
        //%s => 문자열 %-5s(5자리 문자열 왼쪽 정렬 ) || %5s (5자리 문자열 오른쪽 정렬)
        System.out.printf("%s%-5s%5s\n", "123", "123", "123");
        System.out.println();
        //%-8.1f => 소수점이상 최소 6자리, 소수점 이하 1자리  || 출력될 자리수를 최소 8자리(소수점 포함) (왼쪽 정렬) 빈자리 공백으로 채워짐
        // %8.1f => 소수점 이상 최소 6자리, 소수점 이하 1자리 || 출력될 자리수를 최소 8자리(소수점 포함)(오른쪽 정렬) 빈자리는 0으로 채워짐.
        // %e => 지수형태 표현
        System.out.printf("%-8.1f%8.1f %e\n", f, f, f);
        System.out.println();
        //%tY ...... 년 월 일 순서입니다.
        System.out.printf("오늘을 %tY년 %tm월 %td일 입니다.\n", d, d, d);
        
        System.out.printf("지금은 %tH시 %tM분 %tS초 입니다.\n", d, d, d);
        //1$ 첫번째 매개변수임..ㅇㅇ 매개변수로 d 하나만 전달해도 몇번째 매개변수에서 값을 전달받는지 지정해줄수있다.
        System.out.printf("지금은 %1$tH시 %1$tM분 %1$tS초 입니다.\n", d);
    }
}
