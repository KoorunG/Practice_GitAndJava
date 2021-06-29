package concurrent;

public class ForkJoinSumCalculator extends java.util.concurrent.RecursiveTask<Long> {

    private final long[] numbers;
    private final int start;
    private final int end;
    public static final long THRESHOLD = 10_000;

    public ForkJoinSumCalculator (long[] numbers){                          // 초기에 값을 입력 받을 공개 생성자
        this(numbers, 0, numbers.length);
    }

    private ForkJoinSumCalculator (long[] numbers, int start, int end){     // 메인태스크의 서브태스크를 재귀적으로 만들 때 사용하는 비공개 생성자
        this.numbers = numbers;
        this.start = start;
        this.end = end;
    }

    @Override
    protected Long compute() {                      // compute() 구현! ForkJoinFramework.java 에서 언급한 순서에 따라서...
        int length = end - start;
        if (length <= THRESHOLD){
            return computeSequentially();           // length가 THRESHOLD 보다 작아진다면, 순차적으로 계산
        }
        ForkJoinSumCalculator leftTask = new ForkJoinSumCalculator(numbers, start, start + length/2);
        leftTask.fork();                            // leftTask -> 반쪽으로 쪼깬 왼쪽 부분 ( start에서 시작하여, start + length/2에서 끝)

        ForkJoinSumCalculator rightTask = new ForkJoinSumCalculator(numbers, start + length/2 , end);
                                                    // rightTask -> 그 나머지 반쪽 부분 ( start + length/2에서 시작하여 end에서 끝)
        
        Long rightResult = rightTask.compute();     // ☆ 한쪽을 compute()로 돌려 동기 실행함, 또한 이것으로 인해 추가 분할이 일어날 수 있다.
        Long leftResult = leftTask.join();          // 다른 한쪽은 join()으로 처음 서브태스크의 결과를 읽거나 결과가 없다면 대기

        
        return leftResult + rightResult ;           // 두 결과를 다시 더함 
    }

    private long computeSequentially(){             // 순차적 계산 구현
        long sum = 0;
        for (int i = start ; i < end ; i++){
            sum += numbers[i];
        }
        return sum;
    }

    
}
