package refactoring.templatee;

import java.util.function.Consumer;

public class OnlineBankingLambda {
    
    public void processCustomer(int id, Consumer<Customer> makeCustomerHappy) {
        Customer c = Database.getCustomerWithId(id);
        makeCustomerHappy.accept(c);
      }

      public static void main(String[] args) {
        new OnlineBankingLambda().processCustomer(1557, c -> System.out.println("hi "+c.getName()));
      }

}
