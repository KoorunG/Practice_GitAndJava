package collectionapi;

import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentHashMap.KeySetView;

public class PracConcurrentHashMap {

  public static void main(String[] args) {
    testReduceValues();
    testMappingCount();
    testKeySet();
  }

  private static void testReduceValues(){
    ConcurrentHashMap<String, Long> map = new ConcurrentHashMap<>();
    long parallelismThreshold = 1;
    Optional<Long> maxValue = Optional.ofNullable(map.reduceValues(parallelismThreshold, Long::max));
    System.out.println(maxValue);
  }

  //MappingCount

  private static void testMappingCount(){
    ConcurrentHashMap<String, Long> map = new ConcurrentHashMap<>();
    long count = map.mappingCount();
    System.out.println(count);
  }

  //keySet - 집합뷰로 반환하는 메소드 (맵을 바꾸면 집합도 바뀌고 집합을 바꾸면 맵도 영향을 받음)
  //newwKeySet 메소드를 이용하여 ConcurrentHashMap으로 유지되는 집합을 만들 수도 있다.

  private static void testKeySet(){
    ConcurrentHashMap<String, Long> map = new ConcurrentHashMap<>();
    KeySetView<String, Long> ksv = map.keySet();
    System.out.println(ksv);
    System.out.println(map.newKeySet());
  }

    
}
