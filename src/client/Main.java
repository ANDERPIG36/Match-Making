package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import java.awt.*;
import common.*;

public class Main {

    private static Graphics graphics;

    public static void main(String[] args) {

        Settings settings = new Settings();

        //avvia la grafica
        graphics = new Graphics((nickname, livello) -> {
            Player player = new Player(nickname, livello, "127.0.0.1");//ip in realtà è superfluo in questo contesto
            connessioneServer(player);
        });
    }

    private static void connessioneServer(Player player) {

        Settings settings = new Settings();

        new Thread(() -> {
            try(Socket s = new Socket("127.0.0.1", settings.getServerSocket());
                ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
                ObjectInputStream ois = new ObjectInputStream(s.getInputStream())) {

                //invio dei dati al server
                oos.writeObject(player);
                oos.flush();

                //attende la risposta del server
                Match matchTrovato = (Match) ois.readObject();

                //aggiorna la UI in modo sicuro per evitare errori a runtime
                SwingUtilities.invokeLater(() -> {
                    graphics.setStatus("Partita trovata", true);
                });

            } catch(IOException | ClassNotFoundException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                SwingUtilities.invokeLater(() -> {
                    graphics.setStatus("Errore di connessione!", true);
                });
            }
        }).start();
    }
}