package common;

public class Settings{
    private int serverSocket;
    private int giocatoriSquadra;
    private int differenzaLivello;

    public Settings(){
        serverSocket=50000;
        giocatoriSquadra=3;
        differenzaLivello=1;
    }

    public int getServerSocket(){
        return serverSocket;
    }
    public int getGiocatirSquadra(){
        return giocatoriSquadra;
    }
    public int getDifferenzaLivello(){
        return differenzaLivello;
    }
}