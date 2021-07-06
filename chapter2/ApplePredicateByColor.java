package chapter2;

import chapter2.Apple.Color;

public class ApplePredicateByColor implements ApplePredicate {
    static int count = 0;
    @Override
    public boolean test(Apple apple) {
        
        if (apple.getColor() == Color.RED) {
            count++;
            System.out.println("빨간색 사과 " + count +"개 추가!!");
            return true;
        } else {
            return false;
        }
    }
}
