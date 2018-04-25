package main.client.view.ExpressiveComponents.LowerFaceComponents;

import main.client.view.ExpressiveComponents.IExpressive;

import java.awt.*;
import java.awt.geom.Arc2D;

/**
 * Class for mouth clench, inherits from Arc2D
 *
 * @author Ejaz Saifudeen
 * @version 1.1
 */
public class Clench extends Arc2D.Double implements IExpressive {

    private static final int X = 106;
    private static final int Y = 245;
    private static final int WIDTH = 100;
    private static final int HEIGHT = 140;
    private static final int START = 70;
    private static final int EXTENT = 40;

    /**
     * Constructor that initializes the clench arc
     */
    public Clench() {
        super(X, Y, WIDTH, HEIGHT, 0, 0, Arc2D.CHORD);
    }

    /**
     * Clenches mouth arc based on received value
     *
     * @param val double 0-1
     */
    public void set(double val) {

        if (val <= 0 || val > 1)
            return;

        //double newY = Y - (25*val);
        double NewStart = START - (30 * val);
        double NewExtent = EXTENT + (60 * val);
        //setFrame(X, newY, WIDTH, HEIGHT);
        setAngleStart(NewStart);
        setAngleExtent(NewExtent);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void reset(int x, int y) {
        setFrame(x + X, y + Y, WIDTH, HEIGHT);
        setAngleStart(0);
        setAngleExtent(0);
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
