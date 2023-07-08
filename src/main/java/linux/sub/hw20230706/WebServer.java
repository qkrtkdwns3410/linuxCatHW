package linux.sub.hw20230706;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class WebServer {
    public static void main(String[] args) {
        while (true) {
            try (
                    // 서버 소켓은 서버에서 클라이언트의 연결 요청을 기다리는 역할을 한다.
                    // 소켓을 생성함..
                    ServerSocket serverSocket = new ServerSocket(8080);
            ) {
                // 생성된 소켓은 클라이언트의 요청이 들어올때까지, 블로킹합니다.
                Socket socket = serverSocket.accept();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        
    }
}
