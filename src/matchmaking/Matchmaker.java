package matchmaking;

import java.util.ArrayList;
import java.util.List;
import common.*;

public class Matchmaker{

    //elenco client in attesa di trovare partita
    private List<ClientHandler> giocatoriInAttesa = new ArrayList<>();
    private List<ClientHandler> giocatoriNelMatch = new ArrayList<>();
    private int livelloMedioMatch;

    public synchronized void aggiungiGiocatore(ClientHandler handler) {
        //aggiunge giocatore alla lista d'attesa
        giocatoriInAttesa.add(handler);
        //ordina i giocatori
        sortPerLivello();
        //controlla se ha senso creare un game
        if(matchDisponibile()){
            //crea game
            Match match = new Match(livelloMedioMatch);
            for(int i=0; i<settings.giocatoriSquadra*4; i=i+2){//così funziona solo se sono i primi 6 da prendere
                match.aggiungiA1(giocatoriNelMatch.get(i).getPlayer());
                match.aggiungiA2(giocatoriNelMatch.get(i+1).getPlayer());
            }
            //risposta al client
            for(ClientHandler giocatore : giocatoriNelMatch){
                giocatore.inviaMessaggio(match);
            }
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
        for (int i=0; i<giocatoriInAttesa.size(); i++){
            int min = giocatoriInAttesa.get(i).getPlayer().getLvl();
            int j=i;

            while(j<giocatoriInAttesa.size() && giocatoriInAttesa.get(j).getPlayer().getLvl()-min <= 2*settings.getDifferenzaLivello()) {
                j++;
            }

            if(j-i >= settings.getGiocatoriSquadra()*2) {
                for (int k=i; k<j; k++){
                    giocatoriNelMatch.add(giocatoriInAttesa.get(k).getPlayer());
                }
                this.livelloMedioMatch=X;
                return true;
            }
        }
        return false;
    }
}