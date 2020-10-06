package com.mycompany.serverproject;

import java.net.ServerSocket;
import java.net.Socket;

public class ServerConnectionManager 
{
    public void startListening()
    {
        try 
        {
            ServerSocket serverSocket = new ServerSocket(6789);
            while(true)
            {
                System.out.println("1 Server in attesa");
                Socket socket = serverSocket.accept();
                System.out.println("3 Server socket" + socket);
                Server server = new Server(socket);
                server.start();
            }            
        }
        catch (Exception e) 
        {
            System.out.println(e.getMessage());
            System.out.println("Errore durante l'istanza del server");
            System.exit(1);
        }
    }
}
