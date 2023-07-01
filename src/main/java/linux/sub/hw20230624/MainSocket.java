package linux.sub.hw20230624;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class MainSocket {
    private static final int CARRIAGE_RETURN = 13;
    private static final int LINE_FEED = 10;
    private static final byte[] ENTER = new byte[]{CARRIAGE_RETURN, LINE_FEED};
    
    public static void main(String[] args) {
        boolean isSequenceEnter = false;
        try (
                //!서버로부터의 연결 요청을 기다리는 역할입니다. -> 8080 포트에서의 클라이언트의 연결을 기다리는 역할입니다.
                //!예외발생시 자동으로  close() 호출로 자원의 해제를 함.
                ServerSocket serverSocket = new ServerSocket(8080);
        ) {
            //? 연속적인 HTTP 요청과 응답을 받기 위한 while(true)
            while (true) {
                //! 자원의 해제를 위함
                try (
                        //? 서버 소켓이 들어오는 클라이언트의 연결 요청을 기다리다가 요청이 발생하면 해당 클라이언트와 연결한 소켓 객체 반환
                        //! 연결요청이 들어올때까지 블로킹 상태가 됩니다.
                        //! java 11 에서 기본 소켓의 버퍼 사이즈는 65536 바이트.
                        Socket socket = serverSocket.accept();
                        //! 버퍼링 처리 + 클라이언트로부터의 데이터를 읽기위함.
                        InputStream is = new BufferedInputStream(socket.getInputStream(), 8192);
                        OutputStream socketOs = new BufferedOutputStream(socket.getOutputStream(), 8192);
                        //! BufferedWriter 를 사용하기 위한 Writer 클래스
                        OutputStreamWriter osw = new OutputStreamWriter(socketOs, StandardCharsets.UTF_8);
                        BufferedWriter bw = new BufferedWriter(osw, 8192);
                ) {
                    OutputStream os = new BufferedOutputStream(System.out, 8192);
                    
                    byte[] bytes = new byte[1024];
                    byte lastByte = -1;
                    byte secondLastByte = -1;
                    byte thirdLastByte = -1;
                    while (true) {
                        int len = is.read(bytes);
                        for (byte aByte : bytes) {
                            if (aByte == LINE_FEED && lastByte == CARRIAGE_RETURN && secondLastByte == LINE_FEED && thirdLastByte == CARRIAGE_RETURN) {
                                isSequenceEnter = true;
                                break;
                            }
                            thirdLastByte = secondLastByte;
                            secondLastByte = lastByte;
                            lastByte = aByte;
                        }
                        if (len == -1) {
                            System.out.println("종료");
                            break;
                        }
                        os.write(bytes, 0, len);
                        os.flush();
                        bw.flush();
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
                    }
                    os.flush();
                    bw.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
