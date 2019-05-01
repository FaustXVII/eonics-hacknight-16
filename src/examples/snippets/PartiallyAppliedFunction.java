package examples.snippets;

import java.util.function.Function;
import java.util.function.IntBinaryOperator;
import java.util.function.IntUnaryOperator;

public class PartiallyAppliedFunction {
    private final IntBinaryOperator addNumbers = (a, b) -> a + b;

    public void run(){
        exampleOne();
        exampleTwo();
        exampleThree();
    }

    // The normal way of calling a binary-function.
    private void exampleOne(){
        System.out.println(addNumbers.applyAsInt(2, 5));// 7
    }

    // A partially applied function filling an argument with an internal value.
    private void exampleTwo(){
        final IntUnaryOperator add2 = a -> addNumbers.applyAsInt(a, 2);
        System.out.println(add2.applyAsInt(8)); // 10
    }

    // Here the true power of a partially applied function is shown.
    // First we fill in the value of (a) and we can do other things before applying (b)
    // As you can see; we don't keep track of the state of the values but of the process instead.
    // This is especially useful when we pass it to other classes/objects.
    // Instead of passing down a group of vague values we try to give meaning to we pass down a partially filled in function.
    private void exampleThree(){
        final Function<Integer, IntUnaryOperator> addLeftFirstAndRightLater = a -> b -> addNumbers.applyAsInt(a, b);
        final IntUnaryOperator storeForLater = addLeftFirstAndRightLater.apply(5);// returns a partially applied function.
        // Do other stuff
        System.out.println(storeForLater.applyAsInt(3)); // 8
    }
}
