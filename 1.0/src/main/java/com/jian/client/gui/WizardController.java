package com.jian.client.gui;

import com.jian.server.Account;

import javax.swing.*;
import java.awt.*;

/**
 * Created by patrick on 8/9/2015.
 */
public class WizardController {
    private JFrame _jframe ;
    public WizardController(JFrame jframe)
    {
        _jframe = jframe;
    }

    public void setupLoginPage()
    {
        _jframe.getContentPane().removeAll();
        _jframe.getContentPane().add(new LoginPanel(this), BorderLayout.CENTER);
        _jframe.pack();
        _jframe.setVisible(true);
    }

    public void setupFriendPage(Account account)
    {
        _jframe.getContentPane().removeAll();
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
        _jframe.setJMenuBar(menuBar);
        _jframe.getContentPane().add(yellowLabel, BorderLayout.CENTER);

        //Display the window.
        _jframe.pack();
        _jframe.setVisible(true);
    }

}
