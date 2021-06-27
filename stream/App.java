package stream;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import static java.util.stream.Collectors.toList;

import java.util.ArrayList;

public class App {

        public static List<Dish> menu = Arrays.asList(
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

        public static List<Dish> specialMenu = Arrays.asList(
            new Dish("season fruit", true, 120, Dish.Type.OTHER),
            new Dish("prawns", false, 300, Dish.Type.FISH),
            new Dish("rice", true, 350, Dish.Type.OTHER),
            new Dish("chicken", false, 400, Dish.Type.MEAT),
            new Dish("french fries", true, 530, Dish.Type.OTHER),
            new Dish("pizza", true, 550, Dish.Type.OTHER)
        );
    public static void main(String[] args) {
        
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

         // == 스트림 - 필터링 ==
        
         List<Dish> vegetarianMenu = menu.stream()
         .filter(Dish::isVegetarian)         // 필터
         .distinct()                         // 중복 제거
         .collect(toList());
         System.out.println(vegetarianMenu);


         // == 스트림 - 슬라이싱 ==

         List<Dish> sliceMenuDefault = specialMenu.stream().filter(dish -> dish.getCalories() <= 320).collect(toList());      //일반적인 필터링

         List<Dish> sliceMenuByTakeWhile = specialMenu.stream().takeWhile(dish -> dish.getCalories() <= 320).collect(toList());   
         //takeWhile -> 칼로리순으로 정렬된 메뉴의 스트림을 만들때는 takeWhile을 통해 슬라이스를 해서 '반복작업을 아예 하지 않을 수' 있다.
         List<Dish> sliceMenuByDropWhile = specialMenu.stream().dropWhile(dish -> dish.getCalories() <= 320).collect(toList());
         //dropWhile -> takeWhile의 정반대 (320칼로리보다 큰 메뉴 출력)

         List<Dish> sliceMenuBySkipAndLimit = menu.stream().skip(2).limit(2).collect(toList());
         // skip(n) -> n개를 건너 뛰고 출력 , limit(n) -> 처음부터 n개만 출력

         

         menu.stream()
         .filter(Dish::isVegetarian)
         .findAny() /*여기까지 스트림 Optional<Dish> 반환 */.ifPresent(dish -> System.out.println(dish.getName())); //값이 있으면 출력, 없으면 아무일도 일어나지 않음


         int count = menu.stream().map(d->1).reduce(0, (a,b)->a+b);
         System.out.println(count);         // Map - Reduce 패턴 : 쉽게 병렬화 가능, 구글이 웹 검색에 적용하면서 유행함
    }
  

}
