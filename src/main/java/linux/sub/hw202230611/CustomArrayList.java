package linux.sub.hw202230611;

import java.util.*;
import java.util.stream.IntStream;

import static java.lang.Math.ceil;
import static java.lang.Math.log;

public class CustomArrayList<T> implements Iterable<T> {
    
    private static final Object[] DEFAULT_ELEMENTS = {};
    private static final int DEFAULT_CAPACITY = 10;
    private Object[] elements;
    //!MAX_ARRAY_SIZE -> 가독성을 위해 만단위 숫자그룹핑
    private final int MAX_ARRAY_SIZE = 21_0000_0000;
    //!innerSize -> index로 변수명 변경
    private int elementsIndex = 0;
    
    public static <T> CustomArrayList<T> from() {
        //! 기본용량을 10으로 고정했습니다.
        return new CustomArrayList<>(DEFAULT_CAPACITY);
    }
    
    public static <T> CustomArrayList<T> from(int capacity) {
        return new CustomArrayList<>(capacity);
    }
    
    private CustomArrayList(int capacity) {
        if (capacity == 0) {
            elements = DEFAULT_ELEMENTS;
        } else if (capacity > 0 && capacity < MAX_ARRAY_SIZE) {
            elements = new Object[capacity];
        } else {
            throw new IllegalArgumentException("초깃값은 0 ~ 21억 이하의 정수만 가능합니다. ");
        }
    }
    
    public boolean add(T element) {
        if (elements.length == elementsIndex) {
            ensureCapacity();
        }
        elements[elementsIndex] = element;
        elementsIndex++;
        return true;
    }
    
    public T delete(Integer index) {
        checkIndex(index);
        T removedValue = (T) elements[index];
        remove(index);
        elementsIndex--;
        return removedValue;
    }
    
    //! 객체의 사이즈제어의 경우 public 에서만 제어하도록 변경함
    //?객체의 삭제되어야하는 부분을 다시 조정했습니다.
    private void remove(Integer index) {
        int backIndex = elementsIndex - index - 1;
        System.arraycopy(elements, index + 1, elements, index, backIndex);
    }
    
    public boolean delete(T target) {
        remove(target);
        elementsIndex--;
        return true;
    }
    
    private void remove(T value) {
        try {
            int findIndex = indexOf(value);
            remove(findIndex);
        } catch (RuntimeException e) {
            e.printStackTrace();
        }
    }
    
    //! indexOf 를 별도 public 메서드로 생성함
    public int indexOf(T value) {
        OptionalInt findOptionalFirst = IntStream.range(0, elementsIndex)
                                                 .filter(i -> Objects.equals(elements[i], value))
                                                 .findFirst();
        if (findOptionalFirst.isPresent()) {
            return findOptionalFirst.getAsInt();
        }
        return -1;
    }
    
    
    private void checkIndex(int index) {
        if (index < 0 || index > elements.length) {
            throw new IndexOutOfBoundsException("올바른 INDEX가 아닙니다. 0 ~ 인덱스의 사이즈까지로 지정해주세요");
        }
    }
    
    //! 현재 객체와 대상 target 객체와 명확한 구분을 위해 this 구분자 추가, 
    //! target 객체 자체를 불러오도록 설정함
    public boolean addAll(CustomArrayList<T> target) {
        int wishSize = this.elementsIndex + target.size();
        ensureCapacity(wishSize);// 배열의 안전한 삽입을 위해 공간을 확보
        System.arraycopy(target.elements, 0, this.elements, this.elementsIndex, target.size());
        this.elementsIndex += target.size();
        return true;
    }
    
    public T set(int index, T element) {
        checkIndex(index);
        T oldValue = (T) elements[index];
        elements[index] = element;
        return oldValue;
    }
    
    //! contains 에 indexOf 활용으로 해당 값이 있는지 없는지 확인
    public boolean contains(T element) {
        int index = indexOf(element);
        if (index != -1) {
            return true;
        }
        return false;
    }
    
    public void shuffle() {
        boolean isSame = false;
        T[]copiedArr = (T[]) Arrays.copyOf(elements, elementsIndex);
        Random random = new Random();
        for (int i = 0; i < copiedArr.length; i++) {
            int j = random.nextInt(copiedArr.length);
            T temp = (T) copiedArr[j];
            copiedArr[j] = copiedArr[i];
            copiedArr[i] = temp;
        }
        //만약 기존 배열과 전부 동일한 경우 다시 셔플 수행
        if (Arrays.equals(elements, copiedArr)) {
            shuffle();
        }
    }
    
    //! 내부 재귀를 돌지 않도록 설정 -> 계산으로 몇번 increase 해야하는지 산출
    //! zeroDiv 문제때문에 0 인경우 기본값으로 설정해줌
    private void ensureCapacity() {
        ensureCapacity(elementsIndex + 1);
    }
    
    private void ensureCapacity(double wishSize) {
        increaseEmptyElement();
        int divNum = (int) ceil(log(wishSize / elements.length) / log(2));
        checkMaxCapacity(divNum);
        increaseLength(divNum);
    }
    
    private void increaseEmptyElement() {
        if (elements.length == 0) {
            increaseLength(DEFAULT_CAPACITY);
        }
    }
    
    private void checkMaxCapacity(int requiredSize) {
        if (MAX_ARRAY_SIZE < requiredSize) {
            throw new IllegalStateException("배열의 허용공간을 초과했습니다.");
        }
    }
    
    private void increaseLength(int squareNumber) {
        int increasedSize = elements.length == 0 ? DEFAULT_CAPACITY : elements.length << squareNumber;
        elements = Arrays.copyOf(elements, increasedSize);
    }
    
    public int size() {
        return elementsIndex;
    }
    
    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            int index = 0;
            
            @Override
            public boolean hasNext() {
                return index < elementsIndex;
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
                .add("elements=" + Arrays.toString(Arrays.stream(elements).limit(elementsIndex).toArray()))
                .add("elementsSize=" + elementsIndex)
                .toString();
    }
}
