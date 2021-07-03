package refactoring.stragy;

@FunctionalInterface        // 1. 검증 전략 인터페이스를 생성 (텍스트가 다양한 조건에 맞게 포멧되어 있는지)
public interface ValidationStrategy {
    boolean execute(String s);
}   
