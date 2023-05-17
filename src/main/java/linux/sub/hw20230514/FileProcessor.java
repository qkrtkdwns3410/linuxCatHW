package linux.sub.hw20230514;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.MessageFormat;
import java.util.Optional;

public class FileProcessor {
    
    /*
    Path 등을 기본 멤버변수로 가집니다.
    기존Input 의 경우 말씀하신대로 그냥 클래스로 감싸서 포장을 했을 뿐 아무 의미없는 맹목적인 코딩이였습니다.
    
    그래서 해당 Input 안의 String HelloWorld의 경우 인자로 받는걸로 변경했습니다.
    
    입력컨텍스트에서 전달된 값이
    * */
    
    /**
     * 버퍼사이즈
     */
    private final int BUFFER_SIZE = 1024;
    /**
     * 바이트 처리단위
     */
    private final int BUFFER_BYTE_SIZE = 4;
    
    /**
     * 기
     */
    private final Charset DEFAULT_CHARSET = StandardCharsets.UTF_8;
    private final Path path;
    
    
    public FileProcessor(Path fileLocation) throws IOException {
        if (fileLocation == null) {
            throw new IllegalArgumentException("null은 허용되지 않습니다");
        }
        if (!Files.exists(fileLocation)) {
            Files.createFile(fileLocation);
        } else {
            String errMsg = MessageFormat.format("이미 {0} 파일이 존재합니다", getFilenameOrNull(fileLocation));
            throw new IllegalStateException(errMsg);
        }
        this.path = fileLocation;
    }
    
    public void writeStringContent(String writableContent) { // word는 write한 곳에서만 사용한다.
        byte[] bStr = writableContent.getBytes(DEFAULT_CHARSET);
        try (BufferedOutputStream bos = openBufferedFileOutputStream(path)) {
            bos.write(bStr);
        } catch (RuntimeException | IOException e) {
            e.printStackTrace();
        }
        
    }
    
    public String readFileContent() throws IOException {
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
    
    /**
     * path 로의 변환만을 수행한다.
     *
     * @param fileLocation
     * @return ContentProcessor
     * @throws IOException
     */
    public static FileProcessor from(String fileLocation) throws IOException {
        Path paths = Paths.get(fileLocation);
        return new FileProcessor(paths);
    }
    
    /**
     * Path에 대한 파일 이름을 가져옵니다.
     *
     * @param path
     * @return
     */
    public Optional<String> getFilenameOrNull(Path path) {
        String filename = path.getFileName().toString();
        return Optional.ofNullable(filename);
    }
}
