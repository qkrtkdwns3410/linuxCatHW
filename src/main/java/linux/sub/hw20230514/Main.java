package linux.sub.hw20230514;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        
        final String OUPUT_INPUT_FILENAME = "dkaldkjqew-kjasdll-djasdad-ad.txt";
        
        try {
            String word = "HelloWorld";
            
            
            /*
            * 입 처 출 순서대로 코드를 왜 그렇게 짰는지와 의식의 흐름대로의 **의구심** 을 서술했습니다.
            * */
            
            //파일에 대한 스트림을 처리하는 처리클래스입니다.
            //해당 객체는 어느곳에 파일을 위치시킬것인지에 대한 String 인자를 전달한다.
            // => 머릿속에서는 Stirng으로 메모리에 입력된 유니코드형식의 문자열 (입력컨텍스트)를 변환해서 처리컨텍스트에 전달해야한다
            // 라고 그냥 박혀있습니다만.. word를 변환해서 넣어주는 행위자체가 그냥 어색하다고 생각했습니다.
            
            // 그래서 그냥 파일이름에 해당 하는 `String OUPUT_INPUT_FILENAME` 을 FM 메서드에 인자로 전달했습니다.
            // 그리고 해당 String 은
            /*
             public static FileProcessor from(String fileLocation) throws IOException {
                    Path paths = Paths.get(fileLocation);
                    return new FileProcessor(paths);
               }
               
               에서 Path로 변환됩니다.
               
               => 에서 조금 걱정되는 부분 Paths.get 자체가 에러를 던지고있는데 해당 부분이 검증만을 수행한다는 점을 해치고 있지 않은가?..
               
               변환후
               
               생성자 호출
               
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
               
               
               FM 에서 걸리는 부분말고의 모든 인자 검증과 파일이 없는 경우 생성까지는 생성자에서 수행합니다.
               
               2. 두번째로 걸리는 점
               
               입력 컨텍스트가 함수의 인자로 전달되고 있는데 해당 부분이 인자로 전달된다는 점이 컨텍스트간의 독립성과 연관이 없는 지 -> 이해가 부족한거같긴함..
               
               
               
               3. 세번쟤로 걸리는 점
               
               처리 컨텍스트에서 만들어진 자료형인 String 도 결국에 출력컨텍스트에 이니셜라이징된 형태로 전달해줘야하는건지
             */
            FileProcessor fileProcessor = FileProcessor.from(OUPUT_INPUT_FILENAME);
            fileProcessor.writeStringContent(word);
            String returnedResult = fileProcessor.readFileContent();
            
            System.out.println(returnedResult);
            
        } catch (RuntimeException e) {
            e.printStackTrace();
        }
        
    }
    
}
