package main.server.view.components;

import javax.swing.*;

/**
 * Wrapper for JSpinner
 */
public class XSpinner extends JSpinner {

    public XSpinner(double start, double maximum, double step){
        setModel(new SpinnerNumberModel(start, start, maximum, step));
        setEditor(new JSpinner.NumberEditor(this, "0.00"));
    }
}
