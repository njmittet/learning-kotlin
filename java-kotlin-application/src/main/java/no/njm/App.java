package no.njm;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class App {
    private static final Logger log = LogManager.getLogger(App.class);

    public static void main(String[] args) {
        GreetingJoiner greetingJoiner = new GreetingJoiner(new Greeting("Hi"));
        greetingJoiner.addName("Alfa");
        greetingJoiner.addName("Bravo");
        // Adding null in order to test the filtering in the GreetingJoiner.
        greetingJoiner.addName(null);
        greetingJoiner.addName("Charlie");

        log.info(greetingJoiner.joinGreetings());
    }
}