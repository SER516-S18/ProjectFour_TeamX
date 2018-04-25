package main.client.controller;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Client window logic
 *
 * @author Jason Rice
 * @version 1.0
 */
public class ClientWindowLogic {
    JMenuItem openServer;

    /**
     * Constructor that sets the reference to the openServer menu item.
     *
     * @param openServer
     */
    public ClientWindowLogic(JMenuItem openServer) {
        openServer.addActionListener(generateOpenServerActionListener());
        this.openServer = openServer;
    }

    /**
     * Action Listener for the open server button in the menu on the client.
     */
    public ActionListener generateOpenServerActionListener() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                ServerInstanceSingleton.startServerInstance();
            }
        };
    }
}
