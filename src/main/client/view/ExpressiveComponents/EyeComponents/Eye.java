package main.client.view.ExpressiveComponents.EyeComponents;

import main.client.view.ExpressiveComponents.IExpressive;

import java.awt.*;
import java.awt.geom.Arc2D;

/**
 * Abstract class for eye, inherits from Arc2D
 *
 * @author Ejaz Saifudeen
 * @version 1.1
 */
public abstract class Eye extends Arc2D.Double implements IExpressive {

    protected static final int WIDTH = 23;
    protected static final int HEIGHT = 30;
    private static final int START = 90;
    private static final int EXTENT = -360;

    /**
     * Constructor that initializes the eye arc
     *
     * @param x Top left X position
     * @param y Top right Y position
     */
    public Eye(int x, int y) {
        super(x, y, WIDTH, HEIGHT, START, EXTENT, Arc2D.CHORD);
    }

    /**
     * Sets angle extent to zero if blink is true
     *
     * @param b should blink
     */
    public void blink(boolean b) {

        if (!b)
            return;

        //double newStart = START - (val * 180);
        //double newExtent = EXTENT + (val * 360);
        setAngleExtent(0);
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
        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Color getColor() {
        return Color.WHITE;
    }
}
