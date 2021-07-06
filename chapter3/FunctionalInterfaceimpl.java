package chapter3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.IntFunction;
import java.util.function.IntPredicate;
import java.util.function.Predicate;

public class FunctionalInterfaceimpl {

    public static void main(String[] args) {
        testPredicate();
        testComsumer();
        testFunction();
        testIntPredicate();
        testIntFunction();
    }

    public static <T> List<T> filter(List<T> list, Predicate<T> p) {

        List<T> result = new ArrayList<>();
        for (T t : list) {
            if (p.test(t)) {
                result.add(t);
            }
        }
        return result;
    }

    public static <T> void forEach(List<T> list, Consumer<T> c) {
        for (T t : list) {
            c.accept(t);
        }
    }

    public static <T,R> List<R> map(List<T> list, Function<T,R> f){
        List<R> result = new ArrayList<>();
        for(T t : list) {
            result.add(f.apply(t));                     // list의 요소 t를 mapping하여 f에 적용시킨다고 생각하면 편할듯?
        }
        return result;
    }

    private static void testPredicate() {

        List<String> listOfString = new ArrayList<>();
        listOfString.add("A");
        listOfString.add("B");
        listOfString.add("C");
        listOfString.add("");
        Predicate<String> nonEmptySPredicate = (String s) -> !s.isEmpty(); // ! : boolean 전환
        List<String> nonEmpty = filter(listOfString, nonEmptySPredicate);
        System.out.println(nonEmpty);

    }

    private static void testComsumer() {
        List<Integer> listOfConsumer = Arrays.asList(1,2,3,4,5,6,7);
        forEach(listOfConsumer, (Integer i) -> System.out.println(i));
    }

    private static void testFunction(){
        List<Integer> i = map(Arrays.asList("Modern","Java","in","action"), (String s) -> s.length());      /* Arrays.asList()를 첫번째 인수로 받고 
                                                                                                                람다식을 두번째 인수로 받았다 (Function) 
                                                                                                                이를 통해 s.length()로 매핑되어 int값을 반환한다*/
        System.out.println(i);
    }

    private static void testIntPredicate(){
        IntPredicate isEvenNumber = (int i) -> i % 2 == 0;
        System.out.println(isEvenNumber.test(1000));            // 기본형 특화 -> 입력값의 타입을 정하고 들어가기 때문에 유용하다

       
    }

    private static void testIntFunction(){
        IntFunction<Integer> func1 = (int x) -> x*x + 2*x + 1 ;
        System.out.println(func1.apply(100));
    }

}
