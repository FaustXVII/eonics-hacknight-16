package examples.snippets;

// _                     _         _
//| |                   | |       | |
//| |     __ _ _ __ ___ | |__   __| | __ _
//| |    / _` | '_ ` _ \| '_ \ / _` |/ _` |
//| |___| (_| | | | | | | |_) | (_| | (_| |
//\_____/\__,_|_| |_| |_|_.__/ \__,_|\__,_|
public class LambdaExample {
    public void run(){
        // Innerclass
        Runnable runnable1 = new Runnable() {
            @Override
            public void run() {
                System.out.println("Runnable 1");
            }
        };
        runnable1.run();


        // Innerclass set with lambda.
        Runnable runnable2 = () -> System.out.println("Runnable 2");
        runnable2.run();
    }
}
