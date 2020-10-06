package com.mycompany.serverproject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class Server extends Thread
{
    private ServerSocket serverSocket;
    private Socket socket;
    private String stringaRicevuta;
    private String stringaModificata;
    private BufferedReader socketIn;
    private DataOutputStream socketOut;
    
    public Server(Socket s)
    {
        socket = s;
    }

    @Override
    public void run() 
    {
        try 
        {
            comunica();
        } 
        catch (Exception e) 
        {
            e.printStackTrace(System.out);
        }
    }
    
    public void comunica() throws Exception
    {
        socketIn = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        socketOut = new DataOutputStream(socket.getOutputStream());
        
        while(true)
        {
            stringaRicevuta = socketIn.readLine();
            if(stringaRicevuta == null || stringaRicevuta.toUpperCase().equals("FINE"))
            {
                socketOut.writeBytes(stringaRicevuta + "(server in chiusura)\n");
                System.out.println("Server in chiusura");
                break;
            }
            else
            {
                stringaModificata = stringaRicevuta.toUpperCase();
                socketOut.writeBytes(stringaModificata + "\n");
                System.out.println("6 Echo sul server: " + stringaModificata);
            }
        }
        socketIn.close();
        socketOut.close();
        System.out.println("9 Chiusura socket " + socket);
        socket.close();
    }
}
