package linux.sub.hw202230611;

import java.util.ArrayList;
import java.util.List;

/**
 * packageName    : linux.sub.hw202230611
 * fileName       : List
 * author         : ipeac
 * date           : 2023-06-11
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023-06-11        ipeac       최초 생성
 */
public class Main {
    public static void main(String[] args) {
        // List<String> arr = new ArrayList<>();
        // for (int i = 0; i < 1000000000; i++) {
        //     arr.add("e");
        // }
        // System.out.println("arr = " + arr);
        CustomArrayList<String> customArrayList = new CustomArrayList<>();
        for (int i = 0; i < 2; i++) {
            customArrayList.add("e");
        }
        CustomArrayList<String> customArrayList2 = new CustomArrayList<>();
        for (int i = 0; i < 5; i++) {
            customArrayList2.add("a");
        }
        
        customArrayList2.delete(5);
        customArrayList.addAll(customArrayList2);
        System.out.println("customArrayList.size() = " + customArrayList.size());
        System.out.println("customArrayList = " + customArrayList);
        System.out.println("customArrayList2.size() = " + customArrayList2.size());
        System.out.println("customArrayList2 = " + customArrayList2);
    }
}
