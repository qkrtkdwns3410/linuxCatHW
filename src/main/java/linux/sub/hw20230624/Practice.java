package linux.sub.hw20230624;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

import static java.nio.charset.StandardCharsets.UTF_8;

/**
 * packageName    : linux.sub.hw20230624
 * fileName       : Practice
 * author         : ipeac
 * date           : 2023-07-01
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023-07-01        ipeac       최초 생성
 */
public class Practice {
    public static void main(String[] args) {
        try (
                ServerSocket serverSocket = new ServerSocket(8080);
        ) {
            while (true) {
                try (
                        Socket socket = serverSocket.accept();
                        InputStream is = new BufferedInputStream(socket.getInputStream(), 8192);
                        OutputStream os = new BufferedOutputStream(socket.getOutputStream(), 8192);
                        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(os, StandardCharsets.UTF_8));
                ) {
                    OutputStream consoleOs = new BufferedOutputStream(System.out, 8192);
                    byte[] bytes = new byte[1024];
                    byte lastByte = -1;
                    byte secondLastByte = -1;
                    byte thirdLastByte = -1;
                    boolean isSequenceEnter = false;
                    while (true) {
                        int len = is.read(bytes);
                        for (byte aByte : bytes) {
                            if (aByte == 10 && lastByte == 13 && secondLastByte == 10 && thirdLastByte == 13) {
                                isSequenceEnter = true;
                                break;
                            }
                            thirdLastByte = secondLastByte;
                            secondLastByte = lastByte;
                            lastByte = aByte;
                        }
                        consoleOs.write(bytes, 0, len);
                        consoleOs.flush();
                        if (isSequenceEnter) {
                            String jsonData = "{\"code\":\"value1\",\"status\":\"200\"}";
                            bw.write("HTTP/1.1 200 OK");
                            bw.newLine();
                            bw.write("Content-Type: application/json");
                            bw.newLine();
                            bw.write("Content-Length: " + jsonData.getBytes(UTF_8).length);
                            bw.newLine();
                            bw.newLine();
                            bw.write(jsonData);
                            bw.flush();
                            break;
                        }
                        if (len == -1) {
                            break;
                        }
                        
                    }
                    consoleOs.flush();
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
