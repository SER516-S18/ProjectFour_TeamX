package main.server.controller;

import main.server.view.ServerInitUI;
import main.utils.ConnectionConstants;

import javax.websocket.DeploymentException;
import java.util.Scanner;

/**
 * Server Class which starts the server application
 *
 * @author Balachandar Sampath
 * @version 1.0
 */
public class Server {

    /**
     * Main Function which initialises the Server
     *
     * @param args
     */
    public static void main(String[] args) {

        // Initilaise the gui
        ServerInitUI.initializeGUI();
        org.glassfish.tyrus.server.Server server = new
                org.glassfish.tyrus.server.Server(ConnectionConstants.HOSTNAME,
                ConnectionConstants.PORT, "/" + ConnectionConstants.ROOT_PATH, ServerEndpoint.class);
        try {
           new Thread() {
               @Override
               public void run() {
                   super.run();
                   try {
                       server.start();
                   } catch (DeploymentException e) {
                       e.printStackTrace();
                   }
               }
           }.start();
        }  finally {
            server.stop();
        }
    }
}
