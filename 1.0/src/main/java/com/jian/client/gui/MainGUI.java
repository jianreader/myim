package com.jian.client.gui;

import javax.swing.*;
import java.awt.*;
import java.util.EventListener;

/**
 * Created by Patrick on 8/9/2015.
 */
public class MainGUI {

    private static void createAndShowGUI() {
        //Create and set up the window.
        JFrame frame = new JFrame("MyIm");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Create the menu bar.  Make it have a green background.
        JMenuBar menuBar = new JMenuBar();
        menuBar.setOpaque(true);
        menuBar.setBackground(new Color(70, 94, 165));
        menuBar.setPreferredSize(new Dimension(250, 30));

        JMenu fileMenu = new JMenu("File");
        JMenuItem addFriendMenuItem = new JMenuItem("Add Friend ...");

        //Create a yellow label to put in the content pane.
        JLabel yellowLabel = new JLabel();
        yellowLabel.setOpaque(true);
        yellowLabel.setBackground(new Color(244, 248, 239));
        yellowLabel.setPreferredSize(new Dimension(200, 180));

        //Set the menu bar and add the label to the content pane.
        frame.setJMenuBar(menuBar);
        frame.getContentPane().add(yellowLabel, BorderLayout.CENTER);

        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        //Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }
}
