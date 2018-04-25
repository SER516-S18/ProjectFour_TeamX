package main.client.view.ExpressiveComponents.UpperFaceComponents;

import main.client.view.ExpressiveComponents.IExpressive;

import java.awt.*;
import java.awt.geom.Arc2D;

/**
 * Abstract class for eye brow, inherits from Arc2D
 *
 * @author Ejaz Saifudeen
 * @version 1.1
 */
public abstract class EyeBrow extends Arc2D.Double implements IExpressive {

    protected static final int WIDTH = 50;
    protected static final int HEIGHT = 60;

    /**
     * Constructor that initializes the eye brow arc
     *
     * @param x  top left X position
     * @param y  top left Y position
     * @param st angle start position
     * @param ex angle extent
     */
    public EyeBrow(int x, int y, int st, int ex) {
        super(x, y, WIDTH, HEIGHT, st, ex, Arc2D.OPEN);
    }

    /**
     * Raises the eyebrow based of received value
     *
     * @param val double 0-1
     */
    public void raise(double val) {

        if (val <= 0 || val > 1)
            return;

        double newY = getY() - (val * 10);
        setFrame(getX(), newY, WIDTH, HEIGHT);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void reset(int x, int y) {
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
