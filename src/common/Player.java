package common;

import java.io.Serializable;

public class Player implements Serializable { //serializable è per la conversione del oggetto player per mandarla più comodamente al server
    private static final long serialVersionUID = 1L; //sempre per la serializzazione sovraccitata
    private String name;
    private int lvl;
    private String ipAdress;

    public Player(String name, int lvl, String ipAdress){
        this.name=name;
        this.lvl=lvl;
        this.ipAdress=ipAdress;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public int getLvl(){
        return lvl;
    }

    public void setLvl(int lvl){
        this.lvl = lvl;
    }

    public String getIpAddress(){
        return ipAddress;
    }

    public void setIpAddress(String ipAddress){
        this.ipAddress = ipAddress;
    }
}