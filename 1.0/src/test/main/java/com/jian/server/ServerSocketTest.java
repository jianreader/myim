package com.jian.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by patrick on 8/4/2015.
 */
public class ServerSocketTest  {
    public static void main(String[] args)throws IOException {
        ServerSocket serverSocket = new ServerSocket(2013);
        try {
            while (true) {

                Socket client = serverSocket.accept();

                System.out.println("get client");
                ServerThread thread = new ServerThread(client);



                thread.start();
            }
        }catch (IOException e) {
        }finally {
            serverSocket.close();
        }
    }
}
