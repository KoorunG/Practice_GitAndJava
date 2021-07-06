package chapter2;

public class ApplePredicateByWeight implements ApplePredicate {
    static int count = 0;
    @Override
    public boolean test(Apple apple) {
        
        if (apple.getWeight() > 100) {
            count++;
            System.out.println("100g 이상인 사과 " + count +"개 추가!!");
            return true;
        } else {
            return false;
        }
    }
}
