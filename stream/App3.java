package stream;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import static java.util.Arrays.asList;

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
            int totalCalories5 = menu.stream().mapToInt(Dish::getCalories).sum();
            
            System.out.println(totalCalories);
            System.out.println(totalCalories2);
            System.out.println(totalCalories3);
            System.out.println(totalCalories4);
            System.out.println(totalCalories5);

            Map<String, List<String>> dishTags = new HashMap<>();
            dishTags.put("pork",asList("greasy","salty"));
            dishTags.put("beef",asList("salty","roasted"));
            dishTags.put("chicken",asList("fried","crisp"));
            dishTags.put("french fries",asList("greasy","fried"));
            dishTags.put("rice",asList("light","natural"));
            dishTags.put("season fruit",asList("fresh","natural"));
            dishTags.put("pizza",asList("tasty","salty"));
            dishTags.put("prawns",asList("tasty","roasted"));
            dishTags.put("salmon",asList("delicious","fresh"));

            
            

            IntSummaryStatistics IntSummaryStatics = menu.stream().collect(summarizingInt(Dish::getCalories));
            String shortMenu = menu.stream().map(Dish::getName).collect(joining(", "));



            /* ===== Groupping ===== */

            Map<Dish.Type, List<Dish>> dishesByType = menu.stream().collect(groupingBy(Dish::getType));
            System.out.println(dishesByType);

            Map<Dish.Type, List<Dish>> caloricDishByType0 = menu.stream().filter(dish -> dish.getCalories() > 500).collect(groupingBy(Dish::getType));
            // collect에 앞서 filter으로 인해 만족하는 값이 없는 FISH 항목이 아예 사라짐

            Map<Dish.Type, List<Dish>> caloricDishByType = menu.stream().collect(groupingBy(Dish::getType,filtering(d -> d.getCalories() > 500, toList())));
            //groupingBy -> public static Collector<T,​?,​Map<K,​D>> groupingBy​(Function classifier, Collector downstream)
            //filtering -> public static Collector<T,​?,​R> filtering​(Predicate predicate, Collector downstream) 을 이용하여 처리 한 모습
            //이 경우엔 값이 없는 FISH 항목도 표시된다.

            System.out.println(caloricDishByType0);     
            System.out.println(caloricDishByType);

            Map<Dish.Type, List<String>> dishNamesByType = menu.stream().collect(groupingBy(Dish::getType,mapping(Dish::getName, toList())));
            System.out.println(dishNamesByType);

            
            Map<Dish.Type,Set<String>> dishNamesByType2 = menu.stream().collect(groupingBy(Dish::getType,
            
                                                              flatMapping(dish -> dishTags.get(dish.getName()).stream(),toSet())));
                                                              
                                                              //dish -> dishTags.get(dish.getName()).stream()
                                                              // => dish.getName()을 이용하여 음식의 이름을 추출한 뒤, 이걸 이용해 dishTags를 얻고, stream으로 만든다.
                                                              // 그 후 flatMapping을 통하여 평면화 시킨 뒤, toSet을 통해 Set<String> 으로 만든다
                                                              // 이것이 value값이 됐으므로 (key = Dish::getType)
                                                              // Type=[dishTags] 와 같이 나오게 된다!
            System.out.println(dishNamesByType2);
    }
}
