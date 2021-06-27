package methodref;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import apple.Apple;
import apple.Apple.Color;
import static java.util.Comparator.comparing;

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


    }
    
}
    
