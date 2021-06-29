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
            .map(code -> Character.toUpperCase(code.charAt(0)) + code.substring(1)) // 1. .map()을 이용하여 '교체'
            .collect(Collectors.toList())                                           // List로 모음
            .forEach(System.out::println);                                          // forEach로 각각에 대해 출력
        System.out.println("... but the original List remains unchanged: " + referenceCodes);
    
        System.out.println("--> Mutating a list with a ListIterator");
        for (ListIterator<String> iterator = referenceCodes.listIterator(); iterator.hasNext(); ) {
          String code = iterator.next();
          iterator.set(Character.toUpperCase(code.charAt(0)) + code.substring(1));  // 2. ListIterator을 이용하여 set() 메소드로 교체
        }
        System.out.println("This time it's been changed: " + referenceCodes);
    
        System.out.println("--> Mutating a list with replaceAll()");                // 3. List의 팩토리메소드인 replaceAll() 을 이용하여 교체
        referenceCodes = Arrays.asList("a12", "C14", "b13");
        System.out.println("Back to the original: " + referenceCodes);
        referenceCodes.replaceAll(code -> Character.toUpperCase(code.charAt(0)) + code.substring(1));
        System.out.println("Changed by replaceAll(): " + referenceCodes);
      }
    

        
}
