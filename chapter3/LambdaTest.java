package chapter3;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

import chapter2.Apple;
import chapter2.Apple.Color;

public class LambdaTest {

    public static void main(String[] args) {
        testIsBoolean();
        testComparator();
        testMakeConstuctor();
        testMixingLambda();
    }

    private static void testIsBoolean(){
        System.out.println(isValidName("Koorung"));
    }

    private static boolean isValidName(String string){
        return Character.isUpperCase(string.charAt(0));
    }

    private static void testComparator(){
        List<String> str = Arrays.asList("a","b","A","B");
        // str.sort((s1,s2) -> s1.compareToIgnoreCase(s2));
        str.sort(String::compareToIgnoreCase);
        System.out.println(str);
    
    }

    private static void testMakeConstuctor(){
        Supplier<Apple> c1 = Apple::new;        // 디폴트 생성자와 시그니쳐가 같기에 supplier으로 만든것
        Apple a1 = c1.get();
        System.out.println(a1.getWeight());

        Function<Integer,Apple> f1 = Apple::new;
        Apple a2 = f1.apply(30);
        System.out.println(a2.getWeight());

        BiFunction<Color,Integer,Apple> b1 = Apple::new;
        Apple a3 = b1.apply(Color.RED, 100);
        System.out.println(a3.getColor().toString() + ", "+ a3.getWeight());  // 인스턴스화 하지 않고도 생성자에 접근할수 있으므로 다양한 상황에 응용할 수 있다.
    }

    private static void testMixingLambda(){

        Comparator<Apple> c0 = Comparator.comparing(Apple::getWeight);
        Comparator<Apple> c1 = Comparator.comparing(Apple::getWeight).reversed();
        Comparator<Apple> c2 = Comparator.comparing(Apple::getWeight).thenComparing(Apple::getColor);
        
        Predicate<Apple> redApple = (Apple a) -> a.getColor().equals(Color.RED);
        Predicate<Apple> heavyApple = (Apple a) -> a.getWeight() > 100;
        Predicate<Apple> notRedApple = redApple.negate();
        Predicate<Apple> notRedAndHeavyApple = redApple.negate().and(heavyApple);
    }
}
