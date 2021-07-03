package refactoring;

import java.util.Arrays;
import java.util.List;

public class Peeking {
    
    public static void main(String[] args) {

        List<Integer> numbers = Arrays.asList(1,2,3,4,5,6,7);

        numbers.stream().map(x -> x + 10).filter(m -> m % 2 == 0).limit(3).forEach(System.out::println);    // forEach에서 모든 스트림이 소비됨


        // Peek을 이용하면 매 파이프라인마다 요소를 추출할 수 있다 

        numbers.stream().peek(p -> System.out.println("from stream : " + p))
               .map(x -> x + 10).peek(p -> System.out.println("from map : " + p))
               .filter(m -> m % 2 == 0).peek(p -> System.out.println("from filter : " + p))
               .limit(3).peek(p -> System.out.println("from limit : " + p))
               .forEach(System.out::println);

    }
    
}
