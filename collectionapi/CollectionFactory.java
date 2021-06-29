package collectionapi;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CollectionFactory {
    
    public static void main(String[] args) {
        List<String> friends = new ArrayList<>();
        friends.add("Raphael");
        friends.add("Olivia");
        friends.add("Thibaut");

        List<String> friends2 = Arrays.asList("Raphael","Olivia","Thibaut");    //요소를 갱신할 수는 있으나, 새 요소를 추가하거나 삭제하는 것은 불가능
        // friends2.add("Koorung"); -> UnsupportedOperationException 발생

        List<String> friends4 = new ArrayList<>(Arrays.asList("Raphael","Olivia","Thibaut"));
        Set<String> friends3 = new HashSet<>(Arrays.asList("Raphael","Olivia","Thibaut"));      //1. 인수로 Array.asList를 이용해 만든 리스트를 집어넣음

        Set<String> friends5 = Stream.of("Raphael","Olivia","Thibaut").collect(Collectors.toSet());

        
    }
}
