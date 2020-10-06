package com.mycompany.serverproject;

public class ServerMain 
{
    public static void main(String[] args)
    {
        ServerConnectionManager serverConnectionManager = new ServerConnectionManager();
        serverConnectionManager.startListening();
    }
}
