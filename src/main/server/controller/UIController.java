package main.server.controller;

import main.server.view.ConsolePanel;
import main.server.view.DetectionPanel;
import main.server.view.InteractivePanel;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Controller which acts as a interface between EndpointController and view(UI).
 * This class has to be created first when the main.server ui or main.client ui
 * is on and should be the first one to be invoked
 *
 * @author Balachandar Sampath, Rhythm Sharma, Aashita Priya
 * @version 1.0
 */

public class UIController {
    private static DetectionPanel detectionPanel;
    private static InteractivePanel interactivePanel;
    private static boolean run = true;
    private ConsolePanel consolePanel;
    private boolean runningEyeVal = false;

    private UIController() {
    }



}
