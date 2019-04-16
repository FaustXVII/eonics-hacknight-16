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
    // Look at biteCode and see if this is faster than a for loop.
    public void run(){
        // 1    Good old for loop
        for (int i = 0; i < names.size(); i++){
            final String name = names.get(i);

            System.out.println(name);
        }

        // 2    The better enhanced for loop
        for (String name : names){
            System.out.println(name);
        }

        // 3    Foreach stream
        names.forEach(name -> System.out.println(name));

        // 4    Stream with a foreach
        names.stream().forEach(name -> System.out.println(name));

        // Q: When do you want a stream separated form the 'terminal operation' (foreach part)
        Stream streamOfNames = names.stream();

        // A: So you can use it later / some where else.
        streamOfNames.forEach(name -> System.out.println(name));


        //open a stream on a collection
        // Map (Make changes to items)          // The order of Map and Filter does NOT mather.
        // Filter (Make the collection smaller) // Lazy evaluation uses the most optimal form anyway.
        // Terminal(Make a collection form the results)
    }
}
