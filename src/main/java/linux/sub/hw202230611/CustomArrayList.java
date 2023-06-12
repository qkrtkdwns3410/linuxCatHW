package linux.sub.hw202230611;

import java.util.Arrays;
import java.util.Objects;
import java.util.StringJoiner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
public class CustomArrayList<T> {
    private static final Object[] DEFAULT_ELEMENTS = {};
    private static Object[] elements;
    private static final int DEFAULT_MAX_CAPACITY = 10;
    private int innerSize = 0;
    
    public CustomArrayList() {
        this(0);
    }
    
    public CustomArrayList(int capacity) {
        if (capacity == 0) {
            elements = DEFAULT_ELEMENTS;
        } else if (capacity > 0) {
            elements = new Object[capacity];
        } else {
            throw new IllegalArgumentException("초깃값은 0 혹은 양수여야합니다.");
        }
    }
    
    public boolean add(T element) {
        if (elements.length == innerSize) {
            increaseSize();
        }
        elements[innerSize] = element;
        innerSize++;
        return true;
    }
    
    public boolean addAll(CustomArrayList<T> customArrayList) {
        Object[] paramObjects = customArrayList.toObjects();
        
        return true;
    }
    
    private void increaseSize() {
        copyElements();
    }
    
    private void copyElements() {
        int increasedSize = elements.length == 0 ? DEFAULT_MAX_CAPACITY : (int) (elements.length * (1.5));
        elements = Arrays.copyOf(elements, increasedSize);
    }
    
    public int size() {
        return innerSize;
    }
    
    public boolean checkCapacity() {
        return true;
    }
    public 
    
    private Object[] toObjects() {
        return Arrays.stream(elements).toArray();
    }
    
    @Override
    public String toString() {
        return new StringJoiner(", ", CustomArrayList.class.getSimpleName() + "[", "]")
                .add("elements=" + Stream.of(elements).limit(innerSize).map(Objects::toString)
                                         .collect(Collectors.joining(", ", CustomArrayList.class.getSimpleName() + "[", "]")))
                .toString();
    }
}
