package linux.sub.main;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
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
            try {
                
                StringTokenizer st = new StringTokenizer(words, " ");
                String function = st.nextToken();
                /*
                 * // valueOf api 사용을 => Enum 클래스 내부에서 정적 메서드 생성 후 내부적으로 values api 를 통해 일치하는 값을 들고올수 있도록 변경함 **
                 * 2023.04.03
                 * @박상준
                 * */
                command = Command.getCommandByName(function.toLowerCase());
                if (command == null) {
                    System.out.println("command not found: " + function);
                    continue;
                }
                try {
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
                            if (st.countTokens() != 2) {
                                throw new ParameterInaccurateException();
                            }
                            String fromForCP = st.nextToken();
                            String toForCP = st.nextToken();
                            copyFile(fromForCP, toForCP);
                            
                            break;
                        
                        case MV:
                            if (st.countTokens() != 2) {
                                throw new ParameterInaccurateException();
                            }
                            String fromForMV = st.nextToken();
                            String toForMV = st.nextToken();
                            moveFile(fromForMV, toForMV);
                            
                            break;
                        
                        default:
                            break;
                        
                    }
                } catch (NoSuchFileException | FileNotFoundException e) {
                    System.out.println();
                } catch (NoSuchElementException nsee) {
                    System.out.println("올바른 경로 매개변수가 아닙니다.");
                } catch (ParameterInaccurateException e) {
                    System.out.println("파라미터 개수가 올바르지 않습니다..");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } catch (NoSuchElementException e) {
                System.out.println();
            } catch (Exception e) {
                e.printStackTrace();
            }
            
        }
    }
    
    /**
     * List<String> to List<Path>
     *
     * @param filePaths String 클래스 리스트
     * @return List<Path>  Path 클래스 리스트
     */
    public static List<Path> convertStringToPath(List<String> filePaths) {
        List<Path> paths = new ArrayList<>();
        for (String filePath : filePaths) {
            paths.add(Paths.get(filePath));
        }
        return paths;
    }
    
    /**
     * 파일 복사 기능 => 해당 위치에 이미 같은 이름의 파일이 있는 경우 덮어 쓰도록 한다.
     *
     * @param filePathFrom 이동할 파일의 경로 (파일이름 포함)
     * @param filePathTo   최종 이동시킬 파일의 경로 (파일이름 포함)
     **/
    public static void copyFile(String filePathFrom, String filePathTo) throws IOException {
        if (checkFilePathDup(filePathFrom, filePathTo)) return;
        createFileInputOutputStream(filePathFrom, filePathTo);
    }
    
    /**
     * 파일 이동기능
     *
     * @param filePathFrom 이동할 파일의 경로 (파일이름 포함)
     * @param filePathTo   최종 이동시킬 파일의 경로 (파일이름 포함)
     **/
    public static void moveFile(String filePathFrom, String filePathTo) throws IOException {
        if (checkFilePathDup(filePathFrom, filePathTo)) return;
        createFileInputOutputStream(filePathFrom, filePathTo);
        removeFilePathFrom(filePathFrom); //남아있는 파일을 삭제합니다.
    }
    
    /**
     * 파일을 삭제합니다.
     *
     * @param filePathFrom 기존 파일의 위치
     */
    private static void removeFilePathFrom(String filePathFrom) throws IOException {
        Path path = Paths.get(filePathFrom);
        try {
            Files.delete(path);
        } catch (NoSuchFileException e) {
            System.out.println("원본 파일이 존재하지 않습니다.");
        }
    }
    
    
    /**
     * 파일에 대한 입출력을 정의한 메서드입니다.
     *
     * @param filePathFrom 파일 소스
     * @param filePathTo   파일 목적지
     * @throws IOException
     */
    private static void createFileInputOutputStream(String filePathFrom, String filePathTo) throws IOException {
        try (InputStream is = openBufferedFileInputStream(filePathFrom); OutputStream os = openBufferedFileOutputStream(filePathTo)) {
            byte[] bytes = new byte[1024 * 1024]; // 일시적으로 1M을  버퍼에 저장하고, 요청시마다 일정량씩 버퍼에서 가져와 전달
            while (true) {
                int len = is.read(bytes);
                if (len == -1) {
                    break;
                }
                os.write(bytes, 0, len);
            }
            os.flush();
            
        } catch (NoSuchFileException nsfe) {
            System.out.println();
        }
    }
    
    /**
     * 파일이 복사되는 경로가 동일한지 체크합니다.
     *
     * @param filePathFrom 파일 소스
     * @param filePathTo   파일 목적지
     * @return boolean
     */
    private static boolean checkFilePathDup(String filePathFrom, String filePathTo) {
        if (filePathFrom.equals(filePathTo)) {
            System.out.println("파일 경로가 동일합니다");
            return true;
        }
        return false;
    }
    
    
    /**
     * 파일 찾아서 파일의 내용을 출력합니다.
     *
     * @param filePath 찾을 파일들
     * @throws IOException
     */
    public static void findFile(List<String> filePath) throws IOException {
        List<Path> paths = convertStringToPath(filePath);
        List<String> contents = readAndWriteFiles(paths);
        printContents(contents);
    }
    
    /**
     * List<Path> 반복 => inputStream 을 통해 파일을 읽고 outputStream 에 해당 파일 내용을 담은 값을 return 해줍니다..
     *
     * @param paths 받은 파일의 경로 리스트
     * @return List<String> 파일을 String 으로 변환
     */
    private static List<String> readAndWriteFiles(List<Path> paths) throws IOException {
        List<String> fileContents = new ArrayList<>();
        for (Path path : paths) {
            try (InputStream is = openBufferedFileInputStream(path.toString());ByteArrayOutputStream bos =new ByteArrayOutputStream(); BufferedOutputStream os =  openBufferedByteArrayOutputStream(bos)) {
                byte[] bytes = new byte[1024 * 1024];
                while (true) {
                    int len = is.read(bytes);
                    if (len == -1) {
                        break;
                    }
                    os.write(bytes, 0, len);
                    
                }
                os.flush();
                
                // buffer 내의 데이터를 모두 출력합니다. => 버퍼링된 데이터를 출력 스트림으로 반환합니다.
                // BufferedOutputStream 을 사용하면 내부적으로 출력 스트림에 쓰여질 데이터를 미리 버퍼링하기 때문에
                // 데이터의 양이 적은 경우에는 버퍼에 계속 저장되어있습니다.
                
                //그러므로 flush 를 호출함으로서 버퍼링된 데이터를 완벽하게 출력하고 기존에 남아있는 데이터를 모두 출력스트림으로 내보내어
                //다음 동작을 할때 전 동작에서 남은 데이터에 영향을 받지 않고
                // 메모리상으로 관리가 유리할 수 있도록 사용합니다.
                fileContents.add(bos.toString(StandardCharsets.UTF_8));
                // UTF-8 변환을 위해 ByteArrayOutputStream을 미리 선언후
                //bufferd output에 매개변수로 담아 스트림을 엽니다.
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
    public static InputStream openFileInputStream(String filename) throws FileNotFoundException {
        return new FileInputStream(filename);
    }
    
    /**
     * 해당 파일에 대한 BufferedInputStream을 열어줍니다.
     * @param filename 파일이름
     * @return
     * @throws FileNotFoundException
     */
    public static InputStream openBufferedFileInputStream(String filename) throws FileNotFoundException {
        return new BufferedInputStream(new FileInputStream(filename));
    }
    
    /**
     * 해당 파일에 대한 BufferdOutputStream을 열어줍니다.
     * @param filename 파일 이름
     * @return
     * @throws FileNotFoundException
     */
    public static OutputStream openBufferedFileOutputStream(String filename) throws FileNotFoundException {
        return new BufferedOutputStream(new FileOutputStream(filename));
    }
    
    /**
     * 콘솔에 데이터를 쓰기위한 전초 작업으로 데이터출력 스트림을 열어줍니다.
     * @param bos
     * @return
     */
    public static BufferedOutputStream openBufferedByteArrayOutputStream(ByteArrayOutputStream bos) {
        return new BufferedOutputStream(bos);
    }
    
}
