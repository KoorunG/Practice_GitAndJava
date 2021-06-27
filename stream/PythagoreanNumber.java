package stream;

import java.util.Scanner;
import java.util.stream.IntStream;
import java.util.stream.Stream;


// a^2 + b^2 = c^2 인 숫자 [a,b,c] -> 목표 : Stream<int[]>
public class PythagoreanNumber {

    static Stream<int[]> pythagoreanTriple = IntStream.rangeClosed(1,100)/* a에 사용 할 1~100까지의 숫자를 만듦 */.boxed()/* IntStream -> Stream<Integer> */ 
    .flatMap(a-> IntStream.rangeClosed(a, 100)/* 주어진 a를 이용하여 세 수의 스트림을 만듦 즉, 스트림의 스트림이 만들어지는 것이다 */
                                              /* 따라서 flatMap을 이용하여 각각의 스트림을 하나의 평준화된 스트림으로 만들어야 한다 */
                                              .filter(b -> Math.sqrt(a*a+b*b)%1 == 0)
                                              .mapToObj(b -> new int[]{a,b,(int)Math.sqrt(a*a+b*b)}))
                                              .filter(t->t[2]%1==0);    // 세 수의 세번째 수는 반드시 정수이어야 한다는 조건을 추가하여 최적화 하였다

    public static void main(String[] args){        
    System.out.print("출력할 피타고리안 수 배열의 갯수를 입력하시오 : ");
    Scanner sc = new Scanner(System.in);
    int n = sc.nextInt();
    sc.close();
    
    pythagoreanTriple.limit(n).forEach(t -> System.out.println(t[0] + ", "+t[1] + ", "+ t[2]));                                         
    }
}
