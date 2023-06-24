package linux.sub.hw202230611;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class CustomArrayList<T> implements Iterable<T> {
    
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
    
    public T delete(Integer index) {
        checkIndex(index, innerSize);
        T removedValue = (T) elements[index];
        remove(index);
        return removedValue;
    }
    
    private void remove(Integer index) {
        int numMoved = innerSize - index + 1;
        System.arraycopy(elements, index + 1, elements, 0, numMoved);
        innerSize--;
    }
    
    public boolean delete(T target) {
        remove(target);
        return true;
    }
    
    private void remove(T value) {
        try {
            int index = IntStream.range(0, innerSize).filter(i -> Objects.equals(elements[i], value)).findFirst()
                                 .orElseThrow(() -> new NoSuchElementException("삭제할 대상을 찾을 수 없습니다."));
            delete(index);
            innerSize--;
        } catch (RuntimeException e) {
            e.printStackTrace();
        }
    }
    
    
    private void checkIndex(int index, int arrSize) {
        if (index < 0 || index > arrSize) {
            throw new IndexOutOfBoundsException("올바른 INDEX가 아닙니다. 0 ~ 인덱스의 사이즈까지로 지정해주세요");
        }
    }
    
    public boolean addAll(CustomArrayList<T> target) {
        Object[] paramObjects = target.toObjects();
        int wishSize = innerSize + target.size();
        ensureCapacity(wishSize);// 배열의 안전한 삽입을 위해 공간을 확보
        System.arraycopy(paramObjects, 0, elements, innerSize, target.size());
        innerSize += target.size();
        return true;
    }
    
    public T set(int index, T element) {
        checkIndex(index, innerSize);
        T oldValue = (T) elements[index];
        elements[index] = element;
        return oldValue;
    }
    
    public boolean contains(T element) {
        for (Object o : elements) {
            boolean isSame = Objects.equals(o, element);
            if (isSame) {
                return true;
            }
        }
        return false;
    }
    
    public void shuffle() {
        Random random = new Random();
        for (int i = 0; i < innerSize; i++) {
            int j = random.nextInt(innerSize - i) + i;
            T temp = (T) elements[j];
            elements[j] = elements[i];
            elements[i] = temp;
        }
    }
    
    private void ensureCapacity(int wishSize) {
        checkMaxCapacity(wishSize);
        
        boolean notEnoughSpace = elements.length < wishSize;
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
        int increasedSize = elements.length == 0 ? DEFAULT_MAX_CAPACITY : elements.length << 1;
        elements = Arrays.copyOf(elements, increasedSize);
    }
    
    public int size() {
        return innerSize;
    }
    
    private Object[] toObjects() {
        return elements;
    }
    
    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            int index = 0;
            
            @Override
            public boolean hasNext() {
                return index < innerSize;
            }
            
            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException("다음 요소가 존재하지 않습니다");
                }
                return (T) elements[index++];
            }
        };
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CustomArrayList<?> that = (CustomArrayList<?>) o;
        return Arrays.equals(elements, that.elements);
    }
    
    @Override
    public int hashCode() {
        return Arrays.hashCode(elements);
    }
    
    @Override
    public String toString() {
        return new StringJoiner(", ", CustomArrayList.class.getSimpleName() + "[", "]")
                .add("elements=" + Stream.of(elements).limit(size()).map(Objects::toString)
                                         .collect(Collectors.joining(", ", CustomArrayList.class.getSimpleName() + "[", "]")))
                .toString();
    }
}
