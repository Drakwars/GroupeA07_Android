package com.example.flori.groupea07_mobile.Model;

public class SellerUser {

    private int idSeller;
    private String username;
    private int nbSales;
    private int positiveVote;
    private int negativeVote;
    private int idUser;

    public SellerUser(int idS, String user, int nb, int pVote, int nVote, int idU){
        this.idSeller = idS;
        this.username = user;
        this.nbSales = nb;
        this.positiveVote = pVote;
        this.negativeVote = nVote;
        this.idUser = idU;
    }

    public int getIdSeller(){ return idSeller;}

    public void setIdSeller(int idS){ idSeller = idS;}

    public String getUsername() { return username;}

    public void setUsername(String user){ username = user;}

    public int getNbSales(){ return nbSales;}

    public void setNbSales(int nb){ nbSales = nb;}

    public int getPositiveVote(){ return positiveVote;}

    public void setPositiveVote(int pVote){ positiveVote = pVote;}

    public int getNegativeVote(){ return negativeVote;}

    public void setNegativeVote(int nVote){ negativeVote = nVote};

    public int getIdUser(){ return idUser;}

    public void setIdUser(int idU){ idUser = idU;}
}
