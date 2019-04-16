package examples.snippets;

// _____ _             _         ___  _         _                  _    ___  ___     _   _               _
///  ___(_)           | |       / _ \| |       | |                | |   |  \/  |    | | | |             | |
//\ `--. _ _ __   __ _| | ___  / /_\ \ |__  ___| |_ _ __ __ _  ___| |_  | .  . | ___| |_| |__   ___   __| |
// `--. \ | '_ \ / _` | |/ _ \ |  _  | '_ \/ __| __| '__/ _` |/ __| __| | |\/| |/ _ \ __| '_ \ / _ \ / _` |
///\__/ / | | | | (_| | |  __/ | | | | |_) \__ \ |_| | | (_| | (__| |_  | |  | |  __/ |_| | | | (_) | (_| |
//\____/|_|_| |_|\__, |_|\___| \_| |_/_.__/|___/\__|_|  \__,_|\___|\__| \_|  |_/\___|\__|_| |_|\___/ \__,_|
//                __/ |
//               |___/
public class SingleAbstractMethod {
    // Also called SAM, Single Abstract Method

    public void run(){
        eventObject();
        eventLambda();
    }

    private static void eventObject(){
        Event event = new Event() {
            @Override
            public void run() {
                System.out.println("Hello world!");
            }
        };

        event.run();
    }

    private static void eventLambda(){
        Event event = () -> System.out.println("Hello world!");
        event.run();
    }
}

interface Event {
    void run();
}
