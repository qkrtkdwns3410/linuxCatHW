package linux.sub.hw20230514;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        
        final String OUPUT_INPUT_FILENAME = "dkaldkjqew-kjasdll-djasdad-ad.txt";
        
        try {
            String word = "HelloWorld";
            
            FileWriteAndRetrieveProcessor fileWriteAndRetrieveProcessor = FileWriteAndRetrieveProcessor.from(OUPUT_INPUT_FILENAME);
            fileWriteAndRetrieveProcessor.writeStringContents(word);
            String contentsInnerFile = fileWriteAndRetrieveProcessor.retrieveInnerFileContents();
            
            System.out.println(contentsInnerFile);
            
        } catch (RuntimeException e) {
            e.printStackTrace();
        }
        
    }
    
}
