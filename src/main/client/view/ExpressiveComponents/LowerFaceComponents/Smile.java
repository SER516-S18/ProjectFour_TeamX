package main.client.view.ExpressiveComponents.LowerFaceComponents;

import main.client.view.ExpressiveComponents.IExpressive;

import java.awt.*;
import java.awt.geom.Arc2D;

/**
 * Class for mouth smile, inherits from Arc2D
 *
 * @author Ejaz Saifudeen
 * @version 1.1
 */
public class Smile extends Arc2D.Double implements IExpressive {

    private static final int X = 106;
    private static final int Y = 105;
    private static final int WIDTH = 100;
    private static final int HEIGHT = 140;
    private static final int START = 250;
    private static final int EXTENT = 40;

    /**
     * Constructor that initializes the smile arc
     */
    public Smile() {
        super(X, Y, WIDTH, HEIGHT, 0, 0, Arc2D.CHORD);
    }

    /**
     * Smiles mouth arc based on received value
     *
     * @param val double 0-1
     */
    public void set(double val) {

        if (val <= 0 || val > 1)
            return;

        double newY = getY() + (20 * val);
        double NewStart = START - (30 * val);
        double NewExtent = EXTENT + (60 * val);
        setFrame(getX(), newY, WIDTH, HEIGHT);
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
