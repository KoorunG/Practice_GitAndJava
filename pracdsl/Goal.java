package pracdsl;

public class Goal {

    public static void main(String[] args) {
        
        makeOrder2();
    }


    private static void makeOrder2(){

    Order order = forCustomer("BigBank")   // MethodChainingOrderBuilder 자료형의 정적메소드 forCustomer(customer)인데...
                        .at(375.00)       // MethodChainingOrderBuilder -> TradeBuilderWithStock
                        .on("NASDAQ")     // TradeBuilderWithStock -> StockBuilder
                        .stock("GOOGLE")  // StockBuilder -> TradeBuilder
                        .sell(50)         // TradeBuilder -> MethodChainingOrderBuilder
                        .end();           // MethodChainingOrderBuilder -> Order 

                    //   .buy(80)
                    //   .stock("IBM")
                    //   .on("NYSE")
                    //   .at(125.00)
                      
                }
}     
    
