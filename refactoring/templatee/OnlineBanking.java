package refactoring.templatee;

import java.util.function.Consumer;

public abstract class OnlineBanking {
    public void processCustomer(int id, Consumer<Customer> makeCustomerHappy){  // 추상메소드 makeCustomerHappy를 담고있는 템플릿메소드 processCustomer
        Customer c = Database.getCustomerWithId(id);                            // 데이터페이스에서 아이디로 고객을 불러온 뒤, 고객을 행복하게 해준다는
                                                                                // 탬플릿 내용이 적혀있다...
        makeCustomerHappy.accept(c);
    }
    
    abstract void makeCustomerHappy(Customer c);
    
}
