
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
  }

  private static void plain() {
    Order order = new Order();
    order.setCustomer("BigBank");

    Trade trade1 = new Trade();
    trade1.setType(Trade.Type.BUY);

    Stock stock1 = new Stock();
    stock1.setSymbol("IBM");
    stock1.setMarket("NYSE");

    trade1.setStock(stock1);
    trade1.setPrice(125.00);
    trade1.setQuantity(80);
    order.addTrade(trade1);

    Trade trade2 = new Trade();
    trade2.setType(Trade.Type.BUY);

    Stock stock2 = new Stock();
    stock2.setSymbol("GOOGLE");
    stock2.setMarket("NASDAQ");

    trade2.setStock(stock2);
    trade2.setPrice(375.00);
    trade2.setQuantity(50);
    order.addTrade(trade2);

    System.out.println("Plain:");
    System.out.println(order);
  }

  private static void methodChaining() {
    Order order = forCustomer("BigBank")  //MethodChaningOrderBuilder의 정적 메소드 ( 여기서 start )
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
                                          //MethodChaningOrderBuilder의 forCustomer() static 메소드를 참조했어야함!!

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
                                          sell(t-> t.quantity(50).stock("GOOGLE").on("NASDAQ").at(325.00)));
    }
}
