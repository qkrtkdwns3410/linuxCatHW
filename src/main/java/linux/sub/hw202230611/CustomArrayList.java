package linux.sub.hw202230611;

import java.util.Arrays;
import java.util.Objects;
import java.util.StringJoiner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CustomArrayList<T> {
    private static final Object[] DEFAULT_ELEMENTS = {};
    private static final int DEFAULT_MAX_CAPACITY = 10;
    private Object[] elements;
    private final int MAX_ARRAY_SIZE = 2100000000;
    private int innerSize = 0;
    
    public CustomArrayList() {
        this(0);
    }
    
    public CustomArrayList(int capacity) {
        if (capacity == 0) {
            elements = DEFAULT_ELEMENTS;
        } else if (capacity > 0 && capacity < MAX_ARRAY_SIZE) {
            elements = new Object[capacity];
        } else {
            throw new IllegalArgumentException("초깃값은 0 ~ 21억 이하의 정수만 가능합니다. ");
        }
    }
    
    public boolean add(T element) {
        if (elements.length == innerSize) {
            ensureCapacity(innerSize + 1);
        }
        elements[innerSize] = element;
        innerSize++;
        return true;
    }
    
    public boolean addAll(CustomArrayList<T> target) {
        Object[] paramObjects = target.toObjects();
        int wishSize = innerSize + target.size();
        ensureCapacity(wishSize);// 배열의 안전한 삽입을 위해 공간을 확보
        System.arraycopy(paramObjects, 0, elements, innerSize, target.size());
        innerSize += target.size();
        return true;
    }
    
    private void ensureCapacity(int wishSize) {
        boolean notEnoughSpace = elements.length < wishSize;
        checkMaxCapacity(wishSize);
        if (notEnoughSpace) {
            increaseSize();
            ensureCapacity(wishSize);
        }
    }
    
    private void checkMaxCapacity(int wishSize) {
        if (MAX_ARRAY_SIZE < wishSize) {
            throw new IllegalStateException("배열의 허용공간을 초과했습니다.");
        }
    }
    
    private void increaseSize() {
        int increasedSize = elements.length == 0 ? DEFAULT_MAX_CAPACITY : (int) (elements.length * (1.5));
        elements = Arrays.copyOf(elements, increasedSize);
    }
    
    public int size() {
        return innerSize;
    }
    
    public boolean checkMaxCapacity() {
        return true;
    }
    
    private Object[] toObjects() {
        return elements;
    }
    
    @Override
    public String toString() {
        return new StringJoiner(", ", CustomArrayList.class.getSimpleName() + "[", "]")
                .add("elements=" + Stream.of(elements).limit(size()).map(Objects::toString)
                                         .collect(Collectors.joining(", ", CustomArrayList.class.getSimpleName() + "[", "]")))
                .toString();
    }
}
