package pracdsl;

public class TradeBuilderWithStock {
    public final MethodChainingOrderBuilder builder;
    public final Trade trade;

    public TradeBuilderWithStock(MethodChainingOrderBuilder builder, Trade trade){
        this.builder = builder;
        this.trade = trade;
    }

    public MethodChainingOrderBuilder at(double price){
        trade.setPrice(price);
        return builder.addTrade(trade);
    }
}
