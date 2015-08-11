package com.jian.client.gui;

import com.jian.server.Account;
import com.jian.server.LoginException;
import com.jian.server.LoginOperation;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;

/**
 * Created by Patrick on 8/9/2015.
 */
public class LoginPanel extends JPanel {
    private JButton _loginButton = new JButton("Login");
    private JTextField _uiField = new JTextField(14);
    private JLabel _uiLabel = new JLabel("User ID: ");
    private JLabel _pwLabel = new JLabel("Password: ");
    private JPasswordField _passwordField = new JPasswordField(14);
    private static final String LOGIN_IMAGE = "login.png";
    private Account _account ;
    private WizardController _controller;

    public LoginPanel(WizardController controller)
    {
        _controller = controller;
        createInitGUI();
    }


    public void createInitGUI()
    {
        setPreferredSize(new Dimension(200, 600));

        GridBagLayout layout = new GridBagLayout();
        setLayout(layout);
        GridBagConstraints gbc = new GridBagConstraints();

        try {
            InputStream is = this.getClass().getClassLoader().getResourceAsStream(LOGIN_IMAGE);
            BufferedImage myPicture = ImageIO.read(is);
            JLabel picLabel = new JLabel(new ImageIcon(myPicture));
            add(picLabel);
            gbc.fill = GridBagConstraints.NONE;
            gbc.gridx = 0;
            gbc.gridy = 0;
            gbc.ipady = 8;
            add(picLabel, gbc);
        } catch (IOException ex) {
            // handle exception...
        }

        gbc.fill = GridBagConstraints.NONE;
        gbc.gridx = 0;
        gbc.gridy = 1;
        add(_uiLabel, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        add(_uiField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 6;
        add(_pwLabel, gbc);

        gbc.gridx = 0;
        gbc.gridy = 7;
        add(_passwordField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 8;
        add(new JLabel(" "), gbc);

        gbc.gridx = 0;
        gbc.gridy = 9;
        add(_loginButton, gbc);

        for(int i = 1 ; i < 10; ++i)
        {
            gbc.gridx = 0;
            gbc.gridy = 9 + i;
            add(new JLabel(" "), gbc);
        }

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
