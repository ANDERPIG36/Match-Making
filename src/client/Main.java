package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import java.awt.*;
import common.*;

public class Main {
    
    private Graphics graphics;

    public void main(String[] args) {
        //avvia la grafica
        graphics = new Graphics((nickname, livello) -> {
            Player player = new Player(nickname, livello, "127.0.0.1");
            connessioneServer(player);
        });
    }

    private void connessioneServer(String nickname, int livello) {
        new Thread(() -> {
            try(Socket s = new Socket("127.0.0.1", 50000);
                PrintWriter out = new PrintWriter(s.getOutputStream(), true);
                BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()))) {

                // Invio dei dati al server
                out.println(nickname);
                out.println(livello);

                // Attesa risposta del server
                String risposta = in.readLine();

                // Aggiorniamo la UI in modo sicuro
                SwingUtilities.invokeLater(() -> {
                    graphics.setStatus("Risposta: " + risposta, true);
                });

            } catch(IOException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                SwingUtilities.invokeLater(() -> {
                    graphics.setStatus("Errore di connessione!", true);
                });
            }
        }).start();
    }
}