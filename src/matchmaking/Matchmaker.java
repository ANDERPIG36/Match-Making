package matchmaking;

import java.util.ArrayList;
import java.util.List;
import common.*;

public class Matchmaker{

    //elenco client in attesa di trovare partita
    private List<ClientHandler> giocatoriInAttesa = new ArrayList<>();
    private List<ClientHandler> giocatorNelMatch = new ArrayList<>();
    private int livelloMedioMatch;

    public synchronized void aggiungiGiocatore(ClientHandler handler) {
        //aggiunge giocatore alla lista d'attesa
        giocatoriInAttesa.add(handler);
        //ordina i giocatori
        sortPerLivello();
        //controlla se ha senso creare un game
        if(matchDisponibile()){
            //crea game
            Match match = new Match(livelloMedio);
            for(int i=settings.giocatoriSquadra*4; i<0; i+2){//così funziona solo se sono i primi 6 da prendere
                Match.aggiungiA1(giocatoriNelMatch.get(i).getPlayer());
                Match.aggiungiA2(giocatoriNelMatch.get(i+1).getPlayer());
            }
            //risposta al client
        }
    }
    
    private void sortPerLivello(){
        for (int i=0; i<this.giocatoriInAttesa.size()-1; i++){
            for (int j=0; j<this.giocatoriInAttesa.size()-1-i; j++){
                if (this.giocatoriInAttesa.get(j).getPlayer().getLvl()>this.giocatoriInAttesa.get(j+1).getPlayer().getLvl()) {
                    ClientHandler temp = this.giocatoriInAttesa.get(j);
                    this.giocatoriInAttesa.set(j, this.giocatoriInAttesa.get(j+1));
                    this.giocatoriInAttesa.set(j+1,temp);
                }
            }
        }
    }

    private boolean matchDisponibile(){

    }
}