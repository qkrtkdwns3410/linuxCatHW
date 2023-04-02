package linux.sub.main;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.StringTokenizer;
import java.util.stream.Stream;

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
            try {
                command = Command.valueOf(function.toUpperCase());
            } catch (IllegalArgumentException iae) {
                System.out.println("command not found: " + function);
                continue;
            }
            switch (command) {
                case CAT:
                    while (st.hasMoreTokens()) { // delim 값 을 만나면 포인터를 delim 이후까지 설정합니다. delim 값없이 개행되는경우
                        // 포인터의 위치가 문장의 최대 길이 이상으로 잡히기 때문에
                        // 그때부터는 false 반환하여 다음 단어가 없음을 알려주게됩니다.
                        findFile(st.nextToken()); //해당 파일을 탐색후 출력
                    }
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
     * 파일 복사 기능
     * @param filePathFrom 이동할 파일의 경로 (파일이름 포함)
     * @param filePathTo 최종 이동시킬 파일의 경로 (파일이름 제외)
     **/
    public static void copyFile(String filePathFrom, String filePathTo) throws IOException {
        File source = new File(filePathFrom);
        File destination = new File(filePathTo);
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
    * @param filePathFrom 이동할 파일의 경로 (파일이름 포함)
     * @param filePathTo 최종 이동시킬 파일의 경로 (파일이름 포함)
    **/
    public static void moveFile(String filePathFrom, String filePathTo) {
        File source = new File(filePathFrom);
        File destination = new File(filePathTo + "/" + source.getName());
        System.out.println("filePathTo = " + filePathTo+"/"+source.getName());
        //파일 소스의 경로를 변경해줌으로서 파일 이동 효과
        if (!source.renameTo(destination)) { //security manager 에서 source와 destination 디렉토리에 글을 쓸수있는지 체크하고 source 파일 리네임
            StringBuilder msg = new StringBuilder();
            // 어느 디렉토리 부분이 잘못되었는지 체크하기 위하여 StringBuilder 선언
            if (!source.exists()) { 
                msg.append(source)
                        .append("  ");
            }
            if (!destination.exists()) {
                msg.append(destination);
            }
            System.out.println("파일 이동에 실패했습니다." + msg.replace(msg.indexOf(" "), msg.indexOf(" ") + 1, ", ") + "가 존재하지 않습니다.");
        }
        
        
    }
    
    /**
     * 파일 찾아서 파일의 내용을 출력합니다.
     * @param filePath
     * @throws IOException
     */
    public static void findFile(String filePath) throws IOException {
        Path path = Paths.get(filePath);
        //  try-with-resource 구문사용 Stream 메모리 누수방지
        try (Stream<String> fileContents = Files.lines(path)) {
            fileContents.forEach(System.out::println);
        } catch (NoSuchFileException nsfe) {
            System.out.println();
        }
        
    }
    
}
