package linux.sub.hw20230514;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;

import static java.nio.file.Files.newInputStream;
import static java.nio.file.Files.newOutputStream;
import static java.nio.file.Paths.get;

public class WriteFileAndRetrieveContentProcessor {

    private final int BUFFER_SIZE = 1024;
    private final int BUFFER_BYTE_SIZE = 4;

    private final Charset DEFAULT_CHARSET = StandardCharsets.UTF_8;
    private final Path path;


    public WriteFileAndRetrieveContentProcessor(Path fileLocation) {
        if (fileLocation == null) {
            throw new IllegalArgumentException("null은 허용되지 않습니다");
        }
        this.path = fileLocation;
    }

    public void writeStringContents(String writableContent) {
        byte[] contentBytes = writableContent.getBytes(DEFAULT_CHARSET);
        try (BufferedOutputStream bos = openBufferedFileOutputStream(path)) {
            bos.write(contentBytes);
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
                String decodedString = new String(buffer, 0, len, DEFAULT_CHARSET);
                sb.append(decodedString);
            }
        } catch (RuntimeException | IOException e) {
            e.printStackTrace();
        }

        return sb.toString();
    }

    private BufferedInputStream openBufferedFileInputStream(Path path) throws IOException {
        InputStream fileInputStream = newInputStream(path);
        return new BufferedInputStream(fileInputStream, BUFFER_SIZE);
    }

    private BufferedOutputStream openBufferedFileOutputStream(Path path) throws IOException {
        OutputStream fileOutputStream = newOutputStream(path);
        return new BufferedOutputStream(fileOutputStream, BUFFER_SIZE);
    }

    public static WriteFileAndRetrieveContentProcessor from(String fileLocation) {
        Path paths = get(fileLocation);
        return new WriteFileAndRetrieveContentProcessor(paths);
    }

}
