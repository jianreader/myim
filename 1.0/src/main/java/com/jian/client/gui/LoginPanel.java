package com.jian.client.gui;

import com.jian.server.Account;
import com.jian.server.LoginException;
import com.jian.server.LoginOperation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.SQLException;

/**
 * Created by Patrick on 8/9/2015.
 */
public class LoginPanel extends JPanel {
    private JButton _loginButton = new JButton("Login");
    private JTextField _uiField = new JTextField(14);
    private JPasswordField _passwordField = new JPasswordField(14);
    private Account _account ;
    private WizardController _controller;

    public LoginPanel(WizardController controller)
    {
        _controller = controller;
        createInitGUI();
    }


    public void createInitGUI()
    {
        add(_uiField, BorderLayout.PAGE_START);
        add(_passwordField, BorderLayout.CENTER);
        add(_loginButton, BorderLayout.PAGE_END);

        _loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LoginOperation operation = new LoginOperation(_uiField.getText(), new String(_passwordField.getPassword()));
                try {
                    _account = operation.Login();
                } catch (Exception e1) {
                    JOptionPane.showMessageDialog(null, "Wrong username or password!", "Login Fails!", JOptionPane.INFORMATION_MESSAGE);
                    return;
                }
                _controller.setupFriendPage(_account);
            }
        });
    }

}
