package linux.sub.hw20230624;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Objects;

public class MainSocket {
    public static void main(String[] args) {
        String word = "\r\n";
        byte[] bword = word.getBytes(StandardCharsets.UTF_8);
        System.out.println("bword = " + Arrays.toString(bword));
        try {
            ServerSocket serverSocket = new ServerSocket(7777);
            Socket socket = serverSocket.accept();
            System.out.println("연결됨");
            OutputStream os = new BufferedOutputStream(System.out, 8192);
            boolean checkEndHeader = false;
            try (
                    InputStream is = new BufferedInputStream(socket.getInputStream(), 8192);
            ) {
                byte[] bytes = new byte[1024];
                while (true) {
                    int len = is.read(bytes);
                    
                    for (byte aByte : bytes) {
                        if (aByte == 13) {
                        
                        }
                    }
                    
                    if (len == -1 || checkEndHeader) {
                        break;
                    }
                    os.write(bytes, 0, len);
                    os.flush();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
