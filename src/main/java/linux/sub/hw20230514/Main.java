package linux.sub.hw20230514;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        
        final String OUPUT_INPUT_FILENAME = "dkaldkjqew kjasdll djasdad ad";
        
        try {
            String word = "HelloWorld";
            
            ContentProcessor contentProcessor = ContentProcessor.from(OUPUT_INPUT_FILENAME);
            contentProcessor.writeStringContent(word);
            String returnedResult = contentProcessor.readFileContent();
            
            System.out.println(returnedResult);
            
        } catch (RuntimeException e) {
            e.printStackTrace();
        }
        
    }
    
}
