// package linux.sub.hw20230610;
//
// import java.io.*;
// import java.net.ServerSocket;
// import java.net.Socket;
// import java.nio.charset.StandardCharsets;
//
// public class SocketTest {
//     public static void main(String[] args) throws IOException {
//         ServerSocket serverSocket = new ServerSocket(7777);
//         System.out.println("대기");
//         Socket socket = serverSocket.accept();
//         System.out.println("연결완료");
//         InputStream is = new BufferedInputStream(socket.getInputStream(), 8192);
//         InputStreamReader isr = new InputStreamReader(is, StandardCharsets.UTF_8);
//         BufferedReader br = new BufferedReader(isr, 8192);
//         k
//         System.out.println("클라이언트 -> 서버 요청 메시지");
//         String lines = br.readLine();
//         String[] split = lines.split(" ");
//         String method = split[0];
//         String uriPath = split[1];
//         String httpVersion = split[2];
//
//         OutputStreamWriter osw = new OutputStreamWriter(new BufferedOutputStream(socket.getOutputStream(), 8192), StandardCharsets.UTF_8);
//         BufferedWriter bw = new BufferedWriter(osw, 8192);
//         System.out.println("서버 -> 클라이언트 응답 메시지");
//         String httpVerson = "HTTP/1.1";
//         String statusCode = "200";
//         String statusMessage = "OK";
//         String join = String.join(" ", httpVerson, statusCode, statusMessage);
//         String psj = "psj:7777,8888";
//         String psj2 = "psj:8888,9999";
//         String message = String.join("\n",join,psj,psj2);
//
//         bw.write(join);
//         bw.newLine();
//         String contentLength = "Content-Length:" + message.length();
//         bw.write(contentLength);
//         bw.newLine();
//         bw.write(psj);
//         bw.newLine();
//         bw.write(psj2);
//         bw.newLine();
//         bw.write("ContentType:plain/text");
//         bw.newLine();
//         bw.newLine();
//         bw.write(message);
//         bw.flush();
//     }
//
// }
