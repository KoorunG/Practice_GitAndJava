package generics;

import java.util.ArrayList;
import java.util.List;

public class GenericPrac {

    public static <T> List<T> filter(List<T> list, Predicate<T> p){

        List<T> result = new ArrayList<>();
        for(T t : list) {
            if(p.test(t)){
                result.add(t);
            }
        }

        return result;                          // 제네릭 <T>에 대한 필터 메소드를 만들었다
                                                // 어떤 리스트에도 이 필터를 적용할 수 있음!
    
    }
    
    // List<Apple> redApples = filter(inventory, (Apple apple) -> Color.RED.equals(apple.getColor()));   <T> -> Apple 삽입
    // List<Integer> evenNumbers = filter(numbers, (Integer n) -> n % 2 == 0);                           <T> -> Integer 삽입
    
}
