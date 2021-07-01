package pracdsl;

import java.util.stream.Stream;

public class NestFunctionOrderBuilder {
    
    public static Order order(String customer, Trade ... trades ){      // order(customer, trades)
        Order order = new Order();                                      // Trade ... trades -> 요소가 여러개 있을 수 있음을 명시하는 것    
        order.setCustomer(customer);
        Stream.of(trades).forEach(order::addTrade);  // order에 모든 trades를 추가하는 스트림    (Order의 메소드 addTrade)
        return order;
    }

    public static Trade buy(int quantity, Stock stock, double price){   // trade(거래량, stock, 단가)
        return buildTrade(quantity, stock, price, Trade.Type.BUY);
    }

    public static Trade sell(int quantity, Stock stock, double price){   // trade(거래량, stock, 단가)
        return buildTrade(quantity, stock, price, Trade.Type.SELL);
    }                   
    
    private static Trade buildTrade(int quantity, Stock stock, double price, Trade.Type buy){
        Trade trade = new Trade();
        trade.setQuantity(quantity);
        trade.setType(buy);
        trade.setStock(stock);
        trade.setPrice(price);
        return trade;
    }

    public static double at(double price){
        return price;
    }

    public static Stock stock(String symbol, String market){
        Stock stock = new Stock();
        stock.setSymbol(symbol);
        stock.setMarket(market);
        return stock;
    }

    public static String on(String market){
        return market;
    }
}
