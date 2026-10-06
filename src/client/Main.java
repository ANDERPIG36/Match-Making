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
        Player player = new Player();

        Graphics graphics = new Graphics();

        try {
            Socket s = new Socket("127.0.0.1",50000);//si connette al server

        } catch(IOException ex) {
            graphics.label.setText("Errore di connessione!");
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void start() throws IOException {

        //dati da poi rendere classe player
        String nickname = "NomeProvvisorio";
        int livello = 7;

        // Invio dei dati al server
        out.println(nickname);
        out.println(livello);

        // Aspetta la risposta del server
        String risposta = in.readLine();

        System.out.println("Risposta del server: " + risposta);
    }
}