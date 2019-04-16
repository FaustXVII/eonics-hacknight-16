package examples.snippets;

import java.util.Arrays;
import java.util.List;
import java.util.function.UnaryOperator;
import java.util.stream.Collectors;

//                                                                     _ _____ _
//                                             ___                    | |_   _| |
//  ___ ___  _ __ ___  _ __   ___  ___  ___   ( _ )     __ _ _ __   __| | | | | |__   ___ _ __
// / __/ _ \| '_ ` _ \| '_ \ / _ \/ __|/ _ \  / _ \/\  / _` | '_ \ / _` | | | | '_ \ / _ \ '_ \
//| (_| (_) | | | | | | |_) | (_) \__ \  __/ | (_>  < | (_| | | | | (_| | | | | | | |  __/ | | |
// \___\___/|_| |_| |_| .__/ \___/|___/\___|  \___/\/  \__,_|_| |_|\__,_| \_/ |_| |_|\___|_| |_|
//                    | |
//                    |_|
public class ComposingFunction {
    public void run() {
        exampleOneAndTwo();
        exampleThree();
    }

    private void exampleOneAndTwo(){
        final UnaryOperator<Integer> timesTwo = e -> e * 2;
        final UnaryOperator<Integer> square = e -> e * e;

        // The output of one becomes the input of the other.
        final int one = timesTwo.andThen(square).apply(3); //(3 * 2 = 6) (6 * 6 =) 36
        final int two = timesTwo.compose(square).apply(3); //(3 * 3 = 9) (9 * 2 =) 18

        System.out.println("Question one = " + one);
        System.out.println("Question two = " + two);
    }

    private void exampleThree(){
        // A more real-life example
        final UnaryOperator<List<Integer>> uniqueNumbers =  e -> e.stream().distinct().collect(Collectors.toList());
        final UnaryOperator<List<Integer>> order = e -> e.stream().sorted().collect(Collectors.toList());
        final UnaryOperator<List<Integer>> firstThree = e -> e.subList(0, 3);

        // All together make a new function.
        final UnaryOperator<List<Integer>> threeLowestUniqueNumbers = e -> uniqueNumbers.andThen(order.andThen(firstThree)).apply(e);

        final List<Integer> numbers = Arrays.asList(6,3,8,4,7,2,8,2,2,1,7,9);
        System.out.println("Question three = " + threeLowestUniqueNumbers.apply(numbers));
    }
}
