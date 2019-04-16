package examples.snippets;

import java.util.Arrays;
import java.util.List;

//___  ___     _   _               _  ______      __
//|  \/  |    | | | |             | | | ___ \    / _|
//| .  . | ___| |_| |__   ___   __| | | |_/ /___| |_ ___ _ __ ___ _ __   ___ ___
//| |\/| |/ _ \ __| '_ \ / _ \ / _` | |    // _ \  _/ _ \ '__/ _ \ '_ \ / __/ _ \
//| |  | |  __/ |_| | | | (_) | (_| | | |\ \  __/ ||  __/ | |  __/ | | | (_|  __/
//\_|  |_/\___|\__|_| |_|\___/ \__,_| \_| \_\___|_| \___|_|  \___|_| |_|\___\___|
public class MethodReferenceExample {
    // explain the difference between calling a method and having a reference.
    // read more about the "lazy evaluation".

    public void run() {
        List<String> helloWorld = Arrays.asList("Hello", "world");

        // The :: is the operator for a method reference
        // On the left you find the class and on the right the method
        helloWorld.forEach(System.out::println);
        helloWorld.forEach(this::myPrint);// Works the same

        // keep in mind, the thing you iterate on should be of the same type as the method argument.


    }

    private void myPrint(String printMe){
        System.out.println(printMe);
    }
}
