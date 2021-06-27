package stream;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class Mapping {

    static List<String> words = Arrays.asList("Modern","Java","In","Action");

    static List<Integer> wordLengths = words.stream().map(String::length).collect(toList());

    static List<String[]> wordsChar = words.stream().map(word -> word.split("")).distinct().collect(toList());

    // .split(String) 메소드가 String[]을 반환하기 때문에 원하는 결과를 얻을 수 없다!



    String[] arrayOfWords = {"Modern","Java","In","Action"};
    Stream<String> streamOfWords = Arrays.stream(arrayOfWords);         //Arrays.stream(String[]) -> 문자열을 받아 스트림을 반환하는 메소드

    static List<String> hi = words.stream().map(word -> word.split("")).flatMap(Arrays::stream).distinct().collect(toList());

    // flatMap(Stream<String[]>) : 각 배열을 스트림이 아니라 스트림의 콘텐츠로 매핑한다.
    // 즉, 평면화된 스트림을 반환 ( 즉 Stream<String> 반환 !)



    //숫자 리스트가 주어졌을 때, 각 숫자의 제곱근으로 이루어진 리스트를 반환하시오 ex) [1,2,3,4,5] -> [1,4,9,16,25]

    static List<Integer> numbers = Arrays.asList(1,2,3,4,5);
    static List<Integer> squareNumbers = numbers.stream().map(n -> n * n).collect(toList());

    //두개의 숫자 리스트가 있을 때, 모든 숫자 쌍의 리스트를 반환하시오 ex) [1,2,3], [3,4] -> [(1,3),(1,4),(2,3),(2,4),(3,3),(3,4)]

    static List<Integer> numbers1 = Arrays.asList(1,2,3);
    static List<Integer> numbers2 = Arrays.asList(3,4);
    static List<int[]> pairs = numbers1.stream()
    .flatMap(i->                                            // 2. Stream<int[int[]]>로 매핑된것을 평면화 -> ( Stream<int[]> )
                numbers2.stream()
                                .map(j->new int[]{i,j}))    // 1. int[]의 배열을 요소로 가지는 배열스트림-> ( Stream<int[int[]]> )
    .collect(toList()); // 이상하게 반환되는데...             // 3. Stream<int[]> -> List<int[]> 로 변환
                                                            // 4. int[]의 toString()은 재정의되지 않았으므로, 해시코드가 출력되는것

                                                        



    public static void main(String[] args) {
        System.out.println(wordsChar);  // 실패!

        System.out.println(hi);         // 성공!

        System.out.println(" ==== Square ==== ");

        System.out.println(numbers1);   // [1,2,3]
        System.out.println(numbers2);   // [3,4]

        System.out.println(pairs);      // 이상한 문자...
    }
    
    
}
