package main.client.view.ExpressiveComponents.UpperFaceComponents;

import main.client.view.ExpressiveComponents.IExpressive;

import java.awt.*;
import java.awt.geom.Arc2D;

/**
 * Abstract class for eye ball, inherits from Arc2D
 *
 * @author Ejaz Saifudeen
 * @version 1.1
 */
public abstract class EyeBall extends Arc2D.Double implements IExpressive {

    protected static final int WIDTH = 12;
    protected static final int HEIGHT = 15;
    private static final int START = 90;
    private static final int EXTENT = -360;

    /**
     * Constructor that initializes the eye ball arc
     *
     * @param x Top left X position
     * @param y Top right Y position
     */
    public EyeBall(int x, int y) {
        super(x, y, WIDTH, HEIGHT, START, EXTENT, Arc2D.CHORD);
    }

    public void blink(boolean b) {

        if (!b)
            return;

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
        return Color.BLACK;
    }
}
