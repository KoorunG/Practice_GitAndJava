
package pracdsl;

import static pracdsl.MethodChainingOrderBuilder.forCustomer;
import static pracdsl.NestFunctionOrderBuilder.*;
import static pracdsl.LambdaOrderBuilder.*;
import static pracdsl.MixedBuilder.*;

public class Main {

  public static void main(String[] args) {
    plain();
    methodChaining();
    nestFunctioning();
    lambdaOrder();
    MixingDsl();
  }

  private static void plain() {             // 1. 일반적인 경우
    Order order = new Order();              // Order 객체 생성
    order.setCustomer("BigBank");           // setCustomer -> "BigBank"

    Trade trade1 = new Trade();             // 거래 1을 생성
    trade1.setType(Trade.Type.BUY);         // 거래의 타입을 매수로 설정 (Trade Type.BUY)

    Stock stock1 = new Stock();             // 주식 1을 생성 (Trade의 인자로 Stock stock 이 들어가므로!)
    stock1.setSymbol("IBM");                // 주식 1의 이름과 거래소 세팅
    stock1.setMarket("NYSE");

    trade1.setStock(stock1);                // 주식을 세팅했으므로 거래(Trade)에서 주식을 세팅
    trade1.setPrice(125.00);                // 주식의 가격과 수량을 세팅
    trade1.setQuantity(80);
    order.addTrade(trade1);                 // 모든 준비가 끝났으면 order에 거래 추가

    Trade trade2 = new Trade();             // 거래 2도 위와 마찬가지 흐름...
    trade2.setType(Trade.Type.SELL);        // 거래타입을 매도로 설정 (Trade Type.SELL)

    Stock stock2 = new Stock();
    stock2.setSymbol("GOOGLE");
    stock2.setMarket("NASDAQ");

    trade2.setStock(stock2);
    trade2.setPrice(375.00);
    trade2.setQuantity(50);
    order.addTrade(trade2);

    System.out.println("Plain:");         
    System.out.println(order);              // order 출력... 이 방법은 거래가 추가될때마다 같은 작업을 반복해줘야 한다는 큰 단점이 있다! (유지보수가 힘들다)
  }

  private static void methodChaining() {  // 2. 메소드 체인 ( 메소드가 꼬리에 꼬리를 물고 반환되는 형태 )
    Order order = forCustomer("BigBank")  // MethodChaningOrderBuilder의 정적 메소드 ( 여기서 start (MethodChaningOrderBuilde의 객체를 생성하는 메소드))
                  .buy(80)
                  .stock("IBM")
                  .on("NYSE")
                  .at(125.00)             
                  .sell(50)               // MethodChainingOrderBuilder -> TradeBuilder
                  .stock("GOOGLE")        // TradeBuilder -> StockBuilder
                  .on("NASDAQ")           // StockBuilder -> TradeBuilderWithStock
                  .at(375.00)             // TradeBuilderWithStock  -> MethodChainingOrderBuilder
                  .end();                 // MethodChainingOrderBuilder -> Order       
                  
                                          // 즉 메소드가 꼬리를 물면서 참조하여 결론적으로 Order을 반환하는것...
                                          // MethodChaningOrderBuilder의 forCustomer() static 메소드를 참조했어야함!!

    System.out.println("Method chaining:");
    System.out.println(order);
    }

  
  private static void nestFunctioning(){
    Order order = order("BigBank", buy(80, stock("IBM", on("NYSE")), at(125.00)) , sell(50, stock("GOOGLE", on("NASDAQ")),at(375.00)));

    System.out.println("NestFunction :");
    System.out.println(order);
  }

  // order(customer, trades)
  // trade(거래량, stock, 단가)
  // stock(주식이름, 거래소)

  private static void lambdaOrder(){

    Order order = order (o -> {
      o.forCustomer("BigBank");
      o.buy(t->{
        t.quantity(80);
        t.price(125.00);
        t.stock(s -> {
          s.symbol("IBM");
          s.market("NYSE");
        });
      });

      o.sell(t->{
        t.quantity(50);
        t.price(375.00);
        t.stock(s -> {
          s.symbol("GOOGLE");
          s.market("NASDAQ");
        });
      });
    });

    System.out.println("LambdaOrder : ");
    System.out.println(order);
  } 

    private static void MixingDsl(){
      Order order = forCustomer("BigBank", buy(t-> t.quantity(80).stock("IBM").on("NYSE").at(125.00)),
                                          sell(t-> t.quantity(50).stock("GOOGLE").on("NASDAQ").at(325.00))); //제일 깔끔하긴 하네 
      System.out.println("Mix Dsl : ");   // forCustomer(customer, builders) (중첩)
                                          // builder(람다식 ( 내부는 메소드 체인으로 연결 ))
      System.out.println(order);
      
    }
}
