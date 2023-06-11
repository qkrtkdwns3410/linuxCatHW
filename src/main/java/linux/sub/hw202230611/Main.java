package linux.sub.hw202230611;

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
        CustomArrayList<String> customArrayList = new CustomArrayList<>();
        for (int i = 0; i < 100; i++) {
            customArrayList.add("e");
        }
        System.out.println("customArrayList.size() = " + customArrayList.size());
        System.out.println("customArrayList = " + customArrayList);
    }
}
