package linux.sub.hw20230514;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class Main {
    public static void main(String[] args) throws IOException {
        
        final String OUPUT_INPUT_FILENAME = "dkaldkjqew-kjasdll-djasdad-ad.txt";
        
        try {
            String word = "HelloWorld";
            
            //대분류 파일 쓴다, 읽는다
            // 파일을 읽는 클래스에서 필요한 생성자 ( 필수적으로 들어가야할 인자 ) 는 파일의 경로가 있겠다.
            // 파일을 쓰는 클래스도 마찬가지로 어디에 쓸지는 필수적이기에 파일의 경로를 생성자로 넣는다.
            // 하지만 파일을 쓸 내용 자체는 파라미터로 받는게 나을거같다..
            // 파일에 쓸 내용을 파라미터 인자로 넣는 이유?
            // -> 현재 요구사항 자체는 HelloWorld 라고 고정이 되어 있는 경우는 솔직히 생성자로 받아도 상관은 없을거같긴하다.
            // -> 하지만 생성자에 쓸내용까지 넣는건 뭔가 가독성이 좋지 않을거같다.
            // write() 함수에 무슨 내용을 쓸건지 인자로 들어가지 않는다면, **얘가 뭘 쓰는거야? 라고 생각할거같다**
            
        } catch (RuntimeException e) {
            e.printStackTrace();
        }
        
    }
    
    public static class Reader {
        InputStream inputStream;
        
        public Reader(InputStream inputStream) {
            this.inputStream = inputStream;
        }
        
        public String streamingString(String word) {
            byte[] buffer = new byte[1024];
            while (true) {
                try {
                    int len = inputStream.read(buffer);
                    if (len == -1) {
                        break;
                    }
                    
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                
            }
        }
        
        public static Reader fromFilepath(String filepath) {
            InputStream fileInputStream = openFileInputStream(filepath);
            return new Reader(fileInputStream);
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
        
        public static Writer from(String filepath) {
            return new Writer();
        }
        
    }
    
}
