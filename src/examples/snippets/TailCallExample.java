package examples.snippets;

import javafx.util.Pair;
import java.util.function.*;

// _____     _ _             _ _
//|_   _|   (_) |           | | |
//  | | __ _ _| |   ___ __ _| | |
//  | |/ _` | | |  / __/ _` | | |
//  | | (_| | | | | (_| (_| | | |
//  \_/\__,_|_|_|  \___\__,_|_|_|
public class TailCallExample {
    public void run(){
        // factorial 5! = 5*4*3*2*1 = 120
        System.out.println("Tailcall, loop 'State full': " + factorial(5));
        System.out.println("Tailcall, Method 'Stateless': " + factorialTailCall(5));
        System.out.println("Tailcall, Function 'Stateless': " + factorial.applyAsInt(5));
        System.out.println("Tailcall, trampoline: " + trampoline(5l));
    }

    // Has state, the: i
    private int factorial(final int number){
        int answer = number;

        for(int i = 1; i < number; i++){
            answer = answer * i;
        }

        return answer;
    }

    // Warning!
    // Be aware of "tailcall optimization" which the JVM does -not- support.
    // If you compile with https://github.com/bodar/jcompilo you do have "tailcall optimization" !!!

    // Has no state
    private int factorialTailCall(final int number){
        return (number == 1)? 1 : number * factorialTailCall(number -1);
    }

    // Same as factorialTailCall but even less boilerplate code
    private final IntUnaryOperator factorial = number -> (number == 1)? 1 : number * this.factorial.applyAsInt(number -1);



    // Same pattern as a TailCalled function, but no risk of a StackOverflowError
    private long trampoline(long number){
        Pair<Supplier, Long> supplierOrResult = new Pair<>(()-> factorial(number, 1), null);

        while (supplierOrResult.getValue() == null){
            supplierOrResult = (Pair<Supplier, Long>)supplierOrResult.getKey().get();
        }

        return supplierOrResult.getValue();
    }

    private Pair<Supplier, Long> factorial(long number, long sum){
        return (number == 1)
            ? new Pair<>(null, sum)
            : new Pair<>(() -> factorial(number -1, sum * number), null);
    }

}
