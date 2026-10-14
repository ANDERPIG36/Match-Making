package stresstester;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import common.*;

public class Main {

    private static final Logger LOGGER = Logger.getLogger(Main.class.getName());

    public static void main(String[] args) {
        
        //client da simulare in contemporanea
        int nClient = 10;
        
        if(args.length > 0) {
            try {
                nClient = Integer.parseInt(args[0]);
            } catch (NumberFormatException e) {
                System.out.println("Argomento non valido, default: 10");
            }
        }

        System.out.println("Avvio dello stresstester per " + nClient + " client");

        //avvia n client in contemporanea usando dei thread
        for (int i = 1; i <= nClient; i++) {
            final int id = i;
            
            //ritardo leggero per non mandare tutto nello stesso momento
            try {
                Thread.sleep(50);
            } catch (InterruptedException ex) {
                Thread.currentThread().interrupt();
            }

            new Thread(() -> {

                String nickname = String.valueOf(id);
                
                Settings settings = new Settings();
                Random rand = new Random();
                int livello = rand.nextInt(settings.getLivelloMassimo()) + 1;

                Player player = new Player(nickname, livello, "127.0.0.1");
                connessioneServer(player, id);
            }).start();
        }
    }

    private static void connessioneServer(Player player, int idClient) {

        Settings settings = new Settings();

        try(Socket s = new Socket("127.0.0.1", settings.getServerSocket()); ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream()); ObjectInputStream ois = new ObjectInputStream(s.getInputStream())) {

            //invio dei dati al server
            oos.writeObject(player);
            oos.flush();
            
            System.out.println("[Client " + idClient + "] Inviato player: " + player.getName() + " (Lvl: " + player.getLvl() + ")");

            //attende la risposta del server
            Match matchTrovato = (Match) ois.readObject();

            //stampa a schermo la partita trovata
            if (matchTrovato != null) {
                StringBuilder sb = new StringBuilder();
                sb.append(String.format("[Client %d] Partita trovata! \n", idClient));
                sb.append("  SQUADRA 1\n");
                for (Player p : matchTrovato.getGiocatoriSquadra1()) {
                    sb.append(String.format("    - %s (%s) - lvl %d\n", p.getName(), p.getIpAddress(), p.getLvl()));
                }
                sb.append("  SQUADRA 2\n");
                for (Player p : matchTrovato.getGiocatoriSquadra2()) {
                    sb.append(String.format("    - %s (%s) - lvl %d\n", p.getName(), p.getIpAddress(), p.getLvl()));
                }
                System.out.print(sb.toString());
            }

        } catch(Exception ex) {
            LOGGER.log(Level.SEVERE, "[Client " + idClient + "] Errore di connessione!", ex);
        }
    }
}