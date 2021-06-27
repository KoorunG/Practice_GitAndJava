package stream;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;
import static java.util.Comparator.comparing;

public class App2 {

    public static void main(String[] args) {
        
    
    Trader raoul = new Trader("Raoul","Cambridge");
    Trader mario = new Trader("Mario","Milan");
    Trader alan = new Trader("Alan","Cambridge");
    Trader brian = new Trader("Brian","Cambridge");

    List<Transaction> transactions = Arrays.asList(
        new Transaction(brian, 2011, 300),
        new Transaction(raoul, 2012, 1000),
        new Transaction(raoul, 2011, 400),
        new Transaction(mario, 2012, 710),
        new Transaction(mario, 2012, 700),
        new Transaction(alan, 2012, 950)
        );
    
    
    System.out.println("1. 2011년에 일어난 모든 트랜잭션을 찾아 값을 오름차순으로 정리하시오");
    List<Transaction> q1 = transactions.stream().filter(t -> t.getYear() == 2011).sorted(comparing(Transaction::getValue)).collect(toList());
    System.out.println(q1);

    System.out.println();
    System.out.println("2. 거래자가 근무하는 모든 도시를 중복 없이 나열하시오");
    List<String> q2 = transactions.stream().map(Transaction::getTrader).map(Trader::getCity).distinct().collect(toList());
    System.out.println(q2);

    System.out.println();
    System.out.println("3. 케임브리지에서 근무하는 모든 거래자를 찾아서 이름순으로 정렬하시오");
    List<Trader> q3 = transactions.stream().map(Transaction::getTrader).filter(t->t.getCity()=="Cambridge").sorted(comparing(Trader::getName)).distinct().collect(toList());
    System.out.println(q3);

    System.out.println();
    System.out.println("4. 모든 거래자의 이름을 알파벳 순으로 정렬해서 반환하시오");
    List<String> q4 = transactions.stream().map(Transaction::getTrader).map(Trader::getName).sorted().distinct().collect(toList());
    System.out.println(q4);

    System.out.println();
    System.out.println("5. 밀라노에 거래자가 있는가?");
    boolean q5 = transactions.stream().map(Transaction::getTrader).anyMatch(p->p.getCity().equals("Milan"));
    System.out.println(q5);
    

    System.out.println();
    System.out.println("6. 케임브리지에 거주하는 거래자의 모든 트랜잭션값을 출력하시오");
    transactions.stream().filter(s -> s.getTrader().getCity().equals("Cambridge")).map(Transaction::getValue).forEach(System.out::println);
    // forEach(Consumer) -> void값을 반환!

    System.out.println();
    System.out.println("7. 전체 트랜잭션값중 최댓값은 얼마인가?");
    Optional<Integer> q7 = transactions.stream().map(Transaction::getValue).reduce(Integer::max);
    System.out.println(q7);

    System.out.println();
    System.out.println("8. 전체 트랜잭션값중 최솟값은 얼마인가?");
    Optional<Integer> q8 = transactions.stream().map(Transaction::getValue).reduce(Integer::min);
    System.out.println(q8);
    
    }
}
