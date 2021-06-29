package concurrent;

import java.util.stream.Stream;

public class Sequential {

    public static long sequentialSum(long n){
        return Stream.iterate(1L, i -> i +1).limit(n).reduce(0L,Long::sum);     
                   /* 자연수 스트림 생성 */             /* 리듀싱 (0으로 초기화, 모든 Long을 더함) */
    }

    public static long parallelSum(long n){
        return Stream.iterate(1L, i -> i +1).limit(n).parallel().reduce(0L,Long::sum);
                                                    /* parallel() -> 병렬로 변환 */
    }                                               /* sequential() -> 직렬로 변환 */       //둘 다 전체 파이프라인에 영향을 미치는 메소드!

    public static void main(String[] args) {
        
       System.out.println(sequentialSum(10));       // 55
       System.out.println(parallelSum(10));         // 55
    }
    
    
}
