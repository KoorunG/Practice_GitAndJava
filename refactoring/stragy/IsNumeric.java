package refactoring.stragy;

public class IsNumeric implements ValidationStrategy {      // 전략을 구현하는 클래스 1

    public boolean execute(String s){                       
        return s.matches("\\d+");
    }
    
}
