package refactoring.stragy;

@FunctionalInterface
public interface ValidationStrategy {
    boolean execute(String s);
}
