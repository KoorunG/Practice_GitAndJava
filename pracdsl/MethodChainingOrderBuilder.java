package pracdsl;

public class MethodChainingOrderBuilder {

  public final Order order = new Order();                                 // Order 객체 생성 (final을 붙여서)

  private MethodChainingOrderBuilder(String customer) {                   // customer를 인자로 받는 생성자 설정
    order.setCustomer(customer);                                          // final로 설정한 order.setCustomer(customer) 수행...
  }

  public static MethodChainingOrderBuilder forCustomer(String customer) { // 생성자를 만드는 forCustomer 전역 메소드 설정 (생성자가 private이므로)
    return new MethodChainingOrderBuilder(customer);
  }

  public Order end() {    // end() 메소드 생성 -> order을 반환! ( 즉, 모든 설정을 마무리하고 order을 반환한다 )
    return order;
  }
                                                // buy or sell 메소드 (MethodChainingOrderBuilder -> TradeBuilder 연결)
  public TradeBuilder buy(int quantity) {       // TradeBuilder 자료형의 buy 메소드 설정 (quantity를 인자로 받음)
    return new TradeBuilder(this, Trade.Type.BUY, quantity);    // TradeBuilder의 생성자를 만듦( this => MethodChainingOrderBuilder 자료형을 의미 ) 
  }

  public TradeBuilder sell(int quantity) {
    return new TradeBuilder(this, Trade.Type.SELL, quantity);
  }

  private MethodChainingOrderBuilder addTrade(Trade trade) {    // MethodChainingOrderBuilder 타입의 addTrade 메소드 생성 -> 
    order.addTrade(trade);      // trade를 인자로 받아 order에 추가하고
    return this;                // this를 반환함 (생성된 인스턴스 스스로를 가리키는 예약어 즉 여기서는 MethodChainingOrderBuilder의 인스턴스!!)
  }

  public static class TradeBuilder {                                    // 1. static inner class TradeBuilder 생성

    private final MethodChainingOrderBuilder builder;         // MethodChainingOrderBuilder 타입의 builder 생성 (final임 -> 상수)
    public final Trade trade = new Trade();                   // 역시 final인 Trade 생성

    private TradeBuilder(MethodChainingOrderBuilder builder, Trade.Type type, int quantity) { // TradeBuilder의 생성자( builder, type, quantity )
      this.builder = builder;           // builder를 입력받아 final인 builder에 대입
      trade.setType(type);              // 위에서 생성자로 만든 final 타입의 trade의 Type과 quantity를 Setter 메소드를 통해 세팅
      trade.setQuantity(quantity);
    }
                                                    // stock 메소드 (TradeBuilder -> StockBuilder 연결)
    public StockBuilder stock(String symbol) {      // symbol을 인자로 받는 StockBuilder 타입의 stock 메소드 생성
      return new StockBuilder(builder, trade, symbol);  // StockBuilder 객체를 생성하는데 인자로 builder(상수), trade(상수), symbol(인자)을 받음 
    }

  }

  public static class StockBuilder {                                     // 2. static inner class StockBuilder 생성


    private final MethodChainingOrderBuilder builder;
    private final Trade trade;                                           // 변수 선언
    private final Stock stock = new Stock();                             // 생성자로 stock 생성

    private StockBuilder(MethodChainingOrderBuilder builder, Trade trade, String symbol) {      //StockBuilder 의 생성자 정의
      this.builder = builder;
      this.trade = trade;
      stock.setSymbol(symbol);
    }

    public TradeBuilderWithStock on(String market) {                     // on 메소드 (StockBUilder과  TradeBuilderWithStock 연결)
      stock.setMarket(market);
      trade.setStock(stock);
      return new TradeBuilderWithStock(builder, trade);
    }

  }

  public static class TradeBuilderWithStock {                            // 3. static inner class TradeBuilderWithStock 생성


    private final MethodChainingOrderBuilder builder;
    private final Trade trade;

    public TradeBuilderWithStock(MethodChainingOrderBuilder builder, Trade trade) {
      this.builder = builder;
      this.trade = trade;
    }

    public MethodChainingOrderBuilder at(double price) {                 // at 메소드 (TradeBuilderWithStock ->  MethodChainingOrderBuilder 연결)
      trade.setPrice(price);
      return builder.addTrade(trade);
    }

  }
                  /** 메소드 체이닝 => 함수의 리턴값이 또 다른 함수를 호출하는데 쓰임
                   *  따라서 굳이 리턴값을 저장하는 변수는 필요없기때문에 없애고 함수들만 연이어 작성하도록 코드를 작성할 수 있다.
                   *  코드의 가독성을 높일 수 있는 방법이기 때문에 개발자들이 많이 쓰는 방법!
                   *  여기서 final을 쓰는 이유 : 여러개의 elements중 아무거나 리턴하여 혼란스러운 상황이 발생하는 것을 미리 방지하기 위함
                   */

                  // buy or sell 메소드 (MethodChainingOrderBuilder -> TradeBuilder 연결)
                  // stock 메소드 (TradeBuilder -> StockBuilder 연결)
                  // on 메소드 (StockBUilder과  TradeBuilderWithStock 연결)
                  // at 메소드 (TradeBuilderWithStock ->  MethodChainingOrderBuilder 연결)
                  // end 메소드 (MethodChainingOrderBuilder 자료형으로 order 반환)

}
