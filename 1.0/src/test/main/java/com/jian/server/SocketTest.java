package com.jian.server;

import org.junit.Test;

import java.io.*;
import java.net.Socket;

/**
 * Created by patrick on 8/4/2015.
 */
public class SocketTest {


    public static void main(String[] args)
    {
        try {
            final Socket socket =new Socket("localhost",2013);
            //socket.setSoTimeout(60000);

            PrintWriter printWriter =new PrintWriter(socket.getOutputStream(),true);

                printWriter.println("login\n001:pw1");
                printWriter.flush();




            //printWriter.close();

                new Thread() {
                    public void run() {
                        ObjectInputStream inStream = null;
                        try {
                            inStream = new ObjectInputStream(socket.getInputStream());

                        Account account = (Account) inStream.readObject();
                        System.out.println(" get !" + account.getName());
                        } catch (IOException e) {
                            e.printStackTrace();
                        } catch (ClassNotFoundException e) {
                            e.printStackTrace();
                        }
                    };
                }.start();



        }catch (Exception e) {
            System.out.println("Exception:" + e);
        }

    }

}
