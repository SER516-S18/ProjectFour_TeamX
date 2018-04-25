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

    /**
     * Returns an instance of the class
     *
     * @return UIController
     */
    public static UIController getInstance() {
        return SingletonHolder.uiController;
    }

    /**
     * Sets the Detection Panel value for the class Property
     *
     * @param detectionPanelObj Detection Panel object on the server UI
     */
    public static void setDetectionPanel(DetectionPanel detectionPanelObj) {
        detectionPanel = detectionPanelObj;
    }

    /**
     * Sets the Interactive Panel value for the class Property
     *
     * @param interactivePanelObj Interactive Panel object on the server UI
     */
    public static void setInteractivePanel(InteractivePanel interactivePanelObj) {
        interactivePanel = interactivePanelObj;
    }

    /**
     * This method is updating the send button text on server when autoReset is
     * checked/unchecked
     *
     * @param autoResetSelected boolean flag states if auto reset is
     *                          selected in interactive panel
     */
    public static void updateSendButtonText(boolean autoResetSelected) {
        String sendBtnText;
        if (autoResetSelected) {
            sendBtnText = "Start";
        } else {
            sendBtnText = "Send";
        }
        interactivePanel.updateSendBtnText(sendBtnText);
    }

    /**
     * This method will send the message bean to all the clients
     * It will also update Send Button Text depending upon
     * AutoReset Value and running state of server
     *
     * @param presentText the value/text on the Send Button
     */
    public static void sendMessageBean(String presentText) {
        if (interactivePanel.isAutoReset()) {
            if ("start".equalsIgnoreCase(presentText)) {
                interactivePanel.updateSendBtnText("Stop");
                EndpointController.getInstance().sendInIntervals(interactivePanel.getEmoStateTimeInterval());
            } else {
                interactivePanel.updateSendBtnText("Start");
                // stop sending data to the clients
                EndpointController.getInstance().stop();
            }
        }
        EndpointController.getInstance().sendOnce();
    }

    /**
     * This method is to update time in detection Panel
     *
     * @param emoStateInterval gives the value of the Emo State set in
     *                         the interactive panel
     */
    public static void updateDetectionTime(double emoStateInterval) {
        if (!interactivePanel.isAutoReset()) {
            double detectionTime = detectionPanel.getTimeTextField();
            detectionTime += emoStateInterval;
            detectionPanel.setTimeTxtField(detectionTime);
        } else {
            if ("start".equalsIgnoreCase(interactivePanel.getBtnSendValue())) {
                run = false;
            } else {
                run = true;
            }
            Timer timer = new Timer();
            long delay = 0;
            long intervalPeriod = (long) (emoStateInterval * 1000);
            timer.schedule(new TimerTask() {
                double detectionTime, newDetectionTime;
                public void run() {
                    if (run) {
                        detectionTime = detectionPanel.getTimeTextField();
                        newDetectionTime = detectionTime + emoStateInterval;
                        detectionPanel.setTimeTxtField(newDetectionTime);
                        long period1 = (long) (InteractivePanel.getEmoStateTimeInterval() * 1000);
                        if(intervalPeriod!=period1) {
                            timer.cancel();
                            updateDetectionTime(InteractivePanel.getEmoStateTimeInterval());
                        }
                    } else {
                        timer.cancel();
                        timer.purge();
                    }
                }
            }, 0, intervalPeriod);

        }

    }

    /**
     * Sets the Console Panel value for the class Property
     *
     * @param consolePanel the console panel on the server UI
     */
    public void setConsolePanel(ConsolePanel consolePanel) {
        this.consolePanel = consolePanel;
    }

    /**
     * Updates the message bean taking values from the UI
     */
    public void updateMessageBeanFromUI() {

        this.updateUpperfaceMetrics();
        this.updateLowerfaceMetrics();
        this.updatePerformanceMetrics();
        this.updateEyeStateMetric();
        this.updateClock();
    }

    /**
     * To update emotion bean
     */
    private void updateClock() {
        double detectionTime = detectionPanel.getTimeTextField();
        EndpointController.getInstance().updateClock(detectionTime);
    }

    /**
     * This method is updating the model attributes for eye state
     */

    private void updateEyeStateMetric() {
        String[] eyeStateMetric = {"Blink", "Wink Left", "Wink Right"};
        boolean eyeVal = false;
        if (detectionPanel.isActivated() && !detectionPanel.isResetChaecked()) {
            eyeVal = detectionPanel.isActivated();
            detectionPanel.setActivated(false);
        } else if (detectionPanel.isActivated() && detectionPanel.isResetChaecked()) {
            eyeVal = !runningEyeVal;
            runningEyeVal = eyeVal;
        } else {
            runningEyeVal = false;
        }

        for (String str : eyeStateMetric) {
            if (detectionPanel.getEyeStateSelectedItem().equalsIgnoreCase(str)) {

                switch (str) {
                    case "Blink":
                        EndpointController.getInstance().updateExpBlink(eyeVal);
                        break;
                    case "Wink Left":
                        EndpointController.getInstance().updateExpLeftWink(eyeVal);
                        break;
                    case "Wink Right":
                        EndpointController.getInstance().updateExpRightWink(eyeVal);
                        break;
                }

            } else if (str.equalsIgnoreCase("Blink")) {
                EndpointController.getInstance().updateExpBlink(false);
            } else if (str.equalsIgnoreCase("Wink Left")) {
                EndpointController.getInstance().updateExpLeftWink(false);
            } else if (str.equalsIgnoreCase("Wink Right")) {
                EndpointController.getInstance().updateExpRightWink(false);
            }
        }
    }

    /**
     * This method is updating the model attributes for performance metric
     */
    private void updatePerformanceMetrics() {
        String[] pfMetrics = {"Interest", "Engagement", "Stress", "Excitement",
                "Relaxation", "Focus"};
        for (String str : pfMetrics) {
            if (detectionPanel.getPfMetricSelectedItem().equalsIgnoreCase(str)) {

                switch (str) {
                    case "Interest":
                        EndpointController.getInstance().updateInterest(
                                detectionPanel.getPfMetricSelectedValue());
                        break;
                    case "Engagement":
                        EndpointController.getInstance().updateEngagement(
                                detectionPanel.getPfMetricSelectedValue());
                        break;
                    case "Stress":
                        EndpointController.getInstance().updateStress(
                                detectionPanel.getPfMetricSelectedValue());
                        break;
                    case "Excitement":
                        EndpointController.getInstance().updateExcitement(
                                detectionPanel.getPfMetricSelectedValue());
                        break;
                    case "Relaxation":
                        EndpointController.getInstance().updateRelaxation(
                                detectionPanel.getPfMetricSelectedValue());
                        break;
                    case "Focus":
                        EndpointController.getInstance().updateFocus(
                                detectionPanel.getPfMetricSelectedValue());
                        break;
                }

            } else if (str.equalsIgnoreCase("Interest")) {
                EndpointController.getInstance().updateInterest(0.00);
            } else if (str.equalsIgnoreCase("Engagement")) {
                EndpointController.getInstance().updateEngagement(0.00);
            } else if (str.equalsIgnoreCase("Stress")) {
                EndpointController.getInstance().updateStress(0.00);
            } else if (str.equalsIgnoreCase("Excitement")) {
                EndpointController.getInstance().updateExcitement(0.00);
            } else if (str.equalsIgnoreCase("Relaxation")) {
                EndpointController.getInstance().updateRelaxation(0.00);
            } else if (str.equalsIgnoreCase("Focus")) {
                EndpointController.getInstance().updateFocus(0.00);
            }

        }
    }

    /**
     * This method is updating the model attributes for lowerface
     */
    private void updateLowerfaceMetrics() {
        String[] lowerfaceMetrics = {"Smile", "Clench"};
        for (String str : lowerfaceMetrics) {
            if (detectionPanel.getLowerfaceSelectedItem().equalsIgnoreCase(str)) {

                switch (str) {
                    case "Smile":
                        EndpointController.getInstance().updateSmile(
                                detectionPanel.getLowerfaceSelectedValue());
                        break;
                    case "Clench":
                        EndpointController.getInstance().updateClench(
                                detectionPanel.getLowerfaceSelectedValue());
                        break;
                }

            } else if (str.equalsIgnoreCase("Smile")) {
                EndpointController.getInstance().updateSmile(0.00);
            } else if (str.equalsIgnoreCase("Clench")) {
                EndpointController.getInstance().updateClench(0.00);
            }
        }
    }

    /**
     * This method is updating the model attributes for upperface
     */
    private void updateUpperfaceMetrics() {
        String[] upperfaceMetrics = {"Raise Brow", "Furrow Brow", "Look Left", "Look Right",
                "Look Up", "Look Down"};
        for (String str : upperfaceMetrics) {
            if (detectionPanel.getUpperfaceSelectedItem().equalsIgnoreCase(str)) {

                switch (str) {
                    case "Raise Brow":
                        EndpointController.getInstance().updateEyeBrowRaise(
                                detectionPanel.getUpperfaceSelectedValue());
                        break;
                    case "Furrow Brow":
                        EndpointController.getInstance().updateEyeBrowFurrow(
                                detectionPanel.getUpperfaceSelectedValue());
                        break;
                    case "Look Left":
                        EndpointController.getInstance().updateExpLookingLeft(
                                detectionPanel.getUpperfaceSelectedValue());
                        break;
                    case "Look Right":
                        EndpointController.getInstance().updateExpLookingRight(
                                detectionPanel.getUpperfaceSelectedValue());
                        break;
                    case "Look Up":
                        EndpointController.getInstance().updateLookingUp(
                                detectionPanel.getUpperfaceSelectedValue());
                        break;
                    case "Look Down":
                        EndpointController.getInstance().updateLookingDown(
                                detectionPanel.getUpperfaceSelectedValue());
                        break;
                }

            } else if (str.equalsIgnoreCase("Raise Brow")) {
                EndpointController.getInstance().updateEyeBrowRaise(0.00);
            } else if (str.equalsIgnoreCase("Furrow Brow")) {
                EndpointController.getInstance().updateEyeBrowFurrow(0.00);
            } else if (str.equalsIgnoreCase("Look Left")) {
                EndpointController.getInstance().updateExpLookingLeft(0.00);
            } else if (str.equalsIgnoreCase("Look Right")) {
                EndpointController.getInstance().updateExpLookingRight(0.00);
            } else if (str.equalsIgnoreCase("Look Up")) {
                EndpointController.getInstance().updateLookingUp(0.00);
            } else if (str.equalsIgnoreCase("Look Down")) {
                EndpointController.getInstance().updateLookingDown(0.00);
            }
        }
    }

    /**
     * Inner class SingletonHolder to support lazy initialisation of class
     */
    private static class SingletonHolder {
        public static final UIController uiController = new UIController();
    }

}
