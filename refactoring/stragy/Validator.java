package refactoring.stragy;

public class Validator {
    private final ValidationStrategy strategy;
    public Validator(ValidationStrategy v){
        this.strategy = v;
    }
    
    public boolean validate(String s) {
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

    }
   

}


