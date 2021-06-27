package methodref;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import apple.Apple;
import apple.Apple.Color;
import static java.util.Comparator.*;
import java.util.function.Predicate;

public class AppleComparator implements Comparator<Apple> {

    @Override
    public int compare(Apple a1, Apple a2) {
        return a1.getWeight() - a2.getWeight();
    }

    public static void main(String[] args) {

        List<Apple> inventory = Arrays.asList(
            new Apple(Color.GREEN, 150),
            new Apple(Color.RED, 170),
            new Apple(Color.GREEN, 100),
            new Apple(Color.RED, 80),
            new Apple(Color.GREEN, 200)); 
            
        inventory.sort(new AppleComparator());                        // 1단계 -> 메소드 전달



        inventory.sort(new AppleComparator(){
            public int compare(Apple a1, Apple a2){
                return a1.getWeight() - a2.getWeight();
            }
        });                                                          // 2단계 -> 익명 클래스


        inventory.sort((a1,a2)-> a1.getWeight() - a2.getWeight());   // 3단계 -> 람다식
        inventory.sort(comparing(apple -> apple.getWeight()));       // Comparator.comparing -> 
                                                                     // Comparator 키를 추출하여 Comparator 객체로 만드는 Function 함수를 인수로 받는 정적 메소드!


        inventory.sort(comparing(Apple::getWeight));                 // 4단계 -> 메소드 참조 ( java.util.Comparator.comparing 메소드 이용 )

        inventory.sort(comparing(Apple::getWeight).reversed());      // Comparator.reversed() 메소드 -> 정렬 순서 반대로

        inventory.sort(comparing(Apple::getWeight).thenComparing(Apple::getColor)); // Comparator.thenComparing(Comparator) 메소드 -> Comparator을 이어붙임



        Predicate<Apple> redApple = apple -> apple.getColor().equals(Color.RED);
        Predicate<Apple> notRedApple = redApple.negate();                                       // Predicate의 default 메소드 .negate() : 프레디케이트 반전
        Predicate<Apple> redAndHeavyApple = redApple.and(apple -> apple.getWeight() > 150);     // Predicate의 default 메소드 .and(Predicate) : 프레디케이트 연결     
        Predicate<Apple> redAndHeavyAppleOrGreen = redApple.and(apple -> apple.getWeight() > 150).or(apple -> apple.getColor().equals(Color.GREEN));     // Predicate의 default 메소드 .and(Predicate) : 프레디케이트 연결  



    }
    
}
    
