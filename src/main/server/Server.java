package main.server;

import main.model.EmotionMessageBean;
import main.model.MessageContolBean;
import main.server.controller.EndpointController;
import main.server.controller.ServerEndpoint;
import main.server.view.ConsolePanel;
import main.server.view.DetectionPanel;
import main.server.view.InteractivePanel;
import main.server.view.ServerWindow;
import main.utils.ConnectionConstants;

import javax.websocket.DeploymentException;

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
        EmotionMessageBean emotionMessageBean = new EmotionMessageBean();
        MessageContolBean messageContolBean = new MessageContolBean();
        messageContolBean.setEmotionMessageBean(emotionMessageBean);
        EndpointController.getInstance().setMessageControlBean(messageContolBean);
        InteractivePanel interactivePanel = new InteractivePanel(messageContolBean);
        DetectionPanel detectionPanel = new DetectionPanel(messageContolBean);
        ConsolePanel consolePanel = new ConsolePanel();
        ServerWindow window = new ServerWindow(interactivePanel, detectionPanel, consolePanel);
        window.pack();
        window.setVisible(true);
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
