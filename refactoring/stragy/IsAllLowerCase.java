package refactoring.stragy;

public class IsAllLowerCase implements ValidationStrategy {     // 전략을 구현하는 클래스 2 

    @Override
    public boolean execute(String s) {
        return s.matches("[a-z]+");
    }
    
}
