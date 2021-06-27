package methodref;

import java.util.function.Function;

public class DefaultFunc {

    Function<Integer,Integer> f = x -> x + 1;
    Function<Integer,Integer> g = x -> x * 2;

    Function<Integer,Integer> h1 = f.andThen(g); // f를 수행한 후 g를 수행 g(f(x))
    Function<Integer,Integer> h2 = f.compose(g); // g를 수행한 후 f를 수행 f(g(x))

    
}
