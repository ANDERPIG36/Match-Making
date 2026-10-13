package common;

public class Settings{
    private int serverSocket;

    public Settings(){
        serverSocket=50000;
    }

    public int getServerSocket(){
        return serverSocket;
    }
}