package main.client.view.ExpressiveComponents.LowerFaceComponents;

import main.client.view.ExpressiveComponents.IExpressive;

import java.awt.*;
import java.awt.geom.Rectangle2D;

/**
 * Class for mouth normal, inherits from Rectangle2D
 *
 * @author Ejaz Saifudeen
 * @version 1.1
 */
public class Mouth extends Rectangle2D.Double implements IExpressive {

    private static final int X = 140;
    private static final int Y = 240;
    private static final int WIDTH = 30;
    private static final int HEIGHT = 3;

    /**
     * Constructor that initializes the mouth rectangle
     */
    public Mouth() {
        super(X, Y, WIDTH, HEIGHT);
    }

    /**
     * Hides mouth if val lies between 0(exclusive) - 1(inclusive)
     *
     * @param val double 0-1
     */
    public void set(double val) {
        if (val > 0 && val <= 1)
            setFrame(X, Y, 0, 0);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void reset(int x, int y) {
        setFrame(x + X, y + Y, WIDTH, HEIGHT);
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
