package matchmaking;

import java.util.ArrayList;
import java.util.List;
import common.*;

public class Matchmaker {

    // Lista dei client in attesa di trovare una partita
    private List<ClientHandler> giocatoriInAttesa = new ArrayList<>();

    public synchronized void aggiungiGiocatore(ClientHandler handler) {
        giocatoriInAttesa.add(handler);

    }
}
