package main.model;

import java.util.Observable;

public class ConsoleMessage extends Observable {
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
        notifyObservers();
    }

    private String message;

}
