package collectionapi;

import static java.util.Map.entry;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;
import java.util.Objects;
import java.util.Optional;

public class MapMethods {

    public static void main(String[] args) {

        workingWithMaps();
        computingOnMaps();
        removingFromMaps();
        replacingInMaps();
        mergingMaps();
        // testInitializing();
        testInitializing2();
        testRemoveIf();
        testRemoveIf2();
        
    }
    
    private static void workingWithMaps() {
        System.out.println("------ Working with Maps ------");
    
        System.out.println("--> Iterating a map with a for loop");
        Map<String, Integer> ageOfFriends = Map.of("Raphael", 30, "Olivia", 25, "Thibaut", 26);
        for (Map.Entry<String, Integer> entry: ageOfFriends.entrySet()) {
          String friend = entry.getKey();
          Integer age = entry.getValue();
          System.out.println(friend + " is " + age + " years old");
        }
    
        System.out.println("--> Iterating a map with forEach()");
        ageOfFriends.forEach((friend, age) -> System.out.println(friend + " is " + age + " years old"));
    
        System.out.println("--> Iterating a map sorted by keys through a Stream");

        Map<String, String> favouriteMovies = Map.ofEntries(        //Map.ofEntries() 메소드로 Map<String,String> 자료형의 엔트리를 만듦
            entry("Raphael", "Star Wars"),
            entry("Cristina", "Matrix"),
            entry("Olivia", "James Bond"));

        favouriteMovies.entrySet().stream()                         // 여기에 entrySet() -> 맵에 들어있는 전체 데이터 출력(key와 value값이 모두 필요한 경우)
            .sorted(Entry.comparingByKey())                         // 당연히 Set을 리턴함
                                                                    // Entry.comparingByKey -> Key를 기준으로 비교를하는 Comparator
            .forEachOrdered(System.out::println);                   // Stream.forEachOrdered(Consumer) ->
                                                                    // 스트림의 각 요소에 대해 정의된 조우 순서가 있는 경우 스트림의 조우 순서에 따라 작업을 수행합니다.
                                                                    // 즉 여기서는 sorted(Entry.comparingByKey) 에 의해 정해진 순서가 있기 때문에 쓰는 것임

    
        System.out.println("--> Using getOrDefault()");
        System.out.println(favouriteMovies.getOrDefault("Olivia", "Default"));      
        System.out.println(favouriteMovies.getOrDefault("Thibaut", "Default"));     // getOrDefault(첫번째 인수 : Key, 두번째 인수 : 디폴트값)
      }
    





      private static void computingOnMaps() {
        Map<String, List<String>> friendsToMovies = new HashMap<>();
    
        System.out.println("--> Adding a friend and movie in a verbose way");
        String friend = "Raphael";
        List<String> movies = friendsToMovies.get(friend);
        if (movies == null) {
           movies = new ArrayList<>();
           friendsToMovies.put(friend, movies);
        }
        movies.add("Star Wars");
        System.out.println(friendsToMovies);             //key값이 존재하지 않는 경우 -> if문을 이용하여 null이 있을 경우엔 리스트를 새로 만들어 값을 넣었었다.
    
        System.out.println("--> Adding a friend and movie using computeIfAbsent()");
        friendsToMovies.clear();
        friendsToMovies.computeIfAbsent("Raphael", name -> new ArrayList<>())           
            .add("Star Wars");
        System.out.println(friendsToMovies);             //computeIfAbsent : 제공된 키에 대한 값이 없으면(혹은 null값이면) 새 값을 계산하고 맵에 추가
      }                                                  //computeIfPresent : 제공된 키가 존재하면 새 값을 계산하고 맵에 추가
                                                         //compute : 제공된 키로 새 값을 계산하고 맵에 저장

      
                                                         


      private static void removingFromMaps() {
        // 바꿀 수 있는 맵 필요!
        Map<String, String> favouriteMovies = new HashMap<>();
        favouriteMovies.put("Raphael", "Jack Reacher 2");
        favouriteMovies.put("Cristina", "Matrix");
        favouriteMovies.put("Olivia", "James Bond");
        String key = "Raphael";
        String value = "Jack Reacher 2";
    
        System.out.println("--> Removing an unwanted entry the cumbersome way");
        boolean result = remove(favouriteMovies, key, value);
        System.out.printf("%s [%b]%n", favouriteMovies, result);
    
        // 두 번째 테스트를 하기 전에 삭제된 항목을 다시 돌려놓음
        favouriteMovies.put("Raphael", "Jack Reacher 2");
    
        System.out.println("--> Removing an unwanted the easy way");
        favouriteMovies.remove(key, value);
        System.out.printf("%s [%b]%n", favouriteMovies, result);
      }
    
