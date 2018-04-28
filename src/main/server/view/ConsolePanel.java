package main.server.view;

import main.model.ConsoleMessage;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.text.BadLocationException;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.acl.AclEntry;
import java.util.Date;
import java.util.Observable;
import java.util.Observer;

public class ConsolePanel extends JPanel implements Observer {

    private  JTextPane consoleTextPane;
    private ConsoleMessage consoleMessage;
    public ConsolePanel(ConsoleMessage consoleMessage){

        this.consoleMessage = consoleMessage;
        this.setBorder(new TitledBorder("Console"));

        consoleTextPane = new JTextPane();
        consoleTextPane.setEditable(false);
        consoleTextPane.setForeground(Color.WHITE);
        consoleTextPane.setBackground(Color.DARK_GRAY);
        JScrollPane scrollPane = new JScrollPane(consoleTextPane);
        scrollPane.setPreferredSize(new Dimension(0, 60));

        JButton btnClearLog = new JButton("Clear Log");
        btnClearLog.addActionListener(new buttonListener());


        setLayout(new BorderLayout(0, 10));
        add(scrollPane, BorderLayout.CENTER);
        add(btnClearLog, BorderLayout.SOUTH);
    }

    /**
     * Appends a new message to the top of server console
     *
     * @param message message to be set on the console
     */
    public void setMessage(String message) throws BadLocationException {

            consoleTextPane.setContentType("text/html");
            StyledDocument doc = (StyledDocument) consoleTextPane.getDocument();
            SimpleAttributeSet keyWord = new SimpleAttributeSet();
            StyleConstants.setForeground(keyWord, Color.GREEN);
            StyleConstants.setFontSize(keyWord, 13);

            doc.insertString(0, new Date() + "Message: " + message + "\n", keyWord);
            consoleTextPane.setCaretPosition(0);

    }

    /**
     * Appends a new error message to the top of server console
     *
     * @param errorMsg to be set on the console
     */
    public void setErrorMessage(String errorMsg) throws BadLocationException {

        consoleTextPane.setContentType("text/html");
        StyledDocument doc = (StyledDocument) consoleTextPane.getDocument();
        SimpleAttributeSet keyWord = new SimpleAttributeSet();
        StyleConstants.setForeground(keyWord, Color.RED);
        StyleConstants.setFontSize(keyWord, 13);

        doc.insertString(0, new Date() + "Message: " + errorMsg + "\n", keyWord);
        consoleTextPane.setCaretPosition(0);

    }

    @Override
    public void update(Observable o, Object arg) {
        try {
            setMessage(consoleMessage.getMessage());
        } catch (BadLocationException e) {
            e.printStackTrace();
        }
    }

    private class buttonListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent arg0) {

            consoleTextPane.setText("");
        }
    }
}
