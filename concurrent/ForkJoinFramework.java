package concurrent;

public class ForkJoinFramework {
    
    //쓰레드풀을 이용하려면 RecursiveTask를 활용해야 한다 -> compute() 추상메소드를 구현해야함 : protected abstract R compute()

    /** compute() 메소드의 구현 방식!!
     * 
     *  if(더이상 쪼갤수 없을 때){
     *              순차적 계산   }
     *  else {
     *      Task를 두 subTask로 분할
     *      다시 subTask로 분할되도록 이 메소드를 재귀적으로 호출
     *      모든 subTask의 연산이 완료될때까지 기다림
     *      각 subTask의 결과를 합침
     *     } */
}
