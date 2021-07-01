package pracdsl;

public class StockBuilder {

    public final MethodChainingOrderBuilder builder;
    public final Trade trade;
    public final Stock stock = new Stock();

    public StockBuilder(MethodChainingOrderBuilder builder, Trade trade, String symbol){
        this.builder = builder;
        this.trade = trade;
        stock.setSymbol(symbol);
    }

    public TradeBuilderWithStock on(String market){
        stock.setMarket(market);
        trade.setStock(stock);
        return new TradeBuilderWithStock(builder, trade);
    }
    
}
