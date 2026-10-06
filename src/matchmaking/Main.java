import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        PrintWriter out;
        BufferedReader in;

        try {
            socketAccettazione = new ServerSocket(50000);//apre sulla porta 50000    
            while(true){
                Socket socketServer = socketAccettazione.accept();
                //attende una connessione
                ClientHandler handler = new ClientHandler(socket); //crea un handler per il client appena connesso
                Thread thread = new Thread(handler); //crea il thread del handler
                thread.start(); //avvia il thread
            }
        } catch(IOException ex) {
            System.out.println("Errore: "+ex.getMessage());
        }
    }   
}