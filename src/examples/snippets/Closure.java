package examples.snippets;

import sun.java2d.xr.MutableInteger;

import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Supplier;

// _____ _
///  __ \ |
//| /  \/ | ___  ___ _   _ _ __ ___
//| |   | |/ _ \/ __| | | | '__/ _ \
//| \__/\ | (_) \__ \ |_| | | |  __/
// \____/_|\___/|___/\__,_|_|  \___|
public class Closure {
    public void run(){
        Supplier<Integer> counter = makeCounter.get();
        Supplier<Integer> counter_2 = makeCounter.get();

        System.out.println("Counter = " + counter.get());// prints: Counter = 1
        System.out.println("Counter = " + counter.get());// prints: Counter = 2
        System.out.println("Counter = " + counter.get());// prints: Counter = 3
        System.out.println("Counter = " + counter.get());// prints: Counter = 4
        System.out.println("Counter = " + counter.get());// prints: Counter = 5


        System.out.println("Counter_2 = " + counter_2.get());// prints: Counter_2 = 1
        System.out.println("Counter_2 = " + counter_2.get());// prints: Counter_2 = 2
        System.out.println("Counter_2 = " + counter_2.get());// prints: Counter_2 = 3
    }

    // Local_variable needs to be "implicit final", or we abuse the mutability of java =D
    Supplier<Supplier<Integer>> makeCounter = () -> {
        int[] local_variable = {0};
        return ()-> local_variable[0] += 1;
    };

    // I admit, the int Array is hacky
    // So here is a cleaner solution!
    Supplier<Supplier<Integer>> makeCounter2 = () -> {
        AtomicInteger local_variable = new AtomicInteger(0);
        return ()-> local_variable.incrementAndGet();
    };
}
