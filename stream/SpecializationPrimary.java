package stream;

import java.util.Arrays;
import java.util.List;
import java.util.OptionalInt;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class SpecializationPrimary {

    public static void main(String[] args){
        List<Dish> menu = Arrays.asList(
            new Dish("pork", false, 800, Dish.Type.MEAT),
            new Dish("beef", false, 700, Dish.Type.MEAT),
            new Dish("chicken", false, 400, Dish.Type.MEAT),
            new Dish("french fries", true, 530, Dish.Type.OTHER),
            new Dish("rice", true, 350, Dish.Type.OTHER),
            new Dish("season fruit", true, 120, Dish.Type.OTHER),
            new Dish("pizza", true, 550, Dish.Type.OTHER),
            new Dish("prawns", false, 300, Dish.Type.FISH),
            new Dish("salmon", false, 450, Dish.Type.FISH)
        );

        List<Dish> specialMenu = Arrays.asList(
            new Dish("season fruit", true, 120, Dish.Type.OTHER),
            new Dish("prawns", false, 300, Dish.Type.FISH),
            new Dish("rice", true, 350, Dish.Type.OTHER),
            new Dish("chicken", false, 400, Dish.Type.MEAT),
            new Dish("french fries", true, 530, Dish.Type.OTHER),
            new Dish("pizza", true, 550, Dish.Type.OTHER)
        );

    IntStream intStream = menu.stream().mapToInt(Dish::getCalories);   // mapToInt : Stream<Integer> -> IntStream
    Stream<Integer> stream = intStream.boxed();                        // boxed : IntStream -> Stream<Integer>

    OptionalInt maxCalories = menu.stream().mapToInt(Dish::getCalories).max();  //IntStream의 메소드 .max()가 OptionalInt를 반환함!

    IntStream evenNumbers = IntStream.rangeClosed(1, 100);  // [1,100]
    


    }


    
}
