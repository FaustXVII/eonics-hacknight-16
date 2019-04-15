package examples.snipits;

import java.util.function.IntUnaryOperator;

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

    // Has no state
    private int factorialTailCall(final int number){
        return (number == 0)? 1 : number * factorialTailCall(number -1);
    }

    // Same as factorialTailCall but even less boilerplate code
    private final IntUnaryOperator factorial = number -> (number == 0)? 1 : number * factorial(number -1);
}
