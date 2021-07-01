package pracdsl;

import java.util.ArrayList;
import java.util.List;

public class Order {
    
    private String customer;
    private List<Trade> trades = new ArrayList<>();

    public void addTrade(Trade trade){
        trades.add(trade);
    }

    public String getCustomer(){
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public double getValue() {
        return trades.stream().mapToDouble(Trade::getValue).sum();
    }

    public static void main(String[] args) {

       makeOrder1();

    }

    private static void makeOrder1(){

        Order order = new Order();         // 주문 객체를 새로 만들고
        order.setCustomer("BigBank");      // 고객의 이름을 BigBank로 세팅

        Trade trade1 = new Trade();        // Trade 객체도 새로 만들고 - trade1
        trade1.setType(Trade.Type.BUY);    // 매수주문으로 설정

        Stock stock1 = new Stock();        // 주식 객체도 새로 만들고 - stock1
        stock1.setSymbol("IBM");           // IBM 주식
        stock1.setMarket("NYSE");          // NYSE 거래소

        trade1.setStock(stock1);           // trade1에 주식을 연결
        trade1.setPrice(125.00);           // trade의 price 설정 - 125.00$
        trade1.setQuantity(80);            // trade의 수량 설정 - 80주
        order.addTrade(trade1);            // 이 모든 세팅이 끝났으면 order에 등록!

        Trade trade2 = new Trade();        // Trade 객체도 새로 만들고 - trade2
        trade2.setType(Trade.Type.BUY);    // 매수주문으로 설정

        Stock stock2 = new Stock();        // 주식 객체도 새로 만들고 - stock2
        stock2.setSymbol("GOOGLE");        // GOOGLE 주식
        stock2.setMarket("NASDAQ");        // NASDAQ 거래소

        trade2.setStock(stock2);           // trade2에 주식을 연결
        trade2.setPrice(375.00);           // trade의 price 설정 - 375.00$
        trade2.setQuantity(50);            // trade의 수량 설정 - 50주
        order.addTrade(trade2);            // order에 trade2 등록
    }
}
