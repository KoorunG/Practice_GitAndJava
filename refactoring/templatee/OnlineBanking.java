package refactoring.templatee;

import java.util.function.Consumer;

public abstract class OnlineBanking {
    public void processCustomer(int id, Consumer<Customer> makeCustomerHappy){
        Customer c = Database.getCustomerWithId(id);
        makeCustomerHappy.accept(c);
    }
    
    abstract void makeCustomerHappy(Customer c);
    
}
