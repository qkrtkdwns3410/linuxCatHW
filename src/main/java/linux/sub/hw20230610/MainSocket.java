package linux.sub.hw20230610;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

/**
 * packageName    : linux.sub.hw20230610
 * fileName       : MainSocket
 * author         : ipeac
 * date           : 2023-06-10
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023-06-10        ipeac       최초 생성
 */
public class MainSocket {
    public static void main(String[] args) throws IOException {
        // 서버 소켓을 생성하는 부분입니다,
        ServerSocket serverSocket = new ServerSocket(7777);
        while (true) {
            System.out.println("대기");
            Socket socket = serverSocket.accept();
            System.out.println("연결완료");
            
            BufferedInputStream bis = new BufferedInputStream(socket.getInputStream(), 50);
            InputStreamReader isr = new InputStreamReader(bis, StandardCharsets.UTF_8);
            BufferedReader br = new BufferedReader(isr, 50);
            
            String startLine = br.readLine();
            String[] startLines = startLine.split(" ");
            String method = startLines[0];
            String requestUri = startLines[1];
            String httpVersion = startLines[2];
            System.out.println("method = " + method);
            System.out.println("requestUri = " + requestUri);
            System.out.println("httpVersion = " + httpVersion);
/*
            InputStream is =
            
            
            String content = "<h1>Hello world</h1>";
            OutputStreamWriter osw = new OutputStreamWriter(new BufferedOutputStream(socket.getOutputStream(), 8192), StandardCharsets.UTF_8);
            BufferedWriter bw = new BufferedWriter(osw, 8192);
            bw.write("HTTP/1.1 200 OK");
            bw.newLine();
            bw.write("Content-Length:" + content.length());
            bw.newLine();
            bw.write("Content-Type:text/plain");
            bw.newLine();
            bw.newLine();
            bw.write(content);
            bw.flush();*/
            
        }
    }
    
}
