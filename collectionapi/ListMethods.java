package collectionapi;

import java.util.Arrays;
import java.util.List;
import java.util.ListIterator;
import java.util.stream.Collectors;

public class ListMethods {

    public static void main(String[] args) {
        workingWithLists();
        
      }
    
      private static void workingWithLists() {
        System.out.println("------ Working with Lists ------");
    
        System.out.println("--> Transforming list items with a Stream");
        
        List<String> referenceCodes = Arrays.asList("a12", "C14", "b13");
        referenceCodes.stream()
            .map(code -> Character.toUpperCase(code.charAt(0)) + code.substring(1)) // 1. .map()을 이용하여 '교체' (대문자로 출력하는...)
            .collect(Collectors.toList())                                           // List로 모음
            .forEach(System.out::println);                                          // forEach로 각각에 대해 출력
        System.out.println("... but the original List remains unchanged: " + referenceCodes); // 단, 이 경우엔 기존의 리스트 referenceCodes 는 그대로 남아있다.
                                                                                              // 새 스트림을 생성한 것일뿐...

        System.out.println("--> Mutating a list with a ListIterator");
        for (ListIterator<String> iterator = referenceCodes.listIterator(); iterator.hasNext(); ) { //초기값 iterator, 조건값 iterator.hasNext(), 증감값 없는 for 구문
          String code = iterator.next();
          iterator.set(Character.toUpperCase(code.charAt(0)) + code.substring(1));  // 2. ListIterator을 이용하여 set() 메소드로 교체
        }
        System.out.println("This time it's been changed: " + referenceCodes);
    
        System.out.println("--> Mutating a list with replaceAll()");                // 3. List의 팩토리메소드인 replaceAll() 을 이용하여 교체 (이게 제일 쉬움!)
        System.out.println("Back to the original: " + referenceCodes);
        referenceCodes.replaceAll(code -> Character.toUpperCase(code.charAt(0)) + code.substring(1)); //List<String> 자료형의 referenceCodes에 .replaceAll(UnaryOperator<E>))을 쓴 것
        System.out.println("Changed by replaceAll(): " + referenceCodes);
      }
    

        
}
