package examples.snippets;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

//       _
//      | |
//   ___| |_ _ __ ___  __ _ _ __ ___
//  / __| __| '__/ _ \/ _` | '_ ` _ \
// _\__ \ |_| | |  __/ (_| | | | | | |
//(_)___/\__|_|  \___|\__,_|_| |_| |_|
public class StreamExample {
    List<String> names = Arrays.asList("Bob", "Jane", "Mary", "", null);

    public void run(){
        exampleOne();
        exampleTwo();
        exampleThree();
        exampleFour();
        examplefive();
    }

    private void exampleOne(){
        // 1 Good old for loop
        for (int i = 0; i < names.size(); i++){
            final String name = names.get(i);

            System.out.println(name);
        }
    }

    private void exampleTwo(){
        // 2 The better enhanced for loop
        for (String name : names){
            System.out.println(name);
        }
    }

    private void exampleThree(){
        // 3 Foreach stream
        names.forEach(name -> System.out.println(name));

        // open a stream on a collection
        // Map (Make changes to items)*
        // Filter (Make the collection smaller)*
        // Terminal(Make a collection form the results)
    }

    private void exampleFour(){
        // 4 Stream with a foreach
        names.stream().forEach(name -> System.out.println(name));
    }

    private void examplefive(){
        // You can also build a stream, much like a partially applied function.
        Stream streamOfNames = names.stream();

        // This way you can execute it later/ elsewhere
        streamOfNames.forEach(name -> System.out.println(name));
    }
}
