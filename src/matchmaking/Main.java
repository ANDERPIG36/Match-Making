package matchmaking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.ServerSocket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import java.awt.*;
import common.*;

public class Main {
    public static void main(String[] args) {

        Settings settings = new Settings();
        Matchmaker matchmaker = new Matchmaker();

        try (ServerSocket socketAccettazione = new ServerSocket(settings.getServerSocket())) {
            System.out.println("Server avviato sulla porta " + settings.getServerSocket());
            while(true){
                Socket socketServer = socketAccettazione.accept();
                System.out.println("Nuovo giocatore connesso");
                
                // Passiamo il socket e il matchmaker al handler
                ClientHandler handler = new ClientHandler(socketServer, matchmaker); 
                Thread thread = new Thread(handler); 
                thread.start(); 
            }
        } catch(IOException ex) {
            System.out.println("Errore server: " + ex.getMessage());
        }
    }
}