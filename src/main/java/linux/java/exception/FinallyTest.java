package linux.java.exception;

import java.io.IOException;

/**
 * packageName    : linux.java.exception
 * fileName       : FinallyTeest
 * author         : ipeac
 * date           : 2023-05-02
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023-05-02        ipeac       최초 생성
 */
public class FinallyTest {
    public static void main(String[] args) throws IOException {
        try {
            startInstall();
            copyFiles();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            deleteTempFiles();
        }
    }
    
    static void startInstall() {
        /* 프로그램 설치에 필요한 준비를 하는 코드 작성*/
    }
    
    static void copyFiles() {
        /*파일들을 복사하는 코드 적음*/
    }
    
    static void deleteTempFiles() {
        /* 임시파일들을 삭제하는 코드를 적습니다*/
    }
    
}
