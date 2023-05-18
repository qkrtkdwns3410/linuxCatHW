package linux.sub.hw20230514;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileWriteAndRetrieveProcessor {
    
    private final int BUFFER_SIZE = 1024;
    private final int BUFFER_BYTE_SIZE = 4;
    
    private final Charset DEFAULT_CHARSET = StandardCharsets.UTF_8;
    private final Path path;
    
    
    public FileWriteAndRetrieveProcessor(Path fileLocation) {
        if (fileLocation == null) {
            throw new IllegalArgumentException("null은 허용되지 않습니다");
        }
        this.path = fileLocation;
    }
    
    public void writeStringContents(String writableContent) {
        byte[] bStr = writableContent.getBytes(DEFAULT_CHARSET);
        try (BufferedOutputStream bos = openBufferedFileOutputStream(path)) {
            bos.write(bStr);
        } catch (RuntimeException | IOException e) {
            e.printStackTrace();
        }
    }
    
    public String retrieveInnerFileContents() {
        StringBuilder sb = new StringBuilder();
        try (BufferedInputStream bis = openBufferedFileInputStream(path)) {
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
    
    public BufferedInputStream openBufferedFileInputStream(Path path) {
        InputStream fileInputStream = null;
        try {
            fileInputStream = Files.newInputStream(path);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return new BufferedInputStream(fileInputStream, BUFFER_SIZE);
    }
    
    public BufferedOutputStream openBufferedFileOutputStream(Path path) {
        OutputStream fileOutputStream = null;
        try {
            fileOutputStream = Files.newOutputStream(path);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return new BufferedOutputStream(fileOutputStream, BUFFER_SIZE);
    }
    
    public static FileWriteAndRetrieveProcessor from(String fileLocation) {
        Path paths = Paths.get(fileLocation);
        return new FileWriteAndRetrieveProcessor(paths);
    }
    
}
