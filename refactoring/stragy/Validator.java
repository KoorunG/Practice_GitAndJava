package refactoring.stragy;

public class Validator {
    private final ValidationStrategy strategy;          // 전략을 private final로 설정
    public Validator(ValidationStrategy v){             // 인터페이스 ValidationStrategy를 인자로 받는 생성자
        this.strategy = v;
    }
    
    public boolean validate(String s) {                 // 구현한 전략을 실행하는 메소드 validate(s)
        return strategy.execute(s);
    }
}

class App {
    public static void main(String[] args) {
        // Validator numericValidator = new Validator(new IsNumeric());
        // boolean b1 = numericValidator.validate("aaaa");
        // System.out.println(b1);                                     //false        1. 객체를 생성하여 넣어줌

        Validator numericValidator = new Validator((String s ) -> s.matches("\\d+"));
        boolean b1 = numericValidator.validate("aaaa");
        System.out.println(b1);                                                     //2. 람다로 직접 전달해줌

        // Validator lowerValidator = new Validator(new IsAllLowerCase());
        // boolean b2 = lowerValidator.validate("bbbb");
        // System.out.println(b2);                                     //true

        Validator lowerValidator = new Validator((String s) -> s.matches("[a-z]+"));
        boolean b2 = lowerValidator.validate("bbbb");
        System.out.println(b2);

        //  Validator v1 = new Validator(s -> s.matches("전략을 직접 씀"));     // 이 경우... 굳이 인터페이스를 설정하지 않아도 되겠는데?

    }
   

}


