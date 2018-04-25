package main.client.view.ExpressiveComponents.UpperFaceComponents;

/**
 * Class for right eye ball, inherits from EyeBall
 *
 * @author Ejaz Saifudeen
 * @version 1.1
 */
public class RightEyeBall extends EyeBall {

    private static final int X = 102;
    private static final int Y = 181;

    /**
     * Constructor that calls super constructor with relevant
     * X, Y
     */
    public RightEyeBall() {
        super(X, Y);
    }

    /**
     * Modifies X position based on received val to position
     * eyeball to the left
     *
     * @param val double 0-1
     */
    public void lookLeft(double val) {

        if (val <= 0 || val > 1)
            return;

        double newX = getX() + (5 * val);
        setFrame(newX, getY(), WIDTH, HEIGHT);
    }

    /**
     * Modifies X position based on received val to position
     * eyeball to the right
     *
     * @param val double 0-1
     */
    public void lookRight(double val) {

        if (val <= 0 || val > 1)
            return;

        double newX = getX() - (5 * val);
        setFrame(newX, getY(), WIDTH, HEIGHT);
    }

    /**
     * Modifies Y position based on received val to position
     * eyeball to the top
     *
     * @param val double 0-1
     */
    public void lookUp(double val) {

        if (val <= 0 || val > 1)
            return;

        double newY = getY() - (8 * val);
        setFrame(getX(), newY, WIDTH, HEIGHT);
    }

    /**
     * Modifies Y position based on received val to position
     * eyeball to the bottom
     *
     * @param val double 0-1
     */
    public void lookDown(double val) {

        if (val <= 0 || val > 1)
            return;

        double newY = getY() + (6 * val);
        setFrame(getX(), newY, WIDTH, HEIGHT);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void reset(int x, int y) {
        setFrame(x + X, y + Y, WIDTH, HEIGHT);
        super.reset(x, y);
    }

}
