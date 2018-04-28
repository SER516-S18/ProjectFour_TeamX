package main.server.controller;

public class UIController {

    public static UIController getInstance() {

        return SingletonHolder.uIController;
    }

    public void sendInIntervals(double interval)
    {
        EndpointController.getInstance().sendInIntervals(interval);
    }

    public void sendOnce()
    {
        EndpointController.getInstance().sendOnce();
    }

    public void stop()
    {
        EndpointController.getInstance().stop();
    }
    /**
     * This will support lazy initialisation of UIController
     */
    private static class SingletonHolder {
        public static final UIController
                uIController = new UIController();
    }
}
