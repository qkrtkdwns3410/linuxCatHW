package linux.sub.sub2;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.StringTokenizer;

/**
 * packageName    : linux.sub.sub2
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
        File file = new File("src/main/java/linux/sub/sub2/input.txt"); // 루트로부터의 상대경로
        System.out.println("file = " + file.getPath());
        System.out.println("file = " + file.getName());
        System.out.println("file = " + file.getAbsolutePath());
        System.out.println("file = " + file.getParentFile());
        System.out.println("file = " + file.isDirectory());
        System.out.println("file = " + file.exists());
    }
    
}
