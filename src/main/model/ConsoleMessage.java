package main.model;

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
     *
     * @return message
     */
    public String getMessage() {
        return message;
    }

    /**
     *
     * @param message
     */
    public void setMessage(String message) {
        this.message = message;
        setChanged();
        notifyObservers();
    }

    private String message;

}
