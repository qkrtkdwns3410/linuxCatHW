package linux.sub.hw20230624;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * packageName    : linux.sub.hw20230624
 * fileName       : Practice
 * author         : ipeac
 * date           : 2023-07-01
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023-07-01        ipeac       최초 생성
 */
public class Practice {
    public static void main(String[] args) {
        List<Person> persons = new ArrayList<>(List.of(new Person("박상준", 30), new Person("김상준", 30), new Person("이상준", 18), new Person("cali", 29), new Person("Bali", 1)));
        Collections.sort(persons, new AgeDescComparator());
        System.out.println("persons = " + persons);
    }
    
    public static class AgeDescComparator implements Comparator<Person> {
        @Override
        public int compare(Person o1, Person o2) {
            if (o1 == null && o2 == null) {
                return 0;
            } else if (o1 == null) {
                return 1;
            } else if (o2 == null) {
                return -1;
            }
            return (o1.age - o2.age) * -1;
        }
    }
    
    public static class Person {
        private final static IllegalArgumentException NULL_ARGUMENT_EXCEPTION = new IllegalArgumentException("NULL 값은 허용되지 않습니다.");
        private String name;
        private int age;
        
        public Person(String name, int age) {
            if (age < 0) {
                String ageErrorMessage = MessageFormat.format("age : {0} 은 0보다 작을 수 없습니다.", age);
                throw new IllegalArgumentException(ageErrorMessage);
            }
            this.name = name;
            this.age = age;
        }
        
        @Override
        public String toString() {
            return "Person{" +
                           "name='" + name + '\'' +
                           ", age=" + age +
                           '}';
        }
    }
}
