package linux.sub.hw20230514;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.MessageFormat;

public class ContentProcessor {
    
    private final int BUFFER_SIZE = 1024;
    private final int BUFFER_BYTE_SIZE = 4;
    
    private final Charset DEFAULT_CHARSET = StandardCharsets.UTF_8;
    private final Path path;
    
    
    public ContentProcessor(Path fileLocation) throws IOException {
        if (fileLocation == null) {
            throw new IllegalArgumentException("null은 허용되지 않습니다");
        }
        if (!Files.exists(fileLocation)) {
            Files.createFile(fileLocation);
        } else {
            String fileName = fileLocation.getFileName().toString();
            String errMsg = MessageFormat.format("이미 {0} 파일이 존재합니다", fileName);
            throw new IllegalStateException(errMsg);
        }
        this.path = fileLocation;
    }
    
    public void writeStringContent(String writableContent) {
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
    
    public static ContentProcessor from(String fileLocation) throws IOException {
        Path paths = Path.of(fileLocation);
        return new ContentProcessor(paths);
    }
}
