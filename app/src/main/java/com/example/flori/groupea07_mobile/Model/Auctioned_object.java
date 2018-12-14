package com.example.flori.groupea07_mobile.Model;

public class Auctioned_object {

    private int idObject;
    private String nameObject;
    private String descriptionObject;
    private int priceObject;
    private int idUser;
    private String catObject;

    public Auctioned_object(int id, String nameO, String dObject, int price, int user, String cat){
        this.idObject = id;
        this.nameObject = nameO;
        this.descriptionObject = dObject;
        this.priceObject = price;
        this.idUser = user;
        this.catObject = cat;
    }

    public int getIdObject(){ return idObject;}

    public void setIdObject(int id){ this.idObject = id;}

    public String getNameObject(){ return nameObject;}

    public void setNameObject(String name){ this.nameObject = name;}

    public String getDescriptionObject(){return descriptionObject;}

    public void setDescriptionObject(String descri){this.descriptionObject = descri;}

    public int getPriceObject(){ return priceObject;}

    public void setPriceObject(int price){ priceObject = price;}

    public int getIdUser(){ return idUser;}

    public void setIdUser(int id){ idUser = id;}

    public String getCatObject(){ return catObject;}

    public void setCatObject(String cat){ catObject = cat;}

}

