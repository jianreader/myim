package com.jian.server;

import com.jian.protocol.ImProtocol;

import java.io.*;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by patrick on 8/6/2015.
 */
public class UdpServerThread {

    protected DatagramSocket _serverSocket = null;
    protected BufferedReader in = null;
    private final static Logger _log = Logger.getLogger(UdpServerThread.class.getName());


    public UdpServerThread() throws IOException {
        _serverSocket = new DatagramSocket(4445);
    }


    public void run()
    {
        try {
            byte[] receiveData = new byte[1024];
            //byte[] sendData = new byte[1024];
            // receive request
            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
            _serverSocket.receive(receivePacket);
            String recMessage = new String( receivePacket.getData());
            _log.log(Level.INFO, "Recived Message: " + recMessage);

            String sendMessage = process(recMessage);
            if(sendMessage == null)
            {
                return;
            }

            // send the response to the client at "address" and "port"
            InetAddress IPAddress = receivePacket.getAddress();
            int port = receivePacket.getPort();
            byte[] sendData = sendMessage.getBytes();
            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, port);
            _serverSocket.send(sendPacket);
            _log.log(Level.INFO, "Send Message: " + sendMessage);
        } catch (IOException e) {
            e.printStackTrace();

        }
    }

    public String process(String recMessage)
    {
        String[] array = recMessage.split(":", 2);
        switch(array[0]) {
            case ImProtocol.LOGIN:
                try
                {
                    LoginOperation login = new LoginOperation(array[1]);
                    Account account = login.Login();
                    ByteArrayOutputStream bo = new ByteArrayOutputStream();
                    ObjectOutputStream so = new ObjectOutputStream(bo);
                    so.writeObject(account);
                    so.flush();
                    return ImProtocol.LOGIN_SUCCESS + ":"  + bo.toString();
                }
                catch (LoginException | IOException | SQLException e)
                {
                    _log.log(Level.INFO, "Login failed.");
                    return ImProtocol.LOGIN_FAIL;
                }


            case ImProtocol.SEARCH_PERSON:
                try
                {
                    DBService dbService = new DBServiceImpl();
                    Account person = dbService.getAccount(array[1]);
                    Friend friend = new Friend(person.getUid(), person.getName(), person.getAddress(), person.getPort(), person.isOnline());
                    ByteArrayOutputStream bo = new ByteArrayOutputStream();
                    ObjectOutputStream so = new ObjectOutputStream(bo);
                    so.writeObject(friend);
                    so.flush();
                    return ImProtocol.SEARCH_SUCCESS + ":"  + bo.toString();

                } catch (SQLException | IOException e) {
                    _log.log(Level.SEVERE, "Search failed.");
                    return ImProtocol.SEARCH_FAIL;
                }


            case ImProtocol.SEND_MESSAGE:
                try
                {
                    String[] sendto = array[1].split(":", 2);

                }
                catch(Exception e)
                {

                }
        }
        return null;
    }

}
