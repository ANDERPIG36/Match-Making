package matchmaking;

import java.io.*;
import java.net.Socket;
import common.*;

public class ClientHandler implements Runnable { //runnable serve per il thread

    private Socket socket;
    private Matchmaker matchmaker;
    private Player player;
    private ObjectOutputStream oos;

    public ClientHandler(Socket socket, Matchmaker matchmaker) {
        this.socket = socket;
        this.matchmaker = matchmaker;
    }

    @Override
    public void run() {

        try {
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
            oos = new ObjectOutputStream(socket.getOutputStream());

            //riceve i dati dal client
            this.player = (Player) ois.readObject();
            player.setIpAddress(socket.getInetAddress().getHostAddress());

            System.out.println("Ricevuto giocatore -> Nome: " + player.getName() + ", Livello: " + player.getLvl());

            //manda al matchmaker
            matchmaker.aggiungiGiocatore(this);

            //mettere che fa un ciclo di attesa per tenere la connesione

            //mettere risposta al client
            
        } catch (IOException | ClassNotFoundException ex) {
            ex.printStackTrace();
        }
    }

    public Player getPlayer() {
        return player;
    }

    //per mandare risposta al client
    public void inviaMessaggio(Match match) {
        try {
            oos.writeObject(match);
            oos.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}