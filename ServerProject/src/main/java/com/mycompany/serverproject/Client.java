package com.mycompany.serverproject;
import java.net.*;
import java.io.*;
public class Client {
    //Variabili
    String nomeServer= "nomeServer";
    int portaServer = 6789;
    BufferedReader in;
    DataOutputStream out;
    BufferedReader tastiera;
    String stringaUtente;
    String stringaRicevutaDalServer;
    Socket mioSocket;
    
    //Metodi
    public Socket connetti() {
        System.out.println("2. Client partito in esecuzione");
        try {
            tastiera = new BufferedReader(new InputStreamReader(System.in));
            mioSocket = new Socket(nomeServer, portaServer);
            out = new DataOutputStream(mioSocket.getOutputStream());
            in = new BufferedReader(new InputStreamReader(mioSocket.getInputStream()));
            
        } catch (UnknownHostException e) {
            System.err.println("Host sconosciuto");
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Errore durante la connessione");
            System.exit(1);
        }
        return mioSocket;
    }
    
    public void comunica() {
        for(;;) {
            try {
                System.out.println("4. Utente, inserisci la stringa da trasmettere al server: ");
                stringaUtente = tastiera.readLine();
                System.out.println("5. Invio la stringa al server, attendo...");
                out.writeBytes(stringaUtente + '\n');
                stringaRicevutaDalServer = in.readLine();
                System.out.println("7. Risposta dal server " + '\n' + stringaRicevutaDalServer);
                if (stringaUtente.equals("FINE")) {
                    System.out.println("8. Client: termina elaborazione e chiude la connessione");
                    mioSocket.close();
                    break;
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
                System.out.println("Errore durante la comunicazione col server");
                System.exit(1);
            }
        }
    }
}
