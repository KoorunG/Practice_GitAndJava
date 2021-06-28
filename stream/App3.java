package stream;

import java.util.Arrays;
import java.util.Comparator;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.*;

public class App3 {
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
      
            Comparator<Dish> dishCaloriesComparator = Comparator.comparingInt(Dish::getCalories);       //1. getCalories 메소드를 이용하여 비교하는 Comparator을 만든 뒤,

            Optional<Dish> mostCalorieDish = menu.stream().collect(maxBy(dishCaloriesComparator));      //   maxBy(Comparator)에 dishCaloriesComparator을 넣음, Collector을 리턴함

            int totalCalories = menu.stream().collect(summingInt(Dish::getCalories));                   //2. summingInt(mapping) 함수를 이용하여 Collector을 리턴함
            int totalCalories2 = menu.stream().collect(reducing(0,Dish::getCalories,Integer::sum));
            int totalCalories3 = menu.stream().map(Dish::getCalories).reduce(0,Integer::sum);
            int totalCalories4 = menu.stream().map(Dish::getCalories).reduce(Integer::sum).get(); 
            
            System.out.println(totalCalories);
            System.out.println(totalCalories2);
            System.out.println(totalCalories3);
            System.out.println(totalCalories4);
            

            IntSummaryStatistics IntSummaryStatics = menu.stream().collect(summarizingInt(Dish::getCalories));
            String shortMenu = menu.stream().map(Dish::getName).collect(joining(", "));


            
            
    }
}
