package linux.java.exception;

import java.io.File;

/**
 * packageName    : linux.java.exception
 * fileName       : ExceptionEx2
 * author         : ipeac
 * date           : 2023-04-30
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023-04-30        ipeac       최초 생성
 */
public class ExceptionEx15 {
    public static void main(String[] args) {
        //command line 에서 입력받은 값을 이름으로 갖는 파일을 생성함
        File f = createFile(args[0]);
        System.out.println(f.getName() + " 파일이 성공적으로 생성되었습니다");
    }// main
    
    private static File createFile(String filename) {
        try {
            if (filename.isEmpty()) {
                throw new Exception("파일 이름이 유효하지 않습니다");
            }
        } catch (Exception e) {
            //filename이 부적절한 경우, 파일 이름을  `제목없음.txt` 로 합니다.
            filename = "제목없음.txt";
        } finally {
            File f = new File(filename); // File 클래스의 객체를 만든다.
            createNewFile(f); // 생성된 객체를 이용해서 파일을 생성한다.
            return f;
        }
    }
    
    private static void createNewFile(File f) {
        try {
            f.createNewFile(); //파일 생성
        } catch (Exception e) {
            
        }
    }
    
    private static void method1() throws Exception {
    }
    
}//class
