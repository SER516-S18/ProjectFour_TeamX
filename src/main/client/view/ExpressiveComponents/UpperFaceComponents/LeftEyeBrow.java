package main.client.view.ExpressiveComponents.UpperFaceComponents;

/**
 * Class for left eye brow, inherits from EyeBrow
 *
 * @author Ejaz Saifudeen
 * @version 1.1
 */
public class LeftEyeBrow extends EyeBrow {

    private static final int X = 175;
    private static final int Y = 163;
    private static final int START = 110;
    private static final int EXTENT = -50;

    /**
     * Constructor that calls super constructor with relevant
     * X, Y, Start and Extent values
     */
    public LeftEyeBrow() {
        super(X, Y, START, EXTENT);
    }

    /**
     * Furrows the eyebrow based of received value
     *
     * @param val double 0-1
     */
    public void furrow(double val) {
        if (val <= 0 || val > 1)
            return;

        double newStart = START + (val * 20);
        double newY = getY() + (val * 4);
        setFrame(getX(), newY, WIDTH, HEIGHT);
        setAngleStart(newStart);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void reset(int x, int y) {
        setFrame(x + X, y + Y, WIDTH, HEIGHT);
    }
}
