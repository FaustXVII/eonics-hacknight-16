package examples.snipits;

import java.util.function.Function;
import java.util.function.IntFunction;
import java.util.function.IntUnaryOperator;

// _____                       _
///  __ \                     (_)
//| /  \/_   _ _ __ _ __ _   _ _ _ __   __ _
//| |   | | | | '__| '__| | | | | '_ \ / _` |
//| \__/\ |_| | |  | |  | |_| | | | | | (_| |
// \____/\__,_|_|  |_|   \__, |_|_| |_|\__, |
//                        __/ |         __/ |
//                       |___/         |___/
public class CurryingExample {
    public void run(){
        exampleOne();
        exampleTwo();
    }

    private void exampleOne(){
        final Function<Integer, Function<Integer, Integer>> curryAdd = x -> y -> x + y;
        final int answer = curryAdd.apply(7).apply(10);

        System.out.println("Currying, answer one = " + answer);
    }

    private void exampleTwo(){
        final IntFunction<IntUnaryOperator> curryAdd = x -> y -> x + y;
        final int answer = curryAdd.apply(10).applyAsInt(7);

        System.out.println("Currying, answer two = " + answer);
    }
}
