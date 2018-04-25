package test.java.server.controller;

import main.server.controller.ServerEndpoint;
import main.utils.ConnectionConstants;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import javax.websocket.DeploymentException;
import java.net.URISyntaxException;

import static org.junit.Assert.assertEquals;


/**
 * This is to test the server and client connection
 *
 * @author Balachandar Sampath
 * @version 1.0
 */
@RunWith(JUnit4.class)
public class ServerTest {
    public static final String SERVER = "ws://" + ConnectionConstants.HOSTNAME + ":" + ConnectionConstants.PORT + "/" + ConnectionConstants.ROOT_PATH + "/" + ConnectionConstants.ENDPOINT_PATH;
    org.glassfish.tyrus.server.Server server;

    /**
     * Initialise server with default port mentioned in Connection constants
     */
    @Before
    public void init() {
        System.out.println("Test Initialising");
        System.out.println("Server Starting on " + ConnectionConstants.PORT);
        server = new
                org.glassfish.tyrus.server.Server(ConnectionConstants.HOSTNAME,
                ConnectionConstants.PORT, "/" + ConnectionConstants.ROOT_PATH, ServerEndpoint.class);
        System.out.println("Client Connecting on" + ConnectionConstants.PORT);


    }

    /**
     * Check if server started
     *
     * @throws URISyntaxException
     * @throws InterruptedException
     */
    @Test
    public void isServerStarted() throws URISyntaxException, InterruptedException {
        try {
            server.start();
            assertEquals(server.getServerContainer().getExecutorService().isShutdown(), Boolean.FALSE);
        } catch (DeploymentException e) {
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Check if server stopped
     *
     * @throws URISyntaxException
     * @throws InterruptedException
     */
    @Test
    public void isServerStopped() throws URISyntaxException, InterruptedException {
        try {
            server.start();
            assertEquals(server.getServerContainer().getExecutorService().isShutdown(), Boolean.FALSE);
            server.stop();
            assertEquals(server.getServerContainer(), null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Reclaim the resources by stopping the server
     */
    @After
    public void tearDown() {
        server.stop();
        System.out.println("server stopped");
    }

}
