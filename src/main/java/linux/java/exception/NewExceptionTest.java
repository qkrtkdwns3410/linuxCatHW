package linux.java.exception;

import java.io.IOException;

/**
 * packageName    : linux.java.exception
 * fileName       : MyException
 * author         : ipeac
 * date           : 2023-05-04
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023-05-04        ipeac       최초 생성
 */
public class NewExceptionTest {
    public static void main(String[] args) throws IOException {
        try {
            startInstall();
        } catch (SpaceException e) {
            System.out.println("에러메시지 " + e.getMessage());
            e.printStackTrace();
            System.out.println("공간을 확보하셈");
        } catch (MemoryException e) {
            System.out.println("에러메시지 " + e.getMessage());
            e.printStackTrace();
            System.gc(); // GC 수행하여 메모리를 늘립니다.
            System.out.println("다시 설치 시도하셈");
        } finally {
            deleteTempFiles();
        }
    }
    
    static void copyFiles() {
    
    }
    
    static void deleteTempFiles() {
        
    }
    
    static void startInstall() throws SpaceException, MemoryException {
        if (!enoughSpace()) {
            throw new SpaceException("설치한 공간이 부족");
        }
        if (!enoughMemory()) {
            throw new MemoryException("설치할 메모리가 부족");
        }
    }
    
    static boolean enoughSpace() {
        //필요한 하드공간 체크 ㅇㅇ
        return false;
    }
    
    static boolean enoughMemory() {
        // 설치하는데 필요한 메모리 공간이 있는지 확인하는 코드를 적는다.
        return true;
    }
    
}

class SpaceException extends Exception {
    public SpaceException(String message) {
        super(message);
    }
}

class MemoryException extends Exception {
    public MemoryException(String message) {
        super(message);
    }
}
