package matchmaking;

import java.io.*;
import java.net.Socket;
import common.*;

public class ClientHandler implements Runnable { //runnable serve per il thread

    private Socket socket;
    private Matchmaker matchmaker;
    private Player player;

    public ClientHandler(Socket socket, Matchmaker matchmaker) {
        this.socket = socket;
        this.matchmaker = matchmaker;
    }

    @Override
    public void run() {

        try {
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

            //riceve i dati dal client
            this.player = (Player) ois.readObject();

            System.out.println("Ricevuto giocatore -> Nome: " + player.getName() + ", Livello: " + player.getLvl());

            //manda al matchmaker
            matchmaker.aggiungiGiocatore(this);

        } catch (IOException | ClassNotFoundException ex) {
            ex.printStackTrace();
        }
    }

    public Player getPlayer() {
        return player;
    }

    //per mandare risposta al client
    public void inviaMessaggio(String messaggio) {
        try {
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            out.println(messaggio);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}