package linux.java.file;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * packageName    : linux.java.file
 * fileName       : FileEx3
 * author         : ipeac
 * date           : 2023-04-23
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023-04-23        ipeac       최초 생성
 */
public class FileEx3 {
    static int totalFiles = 0;
    static int totalDirs = 0;
    
    public static void main(String[] args) throws IOException {
        if (args.length != 1) {
            System.out.println("USAGE : java FileEx3 DIRECTORY");
            System.exit(0);
        }
        File dir = new File(args[0]);
        if (!dir.exists() || !dir.isDirectory()) {
            System.out.println("유효하지 않은 디렉토리입니다.");
            System.exit(0);
        }
        printFileList(dir);
        
        System.out.println();
        System.out.println("총 " + totalFiles + " 개의 파일");
        System.out.println("총 " + totalDirs + " 개의 디렉토리");
    }
    
    private static void printFileList(File dir) {
        System.out.println(dir.getAbsolutePath() + " 디렉토리");
        File[] files = dir.listFiles();
        ArrayList subDir = new ArrayList();
        for (int i = 0; i < files.length; i++) {
            String filenames = files[i].getName();
            if (files[i].isDirectory()) {
                filenames = "[" + filenames + "]";
                subDir.add(String.valueOf(i));
            }
            System.out.println(filenames);
        }
        int dirNum = subDir.size();
        int fileNum = files.length - dirNum;
        
        totalFiles += fileNum;
        totalDirs += dirNum;
        
        System.out.println(fileNum + "개의 파일, " + dirNum + "개의 디렉토리");
        System.out.println();
        
        for (int i = 0; i < subDir.size(); i++) {
            int index = Integer.parseInt((String) subDir.get(i));
            printFileList(files[i]);
        }
    }
}
