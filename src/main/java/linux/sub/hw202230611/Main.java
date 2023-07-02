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
        int[] arr = {
                10, 11, 12
        };
        System.out.println(remove(arr, 0));
        System.out.println(remove(arr, 5));
    }

    public static Integer remove(int[] arr, int index) {
        int arrLength = arr.length;
        if (arrLength <= index || 0 > index) {
            return null;
        }
        int oldValue = arr[index];
        arr[index] = -1;
        return oldValue;
    }

}
