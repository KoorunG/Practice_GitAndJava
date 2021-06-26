package apple;

import java.util.ArrayList;
import java.util.List;

import apple.Apple.Color;

public class App {

    public static List<Apple> filterApplesByColor(List<Apple> inventory, Color color){

        List<Apple> result = new ArrayList<Apple>();
        for(Apple apple : inventory) {
            if (apple.getColor().equals(color)){
                result.add(apple);                          // color에 대해서만 필터링
            }
        }
        return result;
    }

    public static List<Apple> filterApples(List<Apple> inventory, ApplePredicate p){

        List<Apple> result = new ArrayList<Apple>();
        for(Apple apple : inventory) {
            if(p.test(apple)) {
                result.add(apple);                          //ApplePredicate 인터페이스를 만들어 각 조건에 맞게 구현 (동작 파라미터화)
            }
        }
        return result;
    }
    
    public static void main(String[] args) {
        // List<Apple> greenApples = filterApplesByColor(inventory, Color.GREEN);
        // List<Apple> redApples = filterApplesByColor(inventory, Color.RED);
    }
}

