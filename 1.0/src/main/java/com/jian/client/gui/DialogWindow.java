package com.jian.client.gui;

import com.jian.server.Account;
import com.jian.server.Friend;

import javax.swing.*;
import java.awt.*;

/**
 * Created by patrick on 8/12/2015.
 */
public class DialogWindow implements Runnable{
    private Friend _friend;
    private Account _account;
    private JFrame _frame;

    public DialogWindow(Friend friend, Account account)
    {
        _friend = friend;
        _account = account;
    }

    public void createAndShowGUI()
    {
        String fname = _friend.getName();
        _frame = new JFrame("Chat with " + fname);
        _frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JTextPane showPane = new JTextPane();
        showPane.setPreferredSize(new Dimension(400, 300));
        JScrollPane showScrollPane = new JScrollPane(showPane);
        JTextPane inputPane = new JTextPane();
        inputPane.setPreferredSize(new Dimension(400, 100));
        JScrollPane inputScrollPane = new JScrollPane(inputPane);

        JLabel headLabel = new JLabel(_account.getName());
        JRadioButton textButton = new JRadioButton("A");
        JRadioButton fileButton = new JRadioButton("F");
        ButtonGroup editButtons = new ButtonGroup();
        editButtons.add(textButton);
        editButtons.add(fileButton);
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(textButton);
        buttonPanel.add(fileButton);

        JMenuBar menuBar = new JMenuBar();
        menuBar.setOpaque(true);
        menuBar.setBackground(new Color(77, 156, 231));
        menuBar.setPreferredSize(new Dimension(200, 30));
        JMenu fileMenu = new JMenu("File");
        JMenuItem addFriendMenuItem = new JMenuItem("Add Friend ...");
        menuBar.add(fileMenu);
        fileMenu.add(addFriendMenuItem);
        //Set the menu bar and add the label to the content pane.
        _frame.setJMenuBar(menuBar);


        GridBagLayout layout = new GridBagLayout();
        _frame.setLayout(layout);
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.ipady = 20;
        gbc.ipadx = 20;
        gbc.gridx = 0;
        gbc.gridy = 1;
        _frame.add(headLabel, gbc);

        gbc.ipady = 20;
        gbc.ipadx = 20;
        gbc.gridx = 0;
        gbc.gridy = 2;
        _frame.add(showScrollPane, gbc);

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.ipady = 5;
        gbc.ipadx = 5;
        gbc.gridx = 0;
        gbc.gridy = 3;
        _frame.add(buttonPanel, gbc);




        gbc.ipady = 5;
        gbc.ipadx = 5;
        gbc.gridx = 0;
        gbc.gridy = 4;
        _frame.add(inputScrollPane, gbc);

        //_frame.setPreferredSize(new Dimension(400, 600));
        _frame.pack();
        _frame.setVisible(true);
    }

    @Override
    public void run() {
        createAndShowGUI();
    }
}
