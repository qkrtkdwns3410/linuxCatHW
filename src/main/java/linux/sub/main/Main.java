package linux.sub.main;

import javax.sql.DataSource;
import java.io.*;
import java.util.Arrays;
import java.util.Scanner;
import java.util.StringTokenizer;

/**
 * packageName    : linux.sub.main
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
        InputStream consoleInputStream = openConsoleInputStream();
        StringBuilder sb = new StringBuilder();
        OutputStream os = new ByteArrayOutputStream();
        InputStream fileInputStream = null;
        byte[] bytes = new byte[4096];
        while (true) {
            int len = consoleInputStream.read(bytes);
            if (len == -1) {
                break;
            }
            String data = new String(bytes).trim();
            sb.append(new String(bytes, 0, len));
            System.out.println("sb = " + sb);
        }
    }
    
    private static InputStream openConsoleInputStream() {
        return System.in;
    }
    
    private static InputStream openFileInputStream(String url) {
        return null;
    }
    
}
