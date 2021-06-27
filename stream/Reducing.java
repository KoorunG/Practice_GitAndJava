package stream;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class Reducing {
    
    
    public static void main(String[] args) {

        List<Integer> numbers = Arrays.asList(1,7,2,5,2,10);
        Optional<Integer> product = numbers.stream().reduce((a,b) -> a * b);   // 초기값이 1인 상태에서, 스트림의 값이 하나로 줄어들 때 까지 하나씩 소모하면서 람다를 반복
        Optional<Integer> max = numbers.stream().reduce(Integer::max);

        System.out.println(product);
        System.out.println(max);
    }
    
}
