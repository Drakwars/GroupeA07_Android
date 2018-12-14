package com.example.flori.groupea07_mobile.Model;

public class Moderator {

    private int idModerator;
    private int idUser;

    public Moderator(int idM, int idU){
        this.idModerator = idM;
        this.idUser = idU;
    }

    public int getIdModerator(){ return idModerator;}

    public void setIdModerator(int idM){ idModerator = idM;}

    public int getIdUser(){ return idUser;}

    public void setIdUser(int idU){ idUser = idU;}

}
