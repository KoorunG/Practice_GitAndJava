package chapter2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.function.Predicate;

import chapter2.Apple.Color;

public class App {
    public static void main(String[] args) {

        List<Apple> inventory = Arrays.asList(new Apple(Color.GREEN, 150), new Apple(Color.RED, 170),
                new Apple(Color.GREEN, 100), new Apple(Color.RED, 80), new Apple(Color.GREEN, 200));

        filterGreenApples(inventory);
        filterApplesByColor(inventory, Color.RED); // color을 파라미터로 전달
        filterApplesByWeight(inventory, 100); // weight를 파라미터로 전달
        filterApples(inventory, new ApplePredicateByColor());
        filterApples(inventory, new ApplePredicateByWeight());
        filterAplesByGenerics(inventory, (Apple apple) -> apple.getColor().equals(Color.RED));

        makeRedApples(); // 익명클래스!!
        RunnableTest();
        compareAppleTest();
        callableTest();

    }

    public static List<Apple> filterGreenApples(List<Apple> inventory) {
        List<Apple> result = new ArrayList<>();
        for (Apple apple : inventory) {
            if (Color.GREEN.equals(apple.getColor())) {
                result.add(apple);
            }
        }
        return result;
    }

    public static List<Apple> filterApplesByColor(List<Apple> inventory, Color color) {
        List<Apple> result = new ArrayList<>();
        for (Apple apple : inventory) {
            if (apple.getColor().equals(color)) {
                result.add(apple);
            }
        }
        return result;
    }

    public static List<Apple> filterApplesByWeight(List<Apple> inventory, int weight) {
        List<Apple> result = new ArrayList<>();
        for (Apple apple : inventory) {
            if (apple.getWeight() == weight) { // boolean 이라는 것을 잊지말자
                result.add(apple);
            }
        }
        return result;
    }

    public static List<Apple> filterApples(List<Apple> inventory, ApplePredicate p) {
        List<Apple> result = new ArrayList<>();
        for (Apple apple : inventory) {
            if (p.test(apple)) {
                result.add(apple);
            }
        }
        return result;
    }

    public static void makeRedApples() {
        List<Apple> inventory = new ArrayList<>(); // 배열 초기화
        filterApples(inventory, new ApplePredicate() { // 익명클래스!!
            public boolean test(Apple apple) { // 왜 public일까?
                return Color.RED.equals(apple.getColor());
            }
        });
    }

    public static void makeRedApples2() {
        List<Apple> inventory = new ArrayList<>(); // 배열 초기화
        filterApples(inventory, (Apple apple) -> apple.getColor().equals(Color.RED)); // 람다식!!
    }

    public static <T> List<T> filterAplesByGenerics(List<T> list, Predicate<T> p) {
        List<T> result = new ArrayList<>();
        for (T t : list) {
            if (p.test(t)) {
                result.add(t); // 리스트 형식으로 추상화
            }
        }
        return result;
    }

    public static void RunnableTest() {
        Thread t = new Thread(new Runnable() {
            public void run() {
                System.out.println("Hello world");
            }
        });
        System.out.println(t);
    }

    public static void compareAppleTest() {
        List<Apple> inventory = new ArrayList<>();
                inventory.sort(new Comparator<Apple>(){
                    @Override
                    public int compare(Apple o1, Apple o2) {
                        return o1.getWeight() - o2.getWeight();
                    }
                });
    }

    public static void callableTest(){

    ExecutorService executorService = Executors.newCachedThreadPool();
    Future<String> threadName = executorService.submit(new Callable<String>(){
        @Override
        public String call() throws Exception{
            return Thread.currentThread().getName();
        }
    });

    System.out.println(threadName);
    } 
    
}
