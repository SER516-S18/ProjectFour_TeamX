package main.model;

/**
 * Bean which stores the console message to be shown on the server side.
 * Setter Getter for class properties included
 *
 * @author Amaresh Bingumalla
 * @version 1.0
 */
import java.util.Observable;

public class ConsoleMessage extends Observable {

    private  ConsoleMessage(){

    }

    /**
     * Returns an instance of the class
     *
     * @return ConsoleMessage
     */
    public static ConsoleMessage getInstance() {

        return SingletonHolder.consoleMessage;
    }

    /**
     * This will support lazy initialisation of consoleMessage
     */
    private static class SingletonHolder {
        public static final ConsoleMessage
                consoleMessage = new ConsoleMessage();
    }

    /**
     * Getter
     * @return message
     */
    public String getMessage() {
        return message;
    }

    /**
     * Setter
     * @param message
     */
    public void setMessage(String message) {
        this.message = message;
        setChanged();
        notifyObservers();
    }

    private String message;

}
