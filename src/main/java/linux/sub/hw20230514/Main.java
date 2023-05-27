package linux.sub.hw20230514;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class Main {
    public static void main(String[] args) throws IOException {
        
        final String OUTPUT_INPUT_FILENAME = "dkaldkjqew-kjasdll-djasdad-ad.txt";
        
        String word = "HelloWorld jald asdl jadljalsdjasj dljlasdjasjdjalsd asjld jaldl ajl jasldjlaj asjljas ad j\nasdjkahdasdaljdasljdasldhlasdhlasd";
        
        //대분류 파일 쓴다, 읽는다
        // 파일을 읽는 클래스에서 필요한 생성자 ( 필수적으로 들어가야할 인자 ) 는 파일의 경로가 있겠다.
        // 파일을 쓰는 클래스도 마찬가지로 어디에 쓸지는 필수적이기에 파일의 경로를 생성자로 넣는다.
        // 하지만 파일을 쓸 내용 자체는 파라미터로 받는게 나을거같다..
        // 파일에 쓸 내용을 파라미터 인자로 넣는 이유?
        // -> 현재 요구사항 자체는 HelloWorld 라고 고정이 되어 있는 경우는 솔직히 생성자로 받아도 상관은 없을거같긴하다.
        // -> 하지만 생성자에 쓸내용까지 넣는건 뭔가 가독성이 좋지 않을거같다.
        // write() 함수에 무슨 내용을 쓸건지 인자로 들어가지 않는다면, **얘가 뭘 쓰는거야? 라고 생각할거같다**
        Reader wordContent = Reader.fromString(word);
        
        Writer fileWriter = Writer.fromFilePath(OUTPUT_INPUT_FILENAME);
        fileWriter.streamingFrom(wordContent);
        
        Reader fileContent = Reader.fromFilepath(OUTPUT_INPUT_FILENAME);
        
        Writer consoleWriter = Writer.fromConsole();
        consoleWriter.streamingFrom(fileContent);
    }
    
    public static class Reader {
        private final InputStream inputStream;
        
        public Reader(InputStream inputStream) {
            if (inputStream == null) {
                throw new IllegalArgumentException("스트림이 닫혀있습니다");
            }
            this.inputStream = inputStream;
        }
        
        public static Reader fromFilepath(String filepath) {
            InputStream fileInputStream = openFileInputStream(filepath);
            return new Reader(fileInputStream);
        }
        
        public static Reader fromString(String word) {
            byte[] words = word.getBytes(StandardCharsets.UTF_8);
            return new Reader(new ByteArrayInputStream(words));
            
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
        private final int BYTE_SIZE = 1024;
        
        //FM 메서드외에는 클래스 생성자에 접근할수없다.
        private Writer(OutputStream outputStream) {
            if (outputStream == null) {
                throw new IllegalArgumentException("스트림이 닫혀있습니다");
            }
            this.outputStream = outputStream;
        }
        
        public static Writer fromFilePath(String filepath) {
            OutputStream fileOutputSteam = openFileOutputStream(filepath);
            return new Writer(fileOutputSteam);
        }
        
        public static Writer fromConsole() {
            OutputStream consoleOutputStream = openConsoleOutputStream();
            return new Writer(consoleOutputStream);
        }
        
        private static OutputStream openConsoleOutputStream() {
            return System.out;
        }
        
        public void streamingFrom(Reader reader) {
            InputStream inputStream = reader.inputStream;
            byte[] bytes = new byte[BYTE_SIZE];
            while (true) {
                try {
                    int len = inputStream.read(bytes);
                    if (len == -1) {
                        break;
                    }
                    outputStream.write(bytes, 0, len);
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    clearOutputStream();
                }
            }
        }
        
        private void clearOutputStream() {
            try {
                outputStream.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
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