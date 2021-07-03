package pad;

public class pad {

    public class Prac1{
        
        int num1 = 10;

        Runnable r1 = new Runnable(){
            int num1 = 2;                                    // 익명클래스에서는 쉐도잉이 가능함
            public void run() {
            System.out.println("Hello");
         }
        };                                                  // 1. 익명 클래스

        int num2 = 10;
        Runnable r2 = () -> {
            int num2 = 3;                                   // 이게 왜 되는거임??? 내부클래스라 그런가?
            System.out.println(num2);
        };                                                  // 2. 람다식 (시그니쳐 () -> void )

    }

    
}
