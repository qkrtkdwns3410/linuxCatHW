package linux.sub.main;

import java.util.Objects;

/**
 * packageName    : linux.sub.main
 * fileName       : Command
 * author         : ipeac
 * date           : 2023-04-02
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023-04-02        ipeac       최초 생성
 */
public enum Command {
    CAT("cat", 1), // 상수 순서가 유지보수하면서 변경된다면, 기존의 동작하던 코드들은 전부 오작동하게 되기에 상수 멤버필드로 별도의 값을 정의해서 사용해야한다고 합니다.
    MV("mv", 2),
    CP("cp", 3);
    
    private final String name; // enum 은 본질적으로 불변의 성격을 가지기에 final 로 정의해서 사용해야함
    private final int order;
    
    Command(String name, int order) {
        this.name = name;
        this.order = order;
    }
    
    public String getName() {
        return name;
    }
    
    public static Command getCommandByName(String name) {
        for (Command command : Command.values()) {
//            if (command.getName()
//                        .equalsIgnoreCase(name)) {
//                return command;
//            }
            if (Objects.equals(command.getName(), name)) {
                return command;
            }
        }
        return null;
    }
    
    public int getOrder() {
        return order;
    }
}
