package linux.sub.hw202230611;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

class CustomArrayListTest {
    CustomArrayList<String> customArrayList;
    
    @BeforeEach
    void setUp() {
        customArrayList = CustomArrayList.from();
        for (int i = 0; i < 2; i++) {
            customArrayList.add("e");
        }
        customArrayList.add("a");
        customArrayList.add("b");
        customArrayList.add("k");
    }
    
    @Test
    void add() {
        System.out.println("customArrayList = " + customArrayList);
        List<String> arr = List.of("e", "e", "a", "b", "k");
        IntStream.range(0, customArrayList.size())
                 .forEach(i -> assertEquals(customArrayList.get(i), arr.get(i)));
        assertEquals(customArrayList.size(), arr.size());
    }
    
    @Test
    void deleteIndex() {
        //인덱스 체크
        assertThrows(IndexOutOfBoundsException.class, () -> {
            customArrayList.delete(-1);
        },"-1 인덱스가 지워지면 안됨");
        customArrayList.delete(0);
        List<String> arr = List.of("e", "a", "b", "k");
        IntStream.range(0, customArrayList.size())
                 .forEach(i -> assertEquals(customArrayList.get(i), arr.get(i),"배열이 동일합니다."));
    }
    
    @Test
    void deleteEle() {
    }
    
    @Test
    void indexOf() {
    }
    
    @Test
    void addAll() {
    }
    
    @Test
    void set() {
    }
    
    @Test
    void contains() {
    }
    
    @Test
    void shuffle() {
    }
    
    @Test
    void size() {
    }
}