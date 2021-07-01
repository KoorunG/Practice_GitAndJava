package pracdsl;

public class MethodChainingOrderBuilder {
    
    public final Order order = new Order();

    public MethodChainingOrderBuilder(String customer) {
        order.setCustomer(customer);
    }

    public static MethodChainingOrderBuilder forCustomer(String customer){
        return new MethodChainingOrderBuilder(customer);
    }

    public TradeBuilder buy(int quantity){
        return new TradeBuilder(this, Trade.Type.BUY,quantity);
    }

    public TradeBuilder sell(int quantity){
        return new TradeBuilder(this, Trade.Type.SELL,quantity);
    }

    public MethodChainingOrderBuilder addTrade(Trade trade){
        order.addTrade(trade);
        return this;            // this = 주문 빌더를 가리킨다
    }

    public Order end(){
        return order;
    }
}
