package linux.sub.hw20230624;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class MainSocket {
    private static final int CARRIAGE_RETURN = 13;
    private static final int LINE_FEED = 10;
    private static final byte[] ENTER = new byte[]{CARRIAGE_RETURN, LINE_FEED};
    
    public static void main(String[] args) {
        boolean isSequenceEnter = false;
        try (
                ServerSocket serverSocket = new ServerSocket(8080);
        ) {
            while (true) {
                try (
                        Socket socket = serverSocket.accept();
                        InputStream is = new BufferedInputStream(socket.getInputStream(), 8192);
                        OutputStream socketOs = new BufferedOutputStream(socket.getOutputStream(), 8192);
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
            }
            
        } catch (IOException e) {
        
        }
    }
}
