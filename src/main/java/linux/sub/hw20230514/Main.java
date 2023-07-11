package linux.sub.hw20230514;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.function.Consumer;

public class Main {
    public static void main(String[] args) throws IOException {
        
        final String OUTPUT_INPUT_FILENAME = "dkaldkjqew-kjasdll-djasdad-ad.txt";
        
        String word = "HelloWorld jald asdl jadljalsdjasj dljlasdjasjdjalsd asjld jaldl ajl jasldjlaj asjljas ad j\nasdjkahdasdaljdasljdasldhlasdhlasd";
        
        Reader wordContent = Reader.fromString(word);
        try (OutputStreamWriter isr = new OutputStreamWriter(new FileOutputStream(OUTPUT_INPUT_FILENAME), StandardCharsets.UTF_8)) {
            wordContent.streaming(str -> {
                writeToFile(isr, str);
            });
        }

        
        Reader fileContent = Reader.fromFilepath(OUTPUT_INPUT_FILENAME);
        fileContent.streaming(System.out::print);
    }
    
    private static void writeToFile(OutputStreamWriter isr, String str) {
        try {
            isr.write(str);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    
    public static class Reader {
        private final InputStreamReader isr;
        private final int BYTE_SIZE = 1024;
        
        public Reader(InputStreamReader inputStreamReader) {
            if (inputStreamReader == null) {
                throw new IllegalArgumentException("스트림이 닫혀있습니다");
            }
            this.isr = inputStreamReader;
        }
        
        public static Reader fromFilepath(String filepath) {
            InputStreamReader isr = new InputStreamReader(openFileInputStream(filepath), StandardCharsets.UTF_8);
            return new Reader(isr);
        }
        
        public static Reader fromString(String word) {
            byte[] words = word.getBytes(StandardCharsets.UTF_8);
            InputStreamReader isr = new InputStreamReader(new ByteArrayInputStream(words), StandardCharsets.UTF_8);
            return new Reader(isr);
        }
        
        public void streaming(Consumer<String> cp) {
            char[] chars = new char[BYTE_SIZE];
            while (true) {
                try {
                    int len = isr.read(chars);
                    if (len == -1) {
                        break;
                    }
                    String str = new String(chars, 0, len);
                    cp.accept(str);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        
        public static InputStream openFileInputStream(String filePath) {
            InputStream fileInputStream = null;
            try {
                fileInputStream = new FileInputStream(filePath);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return fileInputStream;
        }
    }
    
    public static class Writer {
        private final OutputStream outputStream;
        
        //FM 메서드외에는 클래스 생성자에 접근할수없다.
        private Writer(OutputStream outputStream) {
            if (outputStream == null) {
                throw new IllegalArgumentException("스트림이 닫혀있습니다");
            }
            this.outputStream = outputStream;
        }
        
        public static Writer fromConsole() {
            OutputStream consoleOutputStream = openConsoleOutputStream();
            return new Writer(consoleOutputStream);
        }
        
        private static OutputStream openConsoleOutputStream() {
            return System.out;
        }
        
        private static OutputStream openFileOutputStream(String filepath) {
            OutputStream fileOutputStream = null;
            try {
                fileOutputStream = new FileOutputStream(filepath);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            return fileOutputStream;
        }
    }
    
}