      private static <K, V> boolean remove(Map<K, V> favouriteMovies, K key, V value) {
        if (favouriteMovies.containsKey(key) && Objects.equals(favouriteMovies.get(key), value)) {
          favouriteMovies.remove(key);
          return true;
        }
        return false;
      }
    
      private static void replacingInMaps() {                   //맵에서의 replace -> 한개의 맵에만 적용 가능
        Map<String, String> favouriteMovies = new HashMap<>();
        favouriteMovies.put("Raphael", "Star Wars");
        favouriteMovies.put("Olivia", "james bond");
    
        System.out.println("--> Replacing values in a map with replaceAll()");
        favouriteMovies.replaceAll((friend, movie) -> movie.toUpperCase());
        System.out.println(favouriteMovies);
      }
    
      private static void mergingMaps() {                         //맵에서의 merge -> 다수의 맵에 적용 가능 
        Map<String, String> family = Map.ofEntries(
            entry("Teo", "Star Wars"),
            entry("Cristina", "James Bond"));
        Map<String, String> friends = Map.ofEntries(entry("Raphael", "Star Wars"));
    
        System.out.println("--> Merging the old way");
        Map<String, String> everyone = new HashMap<>(family);
        everyone.putAll(friends);
        System.out.println(everyone);                             //기존 방법 -> 중복된 값이 있다면 충돌이 발생할 것이다
    
        Map<String, String> friends2 = Map.ofEntries(
            entry("Raphael", "Star Wars"),
            entry("Cristina", "Matrix"));
    
        System.out.println("--> Merging maps using merge()");
        Map<String, String> everyone2 = new HashMap<>(family);
        friends2.forEach((k, v) -> everyone2.merge(k, v, (movie1, movie2) -> movie1 + " & " + movie2));
        System.out.println(everyone2);                            // merge() -> 중복된 값이 있어도 merge하여 잘 대처 가능하다 
      }

      private static void testInitializing(){
        Map<String,Long> moviesToCount = new HashMap<>();     // moivesToCount라는 HashMap을 만든다. <영화이름, 카운트>
        String movieName = "James Bond";                       
        long count = moviesToCount.get(movieName);            // count는 movieName을 키로, moviesToCount 해쉬맵에서 get()을 이용해 꺼낸다.
        if (count == 0l){                                      // count == 0 이면
          moviesToCount.put(movieName,1L);                    // count에 1L을 밀어 넣고
        }
        else{                                                 
          moviesToCount.put(movieName,count+1);               // 그게 아니면 기존 count에 +1을 하면 된다
                                                              // 근데 왜 NullPointerException이 나오지...?
        }
      }
      
      private static void testInitializing2(){              
        Map<String,Long> moviesToCount2 = new HashMap<>();
        String movieName = "James Bond";                       
        moviesToCount2.merge(movieName,1L,(key,count)->count+1L); 
      }

      private static void testRemoveIf(){
        Map<String, Integer> movies = new HashMap<>();
        movies.put("JameBond", 20);
        movies.put("Matrix", 15);
        movies.put("Harry Potter", 5);
        Iterator<Map.Entry<String, Integer>> iterator = movies.entrySet().iterator();
        while(iterator.hasNext()){
          Map.Entry<String, Integer> entry = iterator.next();   //iterator으로 돌리면서 entry를 추가한 뒤, 조건에 맞는 값 제거(remove)
          if(entry.getValue() < 10){
            iterator.remove();
          }
        }
        System.out.println(movies);
      }

      private static void testRemoveIf2(){
        Map<String, Integer> movies = new HashMap<>();
        movies.put("JameBond", 20);
        movies.put("Matrix", 15);
        movies.put("Harry Potter", 5);
        movies.entrySet().removeIf(entry -> entry.getValue() < 10);
        System.out.println(movies);
      }

      


      // private static void computeDigest() throws NoSuchAlgorithmException{             
      //   Map<String, byte[]> dataToHash = new HashMap<>();
      //   MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");

      //   lines.forEach(line-> dataToHash.computeIfAbsent(line, this::calculateDigest));   //line을 키값으로, 키가 존재하지 않는다면, this::calculateDigest 실행

      //   private byte[] calculateDigest(String key) {
      //     return messageDigest.digest(key.getBytes(StandardCharsets.UTF_8));
      //   }  

      // }

      
      

    /**
     * ageOfFriends.forEach((friend, age) -> System.out.println(friend + "is" + age + "years old"));     // forEach(BiConsumer<Key,Value>) 를 이용하면 쉬움
     * 
     * 
     *  */
    
    
}


