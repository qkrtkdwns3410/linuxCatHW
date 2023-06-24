package linux.sub.hw20230624;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class MainSocket {
    private static final int SPACE_ENTER_CNT = 4;
    private static final int CARRIAGE_RETURN = 13;
    private static final int LINE_FEED = 10;
    private static final byte[] ENTER = new byte[]{CARRIAGE_RETURN, LINE_FEED};
    
    public static void main(String[] args) {
        boolean isSequenceEnter = false;
        int sequencialCnt = 0;
        //무한 서버 반복
        while (true) {
            try {
                ServerSocket serverSocket = new ServerSocket(7777);
                Socket socket = serverSocket.accept();
                OutputStream os = new BufferedOutputStream(System.out, 8192);
                
                try (
                        InputStream is = new BufferedInputStream(socket.getInputStream(), 8192);
                        OutputStream socketOs = new BufferedOutputStream(socket.getOutputStream(), 8192);
                        OutputStreamWriter osw = new OutputStreamWriter(socketOs, StandardCharsets.UTF_8);
                        BufferedWriter bw = new BufferedWriter(osw, 8192);
                ) {
                    byte[] bytes = new byte[1024];
                    while (true) {
                        int len = is.read(bytes);
                        // CRLF 순서를 지켰는지 체크할것
                        for (byte aByte : bytes) {
                            if (aByte == CARRIAGE_RETURN || aByte == LINE_FEED) {
                                sequencialCnt++;
                            } else {
                                sequencialCnt = 0;
                            }
                        }
                        if (sequencialCnt == SPACE_ENTER_CNT) {
                            isSequenceEnter = true;
                            break;
                        }
                        if (len == -1) {
                            System.out.println("종료");
                            break;
                        }
                        os.write(bytes, 0, len);
                        if (isSequenceEnter) {
                            os.write(ENTER);
                            bw.write("HTTP/1.1 200 OK");
                            bw.newLine();
                            bw.write("Content-Type: text/html");
                            bw.newLine();
                            bw.newLine();
                            bw.write("<h2>Hello World</h2>");
                            break;
                        }
                        os.flush();
                        bw.flush();
                    }
                    os.flush();
                    bw.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            
        }
    }
}
