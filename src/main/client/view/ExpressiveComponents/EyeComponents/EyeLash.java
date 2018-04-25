package main.client.view.ExpressiveComponents.EyeComponents;

import main.client.view.ExpressiveComponents.IExpressive;

import java.awt.*;
import java.awt.geom.Arc2D;

/**
 * Abstract class for lash, inherits from Arc2D
 *
 * @author Ejaz Saifudeen
 * @version 1.1
 */
public abstract class EyeLash extends Arc2D.Double implements IExpressive {

    protected static final int WIDTH = 23;
    protected static final int HEIGHT = 30;
    private static final int START = 90;
    private static final int EXTENT = -360;

    /**
     * Constructor that initializes the eye lash arc
     *
     * @param x Top left X position
     * @param y Top right Y position
     */
    public EyeLash(int x, int y) {
        super(x, y, WIDTH, HEIGHT, START, EXTENT, Arc2D.OPEN);
    }

    public void blink(boolean b) {

        if (!b)
            return;

        //double newStart = START - ((val/2) * 180);
        //double newExtent = EXTENT + ((val/2) * 360);
        double newStart = 0;
        double newExtent = -180;
        setAngleStart(newStart);
        setAngleExtent(newExtent);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void reset(int x, int y) {
        setAngleStart(START);
        setAngleExtent(EXTENT);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean getFill() {
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Color getColor() {
        return Color.BLACK;
    }

}
