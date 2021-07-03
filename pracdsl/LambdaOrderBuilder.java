package pracdsl;

import java.util.function.Consumer;

public class LambdaOrderBuilder {           // 메소드 쳉친과 비슷하지만, Consumer 객체를 인수로 받음으로써, 람다식으로 인수를 구현할 수 있게 함
    
    private Order order = new Order();      // 생성자 만든 뒤

    public static Order order(Consumer<LambdaOrderBuilder> consumer) {      //Consumer<LambdaOrderBuilder>를 인수로 받는 정적 메소드 order 정의
        LambdaOrderBuilder builder = new LambdaOrderBuilder();
        consumer.accept(builder);
        return builder.order;
    }

    public void forCustomer(String customer){
        order.setCustomer(customer);
    }

    public void buy(Consumer<TradeBuilder> consumer){
        trade(consumer, Trade.Type.BUY);
    }

    public void sell(Consumer<TradeBuilder> consumer){
        trade(consumer, Trade.Type.SELL);
    }

    private void trade(Consumer<TradeBuilder> consumer, Trade.Type type){
        TradeBuilder builder = new TradeBuilder();
        builder.trade.setType(type);
        consumer.accept(builder);
        order.addTrade(builder.trade);

    }

    public class TradeBuilder {
        private Trade trade = new Trade();

        public void quantity(int quantity){
            trade.setQuantity(quantity);
        }

        public void price(double price){
            trade.setPrice(price);
        }

        public void stock(Consumer<StockBuilder> consumer){
            StockBuilder builder = new StockBuilder();
            consumer.accept(builder);
            trade.setStock(builder.stock);
        }
    }

    public class StockBuilder {
        private Stock stock = new Stock();

        public void symbol(String symbol){
            stock.setSymbol(symbol);
        }

        public void market(String market){
            stock.setMarket(market);
        }
    }
}
