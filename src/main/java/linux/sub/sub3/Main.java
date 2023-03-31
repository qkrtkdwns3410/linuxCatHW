package linux.sub.sub3;

import java.io.File;
import java.io.IOException;

/**
 * packageName    : linux.sub.sub3
 * fileName       : Main
 * author         : ipeac
 * date           : 2023-03-31
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023-03-31        ipeac       최초 생성
 */
public class Main {
    public static void main(String[] args) throws IOException {
        String curDir = System.getProperty("user.dir"); //system.getProperty("user.dir")으로 현재 작업중인 루트 디렉토리의 경로를 들고올 수 있습니다.
        //"user.dir" 이라는 시스템 프로퍼티값은 각 운영체제마다의 값이 다르며 , 현재 디렉토리를 의미합니다. 정확하게는 java 어플리케이션이 시작된 디렉토리 입니다.
/*        public static String getProperty(String key) { // 아래 주석 소스는 getProperty 의 소스코드입니다.
            checkKey(key);// 키 값이 null 인지 빈값인지 체크합니다.
            SecurityManager sm = getSecurityManager();//securityManager 를 호출합니다. => securityManager 는 자바에서 보안을 담당하는 클래스이며, 애플리케이션이 외부의 자원에 접근하려고 할 때 별도 보안정책을 사용해 접근 권한을 검사합니다.
            if (sm != null) {
                sm.checkPropertyAccess(key); // 해당 키값에 접근할수있는 권한이 있는지 체크합니다.
            }
        
            return props.getProperty(key); // 해당 키값의 프로퍼티값을 String 으로 반환합니다. 만약 키에 해당하는 값이 없는경우 null 반환
        }
        }*/
    
    
/*        private static void checkKey(String key) { //
            if (key == null) { //키 값이 null 이라면 NPE 발생
                throw new NullPointerException("key can't be null");
            }
            if (key.equals("")) { // 빈 값이라면 IAE 발생
                throw new IllegalArgumentException("key can't be empty");
            }
        }*/
        System.out.println("curDir = " + curDir);
        File file = new File(curDir, "input.txt");
        System.out.println("file = " + file.isFile());
        System.out.println("file = " + file.exists());
    }
    
}
