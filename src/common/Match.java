package common;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Match implements Serializable {
    private static final long serialVersionUID = 1L;

    private List<Player> giocatoriSquadra1;
    private List<Player> giocatoriSquadra2;

    public Match() {
        this.giocatoriSquadra1 = new ArrayList<>();
        this.giocatoriSquadra2 = new ArrayList<>();
    }

    public List<Player> getGiocatoriSquadra1() {
        return giocatoriSquadra1;
    }
    public void setGiocatoriSquadra1(List<Player> giocatoriSquadra1) {
        this.giocatoriSquadra1 = giocatoriSquadra1;
    }

    public List<Player> getGiocatoriSquadra2() {
        return giocatoriSquadra2;
    }
    public void setGiocatoriSquadra2(List<Player> giocatoriSquadra2) {
        this.giocatoriSquadra2 = giocatoriSquadra2;
    }

    public void aggiungiA1(Player player) {
        giocatoriSquadra1.add(player);
    }
    public void aggiungiA2(Player player) {
        giocatoriSquadra2.add(player);
    }
}