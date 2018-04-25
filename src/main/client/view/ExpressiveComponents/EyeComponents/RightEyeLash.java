package main.client.view.ExpressiveComponents.EyeComponents;

/**
 * Class for right eye lash, inherits from EyeLash
 *
 * @author Ejaz Saifudeen
 * @version 1.1
 */
public class RightEyeLash extends EyeLash {

    private static final int X = 97;
    private static final int Y = 172;

    /**
     * Constructor that calls super constructor with relevant
     * X, Y
     */
    public RightEyeLash() {
        super(X, Y);
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
