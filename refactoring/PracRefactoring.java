package refactoring;

import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.*;

import java.util.ArrayList;
import java.util.Arrays;

public class PracRefactoring {

    public static void main(String[] args) {
        testMethodRef();
        testMethodRef2();
        testSummingInt();
        testParrelStream();
    }

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

    public enum CaloricLevel { DIET, NORMAL, FAT}

    private static void testMethodRef(){
        Map<CaloricLevel, List<Dish>> dishesByCaloricLevel = menu.stream().collect(groupingBy(dish -> {
            if (dish.getCalories() <= 400)
            return CaloricLevel.DIET;
            else if (dish.getCalories() <= 700)
            return CaloricLevel.NORMAL;
            else 
            return CaloricLevel.FAT;
        }));    
        System.out.println(dishesByCaloricLevel);
    }

    // 여기서 Dish클래스에 getCaloricLevel 메소드를 따로 뺄수 있다.

    private static void testMethodRef2(){
        Map<CaloricLevel, List<Dish>> dishesByCaloricLevel = menu.stream().collect(groupingBy(Dish::getCaloricLevel));    
        System.out.println(dishesByCaloricLevel); 
    // 대신 여기에서처럼 enum클래스를 이곳에 만들거나 해서 꼬이지 말고 명확하게 정의하는것이 필요함...

    }
    private static void testSummingInt(){
        int totalCalories = menu.stream().collect(summingInt(Dish::getCalories));
        System.out.println(totalCalories);
    }
    
    private static void testParrelStream() {
        List<String> dishNames = new ArrayList<>();
        for (Dish dish : menu){
            if(dish.getCalories() > 300){
                dishNames.add(dish.getName());
            }
        }       //일반적으로 메뉴에 300칼로리가 넘는 음식을 추가하는 방법
        System.out.println(" === Common way === ");
        System.out.println(dishNames);
        System.out.println(" === Use Stream (parallel)=== ");    //혹은 .parallel() 메소드 사용 
        List<String> dishNamesByStream = menu.parallelStream().filter(dish -> dish.getCalories() > 300).map(Dish::getName).collect(toList());
        System.out.println(dishNamesByStream);
        System.out.println(" === Use Stream (sequential)=== ");  //혹은 .sequential() 메소드 사용
        List<String> dishNamesBySequential = menu.stream().filter(dish -> dish.getCalories() > 300).map(Dish::getName).collect(toList());
        System.out.println(dishNamesBySequential);

    }
    
}
