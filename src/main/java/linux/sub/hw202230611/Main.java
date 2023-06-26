package linux.sub.hw202230611;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
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
        List<String> arr = new ArrayList<>();
        Collections.shuffle(arr);
        CustomArrayList<String> customArrayList = CustomArrayList.from();
        for (int i = 0; i < 2; i++) {
            customArrayList.add("e");
        }
        customArrayList.add("a");
        customArrayList.add("b");
        customArrayList.add("k");
    }
}
