package linux.java.exception;

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
public class ChainedExceptionEx {
    public static void main(String[] args) {
        try {
            install();
        } catch (InstallException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }// main

    static void install() throws InstallException {
        try {
            startInstall();
            copyFiles();
        } catch (MemoryException e) {
            InstallException ie = new InstallException("설치 중 예외 발생");
            ie.initCause(e);
            throw ie;
        } catch (SpaceException se) {
            InstallException ie = new InstallException("설치 중 예외 발생");
            ie.initCause(se); //throwable 에 존재하는 initCause 로 Exception 을 RUntimeExeception 으로 둘러싸서 unchecked exception 으로 리턴합니다.
            // 예외 상황은 항상 발생할수있고 모든 로직에 try-catch를 적용해서 일일이 예외처리를 해주는 것이 힘들다보니.. unchecked execption 으로 변환하는 경우때문에 이런식으로
            // 코딩하나봅니다.
            throw ie;
        }finally {
            deleteTempFiles();
        }
    }

    static void startInstall() throws SpaceException, MemoryException {
        if (!enoughSpace()) {
            throw new SpaceException("설치할 공간이 부족합니다");
        }
        if (!enoughMemory()) {
            throw new MemoryException("메모리가 부족합니다");
        }
    }

    static void copyFiles() {

    }

    static void deleteTempFiles() {

    }

    static boolean enoughSpace() {
        //설치하는데 필요한 공간이 있는지 체크
        return false;
    }

    static boolean enoughMemory() {
        //설치하는데 필요한 메모리 공간이 있는지 확인하는 코드
        return true;
    }


}//class

class InstallException extends Exception {
    public InstallException(String message) {
        super(message);
    }
}

