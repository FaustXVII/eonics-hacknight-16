package examples.snippets;

import java.util.function.*;

//______                _   _               _____      _             __
//|  ___|              | | (_)             |_   _|    | |           / _|
//| |_ _   _ _ __   ___| |_ _  ___  _ __     | | _ __ | |_ ___ _ __| |_ __ _  ___ ___
//|  _| | | | '_ \ / __| __| |/ _ \| '_ \    | || '_ \| __/ _ \ '__|  _/ _` |/ __/ _ \
//| | | |_| | | | | (__| |_| | (_) | | | |  _| || | | | ||  __/ |  | || (_| | (_|  __/
//\_|  \__,_|_| |_|\___|\__|_|\___/|_| |_|  \___/_| |_|\__\___|_|  |_| \__,_|\___\___|
//
//
public class FunctionInterfaceExample {
    public void run() {
        final Runnable runnable = () -> System.out.println("Hello World! From a runnable.");
        runnable.run();

        final Consumer consumer = e -> System.out.println(e);
        consumer.accept("Hello world! From a consumer.");

        final Supplier<String> supplier = () -> "Hello world! From a supplier.";
        System.out.println(supplier.get());

        final Function<String, String> function = e -> e + " From a function.";
        System.out.println(function.apply("Hello world!"));

        final BiFunction<Integer, String, String> biFunction = (e1, e2) -> e2 + e1;
        System.out.println(biFunction.apply(1, "Hello world "));

        final BinaryOperator<String> binaryOperator = (e1, e2) -> e1 + e2;
        System.out.println(binaryOperator.apply("Hello world!", " From binaryOperator"));

        final Predicate<String> predicate = e -> e.startsWith("Hello");
        final String helloWorld = "Hello world! From predicate";
        if (predicate.test(helloWorld)) {
            System.out.println(helloWorld);
        }

        final UnaryOperator<String> unaryOperator = e -> e + " world!";
        System.out.println(unaryOperator.apply("Hello"));
    }
}
