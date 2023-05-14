package linux.sub.hw20230514;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * packageName    : linux.sub.hw20230514
 * fileName       : PathAndInput
 * author         : ipeac
 * date           : 2023-05-14
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023-05-14        ipeac       최초 생성
 */
public class PathAndInput {
    
    private final int BUFFER_SIZE = 1024;
    private final int BUFFER_BYTE_SIZE = 4;
    
    private final Charset DEFAULT_CHARSET = StandardCharsets.UTF_8;
    private final Input input;
    private final Path path;
    
    
    public PathAndInput(Input input, Path path) throws IOException {
        if (input == null || path == null) {
            throw new IllegalArgumentException("null은 허용되지 않습니다");
        }
        if (!Files.exists(path)) {
            Files.createFile(path);
        }
        this.input = input;
        this.path = path;
    }
    
    
    //newOutputStream 대신에 (일단 파일 덮기 쓰기 전략도 고려해야하는게 맞지만..)
    // Main 문에서 Path를 호출한 경우 만약 해당 위치에 파일이 존재하지 않는 상태로
    // 다른 API 에서 호출된다면 NoSuchFileException 이 발생합니다.
    
    // 그렇기에 newOutputStream 대신에 직접 구현했다면
    // 만약 Files.newOutputStream 이전에 생성자를 PathAndInput( arg0 , arg1)
    // 으로 호출시 생성자에서 Files.exsits 호출로 해당 파일이 있는지 검증했을거같습니다.
    
    // (윗 내용 수정)
    // 혹여나 새로운 메서드가 만들어지는 경우 Path 에 대한 검증실패의 가능성이 있기에,
    // 생성자에서 File 존재 여부에 대해 검증하는것으로 함.
    
    // path객체로 Stream 을 열어주기위해서 일단 newOutputStream 과 newInputStream 을 사용
    public void writeInput() {
        String str = input.getInputString();
        byte[] bStr = str.getBytes(DEFAULT_CHARSET);
        try (BufferedOutputStream bos = openBufferedFileOutputStream(path)) {
            bos.write(bStr);
        } catch (RuntimeException | IOException e) {
            e.printStackTrace();
        }
        
    }
    
    public String readInput() throws IOException {
        StringBuilder sb = new StringBuilder();
        try (BufferedInputStream bis = openBufferedFileInputStream(path)) {
            //스트리밍이란
            byte[] buffer = new byte[BUFFER_BYTE_SIZE];
            while (true) {
                int len = bis.read(buffer);
                boolean eol = len == -1;
                if (eol) {
                    break;
                }
                String s = new String(buffer, 0, len, DEFAULT_CHARSET);
                sb.append(s);
            }
        } catch (RuntimeException | IOException e) {
            e.printStackTrace();
        }
        
        return sb.toString();
    }
    
    public BufferedInputStream openBufferedFileInputStream(Path path) throws IOException {
        InputStream fileInputStream = Files.newInputStream(path);
        return new BufferedInputStream(fileInputStream, BUFFER_SIZE);
    }
    
    public BufferedOutputStream openBufferedFileOutputStream(Path path) throws IOException {
        OutputStream fileOutputStream = Files.newOutputStream(path);
        return new BufferedOutputStream(fileOutputStream, BUFFER_SIZE);
    }
    
}
