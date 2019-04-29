package examples.snippets;

import java.util.function.Function;
import java.util.function.IntBinaryOperator;
import java.util.function.IntUnaryOperator;

public class PartiallyAppliedFunction {
    IntBinaryOperator addNumbers = (a, b) -> a + b;

    IntUnaryOperator add2 = a -> addNumbers.applyAsInt(a, 2);
    Function<Integer, IntUnaryOperator> addLeftFirstAndRightLater = a -> b -> addNumbers.applyAsInt(a, b);

    public void run(){
        System.out.println(addNumbers.applyAsInt(2, 5));// 7
        System.out.println(add2.applyAsInt(8)); // 10
        IntUnaryOperator storeForLater = addLeftFirstAndRightLater.apply(5);// returns a partially applied function.
        // Do other stuff
        System.out.println(storeForLater.applyAsInt(3)); // 8
    }
}
