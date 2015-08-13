package com.jian.client.gui;

import com.jian.server.Account;
import com.jian.server.Friend;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by patrick on 8/9/2015.
 */
public class ImMainWindow {

    private Account _account ;
    private Map<String, Friend> _friends ;
    private JFrame _jframe;
    private ImageIcon _onlineIcon ;
    private static final String ONLINE_ICON = "online.png";

    public ImMainWindow( Account account, JFrame jframe)
    {
        _account = account;
        _friends = _account.getFriendMap();
        _jframe = jframe;
    }

    public void setupGUI()
    {
        try {
            InputStream is = this.getClass().getClassLoader().getResourceAsStream(ONLINE_ICON);
            BufferedImage myPicture = ImageIO.read(is);
            _onlineIcon = new ImageIcon(myPicture);
        }
        catch (Exception e)
        {

        }

        _jframe.getContentPane().removeAll();
        //Create the menu bar.  Make it have a green background.
        JMenuBar menuBar = new JMenuBar();
        menuBar.setOpaque(true);
        menuBar.setBackground(new Color(77, 156, 231));
        menuBar.setPreferredSize(new Dimension(200, 30));

        JMenu fileMenu = new JMenu("File");
        JMenuItem addFriendMenuItem = new JMenuItem("Add Friend ...");
        menuBar.add(fileMenu);
        fileMenu.add(addFriendMenuItem);
        //Set the menu bar and add the label to the content pane.
        _jframe.setJMenuBar(menuBar);

        JList jlist = new JList(_friends.keySet().toArray());
        jlist.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        jlist.setLayoutOrientation(JList.VERTICAL);
        jlist.setFixedCellHeight(30);
        jlist.setCellRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                JLabel label = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                label.setIcon(_onlineIcon);
                return label;
            }
        });

        jlist.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                JList list = (JList) evt.getSource();
                if (evt.getClickCount() == 2) {

                    // Double-click detected
                    int index = list.locationToIndex(evt.getPoint());
                    String id = (String)list.getModel().getElementAt(index);
                    Friend friend = _friends.get(id);
                    javax.swing.SwingUtilities.invokeLater(new DialogWindow(friend, _account));
                }
            }
        });

        JScrollPane scrollPane = new JScrollPane(jlist);
        scrollPane.setPreferredSize(new Dimension(200, 400));
        _jframe.getContentPane().add(scrollPane, BorderLayout.CENTER);

        //Display the window.
        _jframe.pack();
        _jframe.setVisible(true);
    }
}
