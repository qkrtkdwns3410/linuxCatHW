package linux.sub.main;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * packageName    : linux.sub.main
 * fileName       : Main
 * author         : ipeac
 * date           : 2023-03-31
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023-03-31        ipeac       최초 생성
 */
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader rd = new BufferedReader(new InputStreamReader(System.in)); //콘솔에 입력하기 위한  BufferReader 객체의 생성
        String words = "";
        Command command;
        while ((words = rd.readLine()) != null) { //\n 개행문자를 만날때까지 문자열을 읽어옵니다. 개행문자이후 읽어오는 데이터가 없으면 종료됩니다.
            StringTokenizer st = new StringTokenizer(words, " ");
            String function = st.nextToken();
            /*
             * // valueOf api 사용을 => Enum 클래스 내부에서 정적 메서드 생성 후 내부적으로 values api 를 통해 일치하는 값을 들고올수 있도록 변경함 **
             * 2023.04.03
             * @박상준
             * */
            command = Command.getCommandByName(function);
            if (command == null) {
                System.out.println("command not found: " + function);
                continue;
            }
            switch (command) {
                case CAT:
                    List<String> filePaths = new ArrayList<>();
                    while (st.hasMoreTokens()) { // delim 값 을 만나면 포인터를 delim 이후까지 설정합니다. delim 값없이 개행되는경우
                        // 포인터의 위치가 문장의 최대 길이 이상으로 잡히기 때문에
                        // 그때부터는 false 반환하여 다음 단어가 없음을 알려주게됩니다.
                        filePaths.add(st.nextToken());
                    }
                    findFile(filePaths);
                    break;
                case CP:
                    String fromForCP = st.nextToken();
                    String toForCP = st.nextToken();
                    copyFile(fromForCP, toForCP);
                    break;
                case MV:
                    String fromForMV = st.nextToken();
                    String toForMV = st.nextToken();
                    moveFile(fromForMV, toForMV);
                    break;
                default:
                    break;
            }
        }
    }
    
    /**
     * List<String> to List<Path>
     *
     * @param filePaths
     * @return
     */
    public static List<Path> convertStringToPath(List<String> filePaths) {
        List<Path> paths = new ArrayList<>();
        for (String filePath : filePaths) {
            paths.add(Paths.get(filePath));
        }
        return paths;
    }
    
    /**
     * 파일 복사 기능
     *
     * @param filePathFrom 이동할 파일의 경로 (파일이름 포함)
     * @param filePathTo   최종 이동시킬 파일의 경로 (파일이름 제외)
     **/
    public static void copyFile(String filePathFrom, String filePathTo) throws IOException {
        Paths.get(filePathTo);
        // try - with -resource 구조 안에 is 와 os 선언
        try (InputStream is = new FileInputStream(source); OutputStream os = new FileOutputStream(destination)) {
            byte[] bytes = new byte[1024]; // 1024 바이트 단위로 os에 문자를 쓴다.
            while (true) {
                int len = is.read(bytes);
                if (len == -1) {
                    break;
                }
                os.write(bytes, 0, len);
            }
        } catch (NoSuchFileException nsfe) {
            System.out.println();
        }
    }
    
    /**
     * 파일 이동기능
     *
     * @param filePathFrom 이동할 파일의 경로 (파일이름 포함)
     * @param filePathTo   최종 이동시킬 파일의 경로 (파일이름 포함)
     **/
    public static void moveFile(String filePathFrom, String filePathTo) {
    
    }
    
    
    /**
     * 파일 찾아서 파일의 내용을 출력합니다.
     *
     * @param filePath 찾을 파일들
     * @throws IOException
     */
    public static void findFile(List<String> filePath) throws IOException {
        List<Path> paths = convertStringToPath(filePath);
        List<String> contents = readFiles(paths);
        printContents(contents);
    }
    
    /**
     * List<Path> 반복 => inputStream 을 통해 파일을 읽고 outputStream 에 해당 파일 내용을 담은 값을 return 해줍니다..
     *
     * @param paths 받은 파일의 경로 리스트
     * @return List<String> 파일을 String 으로 변환
     */
    private static List<String> readFiles(List<Path> paths) throws IOException {
        List<String> fileContents = new ArrayList<>();
        for (Path path : paths) {
            try (InputStream is = openFileInputStream(path.toString()); ByteArrayOutputStream os = new ByteArrayOutputStream()) {
                byte[] bytes = new byte[1024];
                while (true) {
                    int len = is.read(bytes);
                    if (len == -1) {
                        break;
                    }
                    os.write(bytes, 0, len);
                }
                fileContents.add(os.toString(StandardCharsets.UTF_8));
            } catch (IOException nsfe) {
                System.out.println();
            }
        }
        return fileContents;
    }
    
    /**
     * 해당 내용을 forEach 를 통해 프린트
     *
     * @param fileContents 파일의 내용이 String 값으로 담겨있음
     */
    public static void printContents(List<String> fileContents) {
        fileContents.forEach(System.out::println);
    }
    
    /**
     * 해당 파일에 대한 FileInputStream 을 열어줍니다.
     *
     * @param filename 파일이름
     * @return InputStream
     * @throws FileNotFoundException
     */
    public static InputStream openFileInputStream(String filename) {
        try {
            return new FileInputStream(filename);
        } catch (FileNotFoundException e) {
            System.out.println(); // 파일을 찾을수 없는 경우 공백 출력
        }
        return null;
    }
    
}
