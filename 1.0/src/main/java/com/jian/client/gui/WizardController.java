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
        ImMainWindow mainWindow = new ImMainWindow(account, _jframe);
        mainWindow.setupGUI();
    }

}
