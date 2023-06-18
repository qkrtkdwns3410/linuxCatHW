package linux.sub.virtualPath;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * packageName    : linux.sub.virtualPath
 * fileName       : Main
 * author         : ipeac
 * date           : 2023-06-18
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023-06-18        ipeac       최초 생성
 */
public class Main {
    public static void main(String[] args) throws IOException {
        String userInput = "/../sample4.zip";
        String baseRoot = System.getProperty("user.dir");
        String absolutePath = baseRoot + userInput;
        Path normalizedPath = Paths.get(absolutePath).normalize();
        System.out.println("normalizedPath = " + normalizedPath);
        if (!normalizedPath.startsWith(baseRoot)) {
            throw new IllegalStateException("올바른 경로가 아닙니다");
        }
/*        OutputStream consoleOs = new BufferedOutputStream(System.out, 8192);
        try (InputStream is = new BufferedInputStream(new FileInputStream(normalizedPath.toString()), 8192);) {
            byte[] bytes = new byte[1024];
            while (true) {
                int len = is.read(bytes);
                if (len == -1) {
                    break;
                }
                consoleOs.write(bytes, 0, len);
            }
            consoleOs.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }*/
    }
}
