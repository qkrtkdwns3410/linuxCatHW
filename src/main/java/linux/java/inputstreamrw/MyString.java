package linux.java.inputstreamrw;

/**
 * packageName    : linux.java.inputstreamrw
 * fileName       : MyString
 * author         : ipeac
 * date           : 2023-04-22
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023-04-22        ipeac       최초 생성
 */
public class MyString {
    private final byte[] values;
    
    public MyString(byte[] values) {
        this.values = values;
    }
    
    public static MyString from(String string) {
        byte[] newValues = new byte[string.length()];
        for (int i = 0; i < string.length(); i++) {
            int c = string.charAt(i);
            newValues[i] = (byte) c;
        }
        return new MyString(newValues);
    }
    
    public void subString(int beginIndex, int endIndex) {
        for (int i = beginIndex; i < endIndex; i++) {
            byte value = values[i];
            if (value == 97) {
                System.out.print('a');
            } else if (value == 98) {
                System.out.print('b');
            } else if (value == 99) {
                System.out.print('c');
            }
        }
        System.out.println();
    }
    
    public void print() {
        subString(0, values.length);
    }
    
    public byte[] getBytes() {
        byte[] newBytes = new byte[this.values.length];
        for (int i = 0; i < newBytes.length; i++) {
            newBytes[i] = values[i];
        }
        return newBytes;
    }
}
