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
        WizardController controller = new WizardController(frame);
        controller.setupLoginPage();


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
