package examples.snippets;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Supplier;

// _____ _
///  __ \ |
//| /  \/ | ___  ___ _   _ _ __ ___
//| |   | |/ _ \/ __| | | | '__/ _ \
//| \__/\ | (_) \__ \ |_| | | |  __/
// \____/_|\___/|___/\__,_|_|  \___|
public class ClosureExample {
    public void run(){
        counters();
        betterClosure();

    }

    // The functions are impure, and violating the ideas of FP !!!
    // But I included those to show that it keeps some kind of internal state.
    // Which defines a Closure.
    private void counters(){
        Supplier<Supplier<Integer>> makeCounter = () -> {
            int[] local_variable = {0};
            return ()-> local_variable[0] += 1;
        };

        Supplier<Integer> counter = makeCounter.get();
        System.out.println("Counter = " + counter.get());// prints: Counter = 1
        System.out.println("Counter = " + counter.get());// prints: Counter = 2
        System.out.println("Counter = " + counter.get());// prints: Counter = 3
        System.out.println("Counter = " + counter.get());// prints: Counter = 4
        System.out.println("Counter = " + counter.get());// prints: Counter = 5


        // Looks less hacky, but keep in mind; even though you can make this, that doesn't mean you should!
        Supplier<Supplier<Integer>> makeCounter2 = () -> {
            AtomicInteger local_variable = new AtomicInteger(0);
            return ()-> local_variable.incrementAndGet();
        };

        Supplier<Integer> counter_2 = makeCounter2.get();
        System.out.println("Counter_2 = " + counter_2.get());// prints: Counter_2 = 1
        System.out.println("Counter_2 = " + counter_2.get());// prints: Counter_2 = 2
        System.out.println("Counter_2 = " + counter_2.get());// prints: Counter_2 = 3
    }


    private void betterClosure(){
        int number = 5;

        Supplier<Integer> add6ToTheNumber = () -> number + 6;

        // You can't change 'number' here, Java treads it as "effectively final".
        System.out.println("Better closure " + add6ToTheNumber.get());
    }
}
