package com.jian.server;

import com.jian.protocol.ImProtocol;

import java.io.*;
import java.net.Socket;

/**
 * Created by patrick on 8/3/2015.
 */
public class ServerThread extends Thread {

    private Socket _clientSocket = null;

    public ServerThread(Socket socket) throws IOException {
        super("ServerThread");
        _clientSocket = socket;
    }

    public void run()  {

        System.out.println("server running ...");
        try {

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(_clientSocket.getInputStream()));

            PrintWriter printWriter = new PrintWriter(_clientSocket.getOutputStream(), true);
            String line = bufferedReader.readLine();
            switch(line)
            {
                case ImProtocol.LOGIN :
                    try {
                        line = bufferedReader.readLine();
                        LoginOperation login = new LoginOperation(line);
                        Account account = login.Login();
                        //printWriter.println("login success");
                        ObjectOutputStream objectOutputStream = new ObjectOutputStream(_clientSocket.getOutputStream());
                        objectOutputStream.writeObject(account);
                        objectOutputStream.flush();
                    }
                    catch (Exception e)
                    {
                         e.printStackTrace();
                    }
            }

           /* while (!line.equals("bye")) {

                line = bufferedReader.readLine();
                if(line)

            }
            printWriter.println("bye, Client(" + getName() + ")!");

            System.out.println("Client(" + getName() + ") exit!");*/
            printWriter.close();
            bufferedReader.close();
            _clientSocket.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
