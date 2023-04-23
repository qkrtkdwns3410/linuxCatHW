package linux.java.file;

import java.io.File;
import java.io.IOException;

/**
 * packageName    : linux.java.file
 * fileName       : FileEx1
 * author         : ipeac
 * date           : 2023-04-23
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023-04-23        ipeac       최초 생성
 */
public class FileEx1 {
    public static void main(String[] args) throws IOException {
        File f = new File("C:\\Users\\ipeac\\IdeaProjects\\linuxCatHW\\src\\main\\java\\linux\\java\\file\\FileEx12.java");
        String fileName = f.getName();
        int pos = fileName.lastIndexOf(".");
        
        System.out.println("경로를 제외한 파일이름 - " + f.getName());
        System.out.println("확장자를 제외한 파일이름 - " + fileName.substring(0, pos));
        System.out.println("확장자 - " + fileName.substring(pos + 1));
        
        System.out.println("경로를 포함한 파일이름  - " + f.getPath());
        System.out.println("파일의 절대경로 - " + f.getAbsolutePath());
        System.out.println("파일의 정규경로 - " + f.getCanonicalPath());
        
        /*
        * 절대경로와 정규경로
        * 1. 절대경로는 최상위 경로에서부터 상대 경로를 포함한 경로를 의미합니다.
        * -- 현재 디렉토리 가
        * C;\Program Files\Java\jdk-11.0.11\bin
        * --상대경로
        * .\java.exe
        *.\..\bin\java.exe
        * 이 모두를 아우르는게 절대경로임.
        *
        * 2. 정규경로는..
        * 내가 알고있던 절대경로의 의미가 정규경로라는 것을 알게됨..
        * 오직 하나만 존재합니다.
        * C;\Program Files\Java\jdk-11.0.11\bin <- 이런식
        *
        * 현재 디렉터리에 대한 상대 경로를 확인한 후 상대 경로 요소를 제거하고 경로 문자열을 반환함.
        * */
        
        System.out.println();
        System.out.println("File.pathSeparator - " + File.pathSeparator);
        System.out.println("File.pathSeparatorChar - " + File.pathSeparatorChar);
        System.out.println();
        //현재 실행중인 디렉토리
        System.out.println("user.dir=" + System.getProperty("user.dir"));
        System.out.println("sun.boot.class.path =" + System.getProperty("sun.boot.class.path"));
    }
}
