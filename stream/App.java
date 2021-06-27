package stream;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import static java.util.stream.Collectors.toList;

import java.util.ArrayList;

public class App {
    public static void main(String[] args) {
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

        List<String> threeHighCaloricDishNames = menu.stream()
        .filter(dish -> dish.getCalories() > 300)   // 필터링
        .map(Dish::getName)     //매핑 (Dish -> String)
        .limit(3)       // 개수 제한
        .collect(toList());     // collect : 스트림을 다른 형식으로 변환, 여기서는 List를 반환하는 최종스트림
        System.out.println(threeHighCaloricDishNames);


        
        List<String> names = new ArrayList<>();
        for(Dish dish : menu){
            names.add(dish.getName());
        }                                       // menu에 있는 요소를 names에 추가하는 외부 반복
        
        List<String> namesIterator = new ArrayList<>();
        Iterator<Dish> iterator = menu.iterator();
        while(iterator.hasNext()){
            Dish dish = iterator.next();
            names.add(dish.getName());
        }                                       // 위와 똑같은 코드이지만 내부적으로 숨어있는 Iterator을 꺼냄

        List<String> namesStream = menu.stream().map(Dish::getName).collect(toList());  // 스트림을 이용한 내부반복
        
    }

}
