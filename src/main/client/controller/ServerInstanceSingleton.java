package main.client.controller;

import java.io.IOException;

/**
 * Creates a single instance of the server.
 *
 * @author Jason Rice
 * @version 1.0
 */
public class ServerInstanceSingleton {
    private static Process process = null;

    /**
     * private constructor so no new can be called.
     */
    private ServerInstanceSingleton() {
    }

    /**
     * If no instance exists create a new one.
     */
    public static void startServerInstance() {
        if (process == null || !process.isAlive()) {
            try {
                process = Runtime.getRuntime().exec("java -jar ProjectThree_Team08-server.jar");
            } catch (IOException exception) {
                exception.printStackTrace();
            }
        }
    }
}
