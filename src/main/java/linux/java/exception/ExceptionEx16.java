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
public class ExceptionEx16 {
    public static void main(String[] args) {
        try {
            File f = createFile(args[0]);
            System.out.println(f.getName() + " 파일이 성공적으로 생성되었습니다");
        } catch (Exception e) {
            System.out.println(e.getMessage()+" 다시 입력해 주시기 바랍니다.");
        }
    }// main
    
    private static File createFile(String filename) throws Exception {
        if (filename.isEmpty()) {
            throw new Exception("파일이름이 유효하지 않습니다");
        }
        File f = new File(filename); // File 클래스 객체 만듬
        //File 객체의 createNewFile메서드를 이용하여 실제 파일 생성
        f.createNewFile();
        return f;
    }
}//class
