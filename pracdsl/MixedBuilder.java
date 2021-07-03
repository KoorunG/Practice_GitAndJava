package pracdsl;

import java.util.function.Consumer;
import java.util.stream.Stream;

public class MixedBuilder {
    
    public static Order forCustomer(String customer, TradeBuilder ... builders){            // TradeBuilder를 스트림 처리하여 추가함 -> 중첩 방식
                                                                                        // forCustomer - buy,sell 사이에는 중첩 방식이 채용되었다
        Order order = new Order();
        order.setCustomer(customer);
        Stream.of(builders).forEach(b -> order.addTrade(b.trade));
        return order;
    }

    public static TradeBuilder buy(Consumer<TradeBuilder> consumer){        // Consumer를 인수로 받음 -> 람다 방식
        return buildTrade(consumer, Trade.Type.BUY);
    }

    public static TradeBuilder sell(Consumer<TradeBuilder> consumer){
        return buildTrade(consumer, Trade.Type.SELL);
    }

    private static TradeBuilder buildTrade(Consumer<TradeBuilder> consumer, Trade.Type buy){
        TradeBuilder builder = new TradeBuilder();
        builder.trade.setType(buy);
        consumer.accept(builder);
        return builder;
    }
    

    public static class TradeBuilder {                      // 내부 클래스는 전역(static)클래스로 설정해줘야 외부클래스에서 참조가 쉽다...? (다시 알아볼것)
        private Trade trade = new Trade();

        public TradeBuilder quantity(int quantity){
            trade.setQuantity(quantity);
            return this;
        }

        public TradeBuilder at(double price){
            trade.setPrice(price);
            return this;
        }

        public StockBuilder stock(String symbol){
            return new StockBuilder(this, trade, symbol);       // TradeBuilder - StockBuilder 연결 -> 메소드 체이닝 방식
        }

    }

    public static class StockBuilder {
        private final TradeBuilder builder;
        private final Trade trade;
        private final Stock stock = new Stock();

        public StockBuilder(TradeBuilder builder, Trade trade, String symbol){
            this.builder = builder;
            this.trade = trade;
            stock.setSymbol(symbol);
        }

        public TradeBuilder on(String market){
            stock.setMarket(market);
            trade.setStock(stock);
            return builder;
        }
    }

}
