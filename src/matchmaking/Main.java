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

        Graphics graphics = new Graphics();

        try {
            graphics.label.setText("Apertura connessione...");
            socketAccettazione = new ServerSocket(50000);
            graphics.label.setText("Connessione pronta...");    
            Socket socketServer = socketAccettazione.accept();
            //attende la connessione del client
            graphics.label.setText("Connesso:"+s.getInetAddress().toString());
        } catch(IOException ex) {
            graphics.label.setText("Errore di connessione!");
            System.out.println("Errore: "+e.getMessage());
        }
    }   
}