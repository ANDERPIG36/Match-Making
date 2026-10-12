package matchmaking;

import java.util.ArrayList;
import java.util.List;
import common.*;

public class Matchmaker {

    //lista giocatori in attesa
    private List<ClientHandler> giocatoriInAttesa = new ArrayList<>();

    public synchronized void aggiungiGiocatore(ClientHandler handler) {
        giocatoriInAttesa.add(handler);

        System.out.println("Giocatori in attesa: "
                + giocatoriInAttesa.size());
    }

}
